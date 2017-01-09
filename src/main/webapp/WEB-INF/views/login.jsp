<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<head>
		<c:url var="home" value="/" scope="request" />
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Login page</title>
		<link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>
		<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
		<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />
		<script>var CONTEXT_PATH = '${home}';</script>
	</head>

	<body>
		<div id="mainWrapper">
			<c:choose>
				<c:when test="${login}">
					<div class="login-container">
						<div class="login-card">
							<div class="login-form">
								<c:url var="loginUrl" value="/login" />
								<form:form action="${loginUrl}" method="post" modelAttribute="user" class="form-horizontal">
									<c:if test="${param.error != null}">
										<div class="alert alert-danger">
											<p>Invalid username and password.</p>
										</div>
									</c:if>
									<c:if test="${param.logout != null}">
										<div class="alert alert-success">
											<p>You have been logged out successfully.</p>
										</div>
									</c:if>
									<div class="input-group input-sm">
										<label class="input-group-addon" path="userEmail" for="userEmail"><i class="fa fa-user"></i></label>
										<form:input id="userEmail" cssClass="form-control" path="userEmail"></form:input>
									</div>
									<div class="input-group input-sm">
										<label class="input-group-addon" for="userPassword"><i class="fa fa-lock"></i></label> 
										<form:password cssClass="form-control" path="userPassword"></form:password>
									</div>
									<div class="input-group input-sm" style="width:100%;">
		                              <div class="checkbox" style="float:left;">
		                                <label><input type="checkbox" id="rememberme" name="remember-me"> Remember Me</label>  
		                              </div>
		                              <div class="checkbox" style="float:right;">
		                                <label><a id="register" href="<c:url value="/register" />"> Register </a>  
		                              </div>
		                            </div>
									<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
										
									<div class="form-actions">
										<input type="submit" class="btn btn-block btn-primary btn-default" value="Log in">
									</div>
								</form:form>
							</div>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="register-container">
						<div class="register-card">
							<div class="register-form">
								<form:form method="POST" modelAttribute="user">
									<form:input type="hidden" path="${user.userId}" id="userId"></form:input>
									<div class="container-fluid">
										<div class="row">
											<div class="input-group input-sm">
												<label class="input-group-addon" for="userEmail"><i class="fa fa-user"></i></label>
												<form:input class="form-control" path="userEmail"></form:input>
											</div>
											<div class="input-group input-sm">
												<label class="input-group-addon" for="userPassword"><i class="fa fa-lock"></i></label> 
												<form:input class="form-control" path="userPassword"></form:input>
											</div>
										</div>
										<div class="row">
											<c:forEach var="app" items="${user.userApplications}" varStatus="dep">
												<div class="col-sm-6">
													<label><form:checkbox id="app_${app.application_id}" cssClass="addAppCheckBox checkbox-circle" path="userApplications[${dep.index}].verified"  /> ${app.application_name}</label>
												</div>	
											</c:forEach>
										</div>
										<div class="row form-actions">
											<div class="col-lg-6">
												<input type="submit" class="btn btn-sml btn-block btn-primary btn-default" value="Register">
											</div>
											<div class="col-lg-6">
												<a href="/login" id="cancel" name="cancel" class="btn btn-sml btn-danger btn-default">Cancel</a>
											</div>
										</div>
									</div>
								</form:form>
							</div>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="modal fade bd-example-modal-sm" id="addAppModal" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="modal-header>
						<h4 id="addAppModalHeader" style="text-align:center;"class="modal-title">Add Application</h4> 
					</div>
					<div class="modal-body">
						<form>
							<input type="hidden" id="addApp_appId" name="Language" value="English">
							<div class="container-fluid">
								<div class="row">
									<div class="input-group input-sm">
										<label class="input-group-addon" for="addApp_username"><i class="fa fa-user"></i></label> 
 										<input type="text" class="form-control" id="addApp_username" placeholder="Enter App's Username" required> 
									</div>
									<div class="input-group input-sm">
										<label class="input-group-addon" for="addApp_password"><i class="fa fa-user"></i></label> 
 										<input type="password" class="form-control" id="addApp_password" placeholder="Enter App's Password" required> 
									</div>
									<div class="row form-actions top6"> 
										<div class="col-lg-4 left12"> 
											<input type="submit" id="addAppSubmit" class="btn btn-sml btn-block btn-primary btn-default" value="Add"> 
										</div> 
										<div class="col-lg-4 left12"> 
											<a href="#" id="addAppCancel" name="cancel" class="btn btn-sml btn-danger btn-default pull-right">Cancel</a> 
										</div> 
									</div> 
								</div>
							</div>
						<form>
					</div>
				</div>
			</div>
		</div>
		
		<script src="<c:url value='/static/js/jquery-3.1.1.js' />"></script>  
		<script src="<c:url value='/static/js/bootstrap.min.js' />"></script>  
		<script src="<c:url value='/static/js/app.js' />"></script>
	</body>
</html>