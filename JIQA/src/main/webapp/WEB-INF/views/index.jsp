<html>
<head>
<title>Login</title>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false" %>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<form action="authUser" method="POST">
		<div class="container">
			<div class="row">
				<div class="col-sm-12" align = "center">
					<h2>JIQA - Login Here</h2>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-3"></div>
				<div class="col-sm-6">
					<div class = "jumbotron">
						<c:if test = "${param.error ne null}">
							<div class="alert alert-danger fade in">
								<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
								<strong>${param.error}</strong>
							</div>
						</c:if>
						<div class="form-group">
							<label>Username</label> <input type="text" class="form-control" name = "username" placeholder="abc@gmail.com" />
						</div>
						<div class="form-group">
							<label>Password</label> <input type="password" class="form-control" name = "password" placeholder="**********" />
						</div>
						<div align = "right">
							<input type="submit" value="Login" class="btn btn-primary" />
						</div>
					</div>
				</div>
				<div class="col-sm-3"></div>
			</div>
		</div>
	</form>
</body>
</html>
