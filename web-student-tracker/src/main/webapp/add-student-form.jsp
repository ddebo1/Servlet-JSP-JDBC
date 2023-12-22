<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add a Student</title>
<link type = "text/css" rel = "stylesheet" href  = "css/style.css">
<link type = "text/css" rel = "stylesheet" href  = "css/add-student-style.css">
</head>
<body>

	<div id = "wrapper"> 
		<div id = "header">
			<h2> FooBar University</h2>
		</div>
	</div>
	
	<div id = "container">
	
		<h3> Add Student</h3>
		
		<form action = "StudentControllerServlet">	
			
			<input type = "hidden" name = "command" value = "ADD"/>
			<table>
			<tr>
			<td><label> First Name : </label>  </td>
			<td> <input type = "text" name = "firstname"/> </td>
			</tr>
			<tr>
			<td><label> Last Name : </label> </td>
			<td> <input type = "text" name = "lastname"/> </td>
			</tr>
			<tr>
			<td><label> Email Id :  </label> </td>
			<td> <input type = "text" name = "email"/> </td>
			</tr>
			<tr>
			<td><label>  </label> </td>
			<td> <input type = "submit" value = "Save" class = "save"> </td>
			</tr>
			</table>
			
		</form>
		
		<div style = "clear:both;"></div>
		
		<a href = "StudentControllerServlet">Back to List</a>
		
			
	</div>

</body>
</html>