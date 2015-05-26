<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Comcast Registrations</title>

	<style>
		tr:first-child{
			font-weight: bold;
			background-color: #C6C9C4;
		}
	</style>

</head>


<body>
	<h2>Registered Users</h2>	
	<table>
		<tr>
			<td>User Id</td><td>Name</td><td>Registered Date</td><td>Email</td><td></td>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.userName}</td>
				<td>${user.registeredDate}</td>
				<td>${user.email}</td>
				
				<td><a href="<c:url value='/users/${user.id}' />">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br/>
	<a href="<c:url value='/users/registration' />">Register</a>
</body>
</html>