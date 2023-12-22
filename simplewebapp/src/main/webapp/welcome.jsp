<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome</title>
</head>
<body>

	<%
	
	response.setHeader("cache-control", "no-cache, no-store, must-validate");
	
	if (session.getAttribute("username") == null)
		response.sendRedirect("login.jsp");
	%>

	<h1>Welcome <%=request.getParameter("uname") %> </h1> 
	
	

	<form action = "DisplayDetails" method = "post">

		View Employee Details:
		<br>
		<br>
		Enter Employee id: <input type = text name = "empid">
		<br>
		<br>
		<input type = submit value = "View Details">

	</form>
	
	<br><br>
	
	<form action = "LogOut">
		<input type = submit value = "Logout">
	</form>


</body>
</html>