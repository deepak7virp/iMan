<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Applications List</title>

	<style>
		tr:first-child{
			font-weight: bold;
			background-color: #C6C9C4;
		}
	</style>

</head>


<body>
	<h2>List of Applications</h2>	
	<table>
		<tr>
			<td>NAME</td><td>CODE</td><td>Salary</td><td>SSN</td><td></td>
		</tr>
		<c:forEach items="${applications}" var="application">
			<tr>
			<td>${application.application_name}</td>
			<td>${application.application_code}</td>
<%-- 			<td>${employee.salary}</td> --%>
<%-- 			<td><a href="<c:url value='/edit-${employee.ssn}-employee' />">${employee.ssn}</a></td> --%>
<%-- 			<td><a href="<c:url value='/delete-${employee.ssn}-employee' />">delete</a></td> --%>
			</tr>
		</c:forEach>
	</table>
	<br/>
<%-- 	<a href="<c:url value='/new' />">Add New Employee</a> --%>
</body>
</html>