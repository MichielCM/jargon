<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NLP</title>
<script type="text/javascript" src="js/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="js/foliaviewer.js"></script>
<link rel="stylesheet" type="text/css" href="css/foliaviewer.css">
<style type="text/css">
body {
	zoom: 125%;
	font-family: Calibri;
}
label {
	/*display: block;
	margin-bottom: 5px;*/
	margin-right: 10px;
}
input[type=text] {
	width: 300px;
}
textarea {
	width: 100%;
	height: 150px;
}
</style>

<script type="text/javascript">
$(document).ready(function() {
	
	var submitForm = function() {
		var formData = new FormData();
		
		$.each($("form input, form textarea"), function(i,o) {
			switch($(o).attr("type")) {
			case "text":
				formData.append($(o).attr("name"), $(o).val());
				break;
			case "checkbox":
				if ($(o).is(":checked"))
					formData.append($(o).attr("name"), $(o).val());
				break;
			case "button":
				break;
			default:
				formData.append($(o).attr("name"), $(o).val());
			}
		});
		
		$(".foliaViewer").remove();
		
		$.each(
			$.parseJSON(
				$.ajax({
					url: "process/natural/language",
					method: "POST",
					async: false,
					processData: false,
					contentType: false,
					data: formData
				}).responseText
			),
		function(i,o) {
			$("body").append(
				new FoliaViewer(o).view()
			);
		});
		
		/*$.each(
			$($.parseXML(
				$.ajax({
					url: "process/natural/language",
					method: "POST",
					async: false,
					processData: false,
					contentType: false,
					data: formData
				}).responseText
			)
		).find("Array > item"), function(i,o) {
			$("body").append(
				new FoliaViewer(o).view()
			);
		});*/
	}
	
	$("#submitButton").on("click", submitForm);
	
	$("textarea").focus();
});
</script>

</head>
<body>

	<!--<form action="process/natural/language" method="post" enctype="multipart/form-data">-->
	<form action="">
		<label>
			<!--Query Text:	<input type="text" name="text" value="de meneer heeft last van zware hoofdpijn. en hij vindt dat naar." />-->
			<textarea name="text">de meneer heeft last van zware hoofdpijn. en hij vindt dat naar.</textarea>
		</label>
		<label>
			<input type="checkbox" name="unabbreviate" /> Unabbreviate
		</label>
		<label>
			<input type="checkbox" name="spellcheck" /> Spellcheck
		</label>
		<label>
			<input type="checkbox" name="frog" checked="checked" /> Frog
		</label>
		
		<input type="button" value="Process Natural Language" id="submitButton" />
	</form>
	
	<hr />
	
	
</body>
</html>