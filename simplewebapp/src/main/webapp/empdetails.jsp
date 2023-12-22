<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Details</title>
</head>
<body bgcolor="cyan">

<% response.setHeader("cache-control", "no-cache, no-store, must-validate");
if (session.getAttribute("username") == null)
		response.sendRedirect("login.jsp");%>

Employee Id : <%= request.getParameter("empid") %><br><br>
Employee Name : <%= request.getAttribute("ename") %> <br><br>
Employee Designation  : <%= request.getAttribute("desg") %> <br><br>
Employee Salary : <%= request.getAttribute("sal") %> <br><br>

<br><br>

<form action = "welcome.jsp">
	<input type = submit value = "Back">
</form>

<br><br>

<form action = "LogOut">
	<input type = submit value = "Logout">
</form>

</body>
</html>