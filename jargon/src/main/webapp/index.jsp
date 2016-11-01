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
		
		$.each($("form input, form textarea, form select"), function(i,o) {
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
		
		$("body").append(
			$.ajax({
				url: "process/natural/language",
				method: "POST",
				dataType: "html",
				accepts: {
					"html": "text/html; charset=UTF-8"
				},
				async: false,
				processData: false,
				contentType: false,
				data: formData
			}).responseText
		);
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
			<textarea name="text">
				in de familie varices
				zit in familie
				zit niet in familie
				in familie geen varices
				geen atopie in familie
				atopie in familie
				atopische familie
				moeilijke familie
				in familie komt staar voor
				in familie komt geen staar voor
				hele familie heeft buisjes gehad
				familie gering atopie
				atopie licht in familie
			</textarea>
		</label>
		<label>
			<input type="checkbox" name="unabbreviate" checked="checked" /> Unabbreviate
		</label>
		<label>
			<input type="checkbox" name="spellcheck" checked="checked" /> Spellcheck
		</label>
		<label>
			<input type="checkbox" name="frog" checked="checked" disabled="disabled" /> Frog
		</label>
		<label>
			<input type="checkbox" name="summarize" checked="checked" /> Summarize
		</label>
		<label>
			Annotate:
			<select name="annotate">
				<option value="">Nothing</option>
				<option value="family">Family Relations</option>
			</select>
		</label>
		
		<input type="button" value="Process Natural Language" id="submitButton" />
	</form>
	
	<hr />
	
	
</body>
</html>