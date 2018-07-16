<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>Login Page</title>

	<!-- Bootstrap core CSS -->
	<link href="includes/css/tools/bootstrap.min.css"  media="all"  type="text/css" rel="stylesheet"/>
	<style type="text/css">
		body{
			background-color: #f0f0f0;
			width:100%;
		}
		.container{
			width : 400px;
			margin:0 auto;
		}
		.input-group-addon.glyphicon{
			top:0px;
		}
	</style>


</head>
<body onload="document.loginForm.username.focus();">
	<div class="container">
		<c:url value="/loginAuth" var="loginUrl"/>
		<form class="form-signin" action="${loginUrl}" method="post">    
		 	<h2 class="form-signin-heading">Please sign in</h2>
			<c:if test="${param.error != null}">      
				<p>
					<div class="alert alert-warning" role="alert">Invalid username and password.</div>
				</p>
			</c:if>
			<c:if test="${param.logout != null}">       
				<p>
					<div class="alert alert-info" role="alert">You have been logged out.</div>
				</p>
			</c:if>
			
			<c:if test="${nopass != null}">       
				<p>
					<div class="alert alert-danger" role="alert"><b>No password file is configured</b>,<br/> please use the default user.</div>
				</p>
			</c:if>
			
			
				<label class="sr-only" for="username">Username</label>
				<div class="input-group">
					<span class="input-group-addon glyphicon glyphicon-user" ></span>
					<input type="text" autofocus="" required="" placeholder="Username" class="form-control" id="username" name="username"/>	
				</div>
				<label class="sr-only" for="password">Password</label>
				<div class="input-group">
					<span class="input-group-addon glyphicon glyphicon-lock" aria-hidden="true"></span>
					<input type="password" required="" placeholder="Password" class="form-control" id="password" name="password"/>
				</div>	
			<div class="checkbox">
	          <label>
	            <input type="checkbox" value="remember-me" /> Remember me 
	          </label>
	        </div>
			<button type="submit" class="btn btn-lg btn-primary btn-block">Log in</button>
		</form>
	</div>


</body>
</html>