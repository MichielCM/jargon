<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>NLP</title>
<script type="text/javascript" src="js/jquery-3.0.0.min.js"></script>
</head>
<body>

	<form action="process/natural/language" method="post" enctype="multipart/form-data">
		<!--<label for="text">Text:</label>
		<input type="text" name="text" value="Dit is zgn. tekst." />-->
		<label for="foliafile">FoLiA File:</label>
		<input type="file" name="foliafile" />
		<label for="rulesfile">Rules File:</label>
		<input type="file" name="rulesfile" />
		<label for="querytext">Query Text:</label>
		<input type="text" name="querytext" />
		<input type="submit" value="OK" />
	</form>
	
</body>
</html>