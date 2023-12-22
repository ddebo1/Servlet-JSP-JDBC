<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>

	<title>Student Tracker App</title>
	
	<!--  linking css to html using link tag -->
	<link type="text/css"  rel = "stylesheet" href  = "css/style.css">
	
</head>

<body>

	<!--  div is used as container to apply styling -->

	<div id = "wrapper"> 
		<div id = "header">
			<h2> FooBar University</h2>
		</div>
	</div>
	
	<div id = "container">
		<div id = "content">
			
			<input type="button" value="Add Student" 
				   onclick="window.location.href='add-student-form.jsp'; return false;"		   
				   class="add-student-button"
			/>
			<!-- return false prevents default form submission -->
			
			<table>
				<tr>
					<th> First Name </th>
					<th> Last Name </th>
					<th> Email Id </th>
					<th> Action</th>
				</tr>			
				<c:forEach var = "student" items = "${STUDENT_LIST}">
				<c:url var ="tempLink" value = "StudentControllerServlet">
				<c:param name = "command" value = "LOAD"/>
				<c:param name = "studentId" value = "${student.id}"/>
				</c:url>
				
				<c:url var ="deleteLink" value = "StudentControllerServlet">
				<c:param name = "command" value = "DELETE"/>
				<c:param name = "studentId" value = "${student.id}"/>
				</c:url>
				
				
					<tr>
						<td>${student.firstName}</td>
						<td>${student.lastName}</td>
						<td>${student.email}</td>
						<td> 
							<a href = "${tempLink}">Update</a>
							|
							<a href = "${deleteLink}" onclick="if(!(confirm('Are you sure you want to delete this student?'))) return false">Delete</a>
							
							<!-- return false prevents the default behavior of the link (i.e., navigating to the specified URL). As a result, if the user clicks "Cancel" in the confirmation dialog, the link won't trigger navigation. -->
							
						</td>
					</tr>
				</c:forEach>			
			</table>
		</div>
	</div>


</body>
</html>