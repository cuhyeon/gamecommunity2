<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>Login</title>
<meta
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"
	name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/app.min.css"/>

</head>
<body>
	<!-- BEGIN #app -->
	<div id="app" class="app app-full-height app-without-header">
		<!-- BEGIN login -->
		<div class="login">
			<!-- BEGIN login-cover -->
			<div class="login-cover"></div>
			<!-- END login-cover -->
			<!-- BEGIN login-content -->
			<div class="login-content">
				<!-- BEGIN login-brand -->
				<div class="login-brand">
					<a href="#"><b>Ad</b>metro</a>
				</div>
				<!-- END login-brand -->
				<h3 class="m-b-20">
					<span>Sign In</span>
				</h3>
				<!-- BEGIN login-desc -->
				<div class="login-desc m-b-30">For your protection, please
					verify your identity.</div>
				<!-- END login-desc -->
				<!-- BEGIN login-form -->
				<form action="login.reg" method="POST" name="login_form">
					<div class="form-group">
						<label>Username <span class="text-danger">*</span></label> <input
							type="text" class="form-control" value=""  maxlength = "30"/>
					</div>
					<div class="form-group">
						<label>Password <span class="text-danger">*</span></label> <input
							type="password" class="form-control" value=""  maxlength = "20"/>
					</div>
					<div class="m-b-30">
						<div class="checkbox-inline">
							<input type="checkbox" id="login-remember-me" value="2">
							<label for="login-remember-me">Remember me</label>
						</div>
					</div>
					<div class="d-flex align-items-center">
						<button type="submit"
							class="btn btn-primary width-150 btn-rounded">Sign In</button>
						<a href="#" class="m-l-10">Forgot password?</a>
					</div>
				</form>
				<!-- END login-form -->
				<div class="login-desc m-t-30">
					Not a member yet? Register <a href="page_register.html">Here</a>.
				</div>
			</div>
			<!-- END login-content -->
		</div>
		<!-- END login -->

		<!-- BEGIN btn-scroll-top -->
		<a href="#" data-click="scroll-top" class="btn-scroll-top fade"><i
			class="fa fa-arrow-up"></i></a>
		<!-- END btn-scroll-top -->
	</div>
	<!-- END #app -->


	<!-- ================== BEGIN BASE JS ================== -->
	<script src="${pageContext.request.contextPath}/resource/js/app.min.js" type="text/javascript"></script>
	<!-- ================== END BASE JS ================== -->
</body>
</html>
