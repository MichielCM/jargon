<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NLP</title>
<script type="text/javascript" src="js/jquery-3.1.0.min.js"></script>
<style type="text/css">
body {
	zoom: 150%;
	font-family: Calibri;
}
label {
	display: block;
	margin-bottom: 5px;
}
input[type=text] {
	width: 300px;
}
</style>

<script type="text/javascript">
$(document).ready(function() {
	
	var submitForm = function() {
		var formData = new FormData();
		
		$.each($("form input"), function(i,o) {
			switch($(o).attr("type")) {
			case "text":
				formData.append($(o).attr("name"), $(o).val());
				break;
			case "checkbox":
				if ($(o).is(":checked"))
					formData.append($(o).attr("name"), $(o).val());
				break;
			}
		});
		
		$.ajax({
			url: "process/natural/language",
			method: "POST",
			processData: false,
			contentType: false,
			data: formData,
			success: function(data) {
				console.log(data);
			}
		});
	}
	
	$("#submitButton").on("click", submitForm);
});
</script>

</head>
<body>

	<!--<form action="process/natural/language" method="post" enctype="multipart/form-data">-->
	<form>
		<label>
			Query Text:	<input type="text" name="text" />
		</label>
		<label>
			<input type="checkbox" name="unabbreviate" checked="checked" /> Unabbreviate
		</label>
		<label>
			<input type="checkbox" name="spellcheck" checked="checked" /> Spellcheck
		</label>
		<label>
			<input type="checkbox" name="frog" checked="checked" /> Frog
		</label>
		
		<input type="button" value="Process Natural Language" id="submitButton" />
	</form>
	
</body>
</html>