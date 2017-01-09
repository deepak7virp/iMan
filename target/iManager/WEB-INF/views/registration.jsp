<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Registration</title>
<link href="<c:url value='/static/css/bootstrap.css' />"
	rel="stylesheet"></link>
<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
<link rel="stylesheet" type="text/css"
	href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
<style>
.error {
	color: #ff0000;
}
</style>

</head>

<body>
	<h2>Registration Form</h2>
	<form:form method="POST" modelAttribute="user">
		<form:input type="hidden" path="userId" id="userId" />
		<table>
			<tr>
				<td><label for="userEmail">Email: </label></td>
				<td><form:input path="userEmail" id="userEmail" /></td>
				<td><form:errors path="userEmail" cssClass="error" /></td>
			</tr>
			<tr>
				<td><label for="userPassword">Password: </label></td>
				<td><form:input path="userPassword" id="userPassword" /></td>
				<td><form:errors path="userPassword" cssClass="error" /></td>
			</tr>
			<c:forEach var="userApp" items="user.userApplications">
				<tr>
					<td><label>${app.application_name}</label></td>
				</tr>
			</c:forEach>

			<tr>
				<td colspan="3"><c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update" />
						</c:when>
						<c:otherwise>
							<input type="submit" value="Register" />
						</c:otherwise>
					</c:choose></td>
			</tr>
		</table>
	</form:form>
	<br />
	<br />
	<div style="display:none;">
		<div class="dropdown">
			<button class="btn btn-default dropdown-toggle" type="button"
				id="appDropdown" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="true">
				Select Application <span class="caret"></span>
			</button>
			<ul class="dropdown-menu" aria-labelledby="appDropdown">
				<c:forEach var="app" items="${applications}">
					<li><a href="#">${app.application_name}</a></li>
				</c:forEach>
			</ul>
		</div>
		
	</div>
	<%-- 	Go back to <a href="<c:url value='/list' />">List of All Employees</a> --%>
</body>
</html>