<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
</head>

<!-- BEGIN #app / 이거의 끝(/div)는 Footer_bootem.jsp에 있음. -->
<div id="app" class="app app-header-fixed app-sidebar-fixed">


	<!-- BEGIN #header -->
	<header id="header" class="app-header">
		<!-- BEGIN navbar-toggle-minify -->
		<button type="button" class="navbar-toggle navbar-toggle-minify"
			data-click="sidebar-minify">
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<!-- END navbar-toggle-minify -->
		<!-- BEGIN navbar-header -->
		<div class="navbar-header">
			<a href="index.reg" class="navbar-brand"> Game Community </a>
			<button type="button" class="navbar-toggle"
				data-click="sidebar-toggled">
				<span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
		</div>
		<!-- END navbar-header -->
		
		<!-- BEGIN navbar-nav -->
		<ul class="navbar-nav navbar-right">
			<c:set var="auth" value="${user_auth}"/>
				<c:choose>
					<c:when test="${empty auth}">
						<li class="nav-item"><a href="#" 
							class="nav-link"> <i <c:choose><c:when test="${not empty sessionScope.nick_name}"> class="fas fa-lg fa-lock-open" </c:when>
														<c:when test="${empty sessionScope.nick_name}"> class="fas fa-lg fa-lock" </c:when></c:choose>></i>
						</a></li>
						<li>
							<div class="navbar-header">
								<a href="registerform.reg" class="navlogin"> 회원가입 </a>
							</div>
						</li>
						<li>
							<div class="navbar-header">
								<a href="#inverse-modal" data-toggle="modal" class="navlogin">
									로그인&nbsp; </a>
							</div>
						</li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a href="#" 
							class="nav-link"> <i class="fas fa-lg fa-lock-open"></i>
						</a></li>
						<li>
							<div class="navbar-header">
								<a href="memberinfo.reg" class="navlogin">[${nick_name}] 님</a>
							</div>
						</li>
						<li>
							<div class="navbar-header">
								<a href="#logout-modal" data-toggle="modal" class="navlogin">
									<small>로그아웃</small> </a>
							</div>
						</li>
					</c:otherwise>
				</c:choose>
		</ul>
		<!-- END navbar-nav -->
		
		<!-- BEGIN navbar-search -->
		<div class="navbar-search">
			<form action="#" method="POST" name="navbar_search_form">
				<div class="form-group">
					<div class="icon">
						<i class="fa fa-search"></i>
					</div>
					<input type="text" class="form-control" id="header-search"
						placeholder="Search admetro..." />
					<div class="icon">
						<a href="#" data-dismiss="search-bar" class="right-icon"><i
							class="fa fa-times"></i></a>
					</div>
				</div>
			</form>
		</div>
		<!-- END navbar-search -->
	</header>
	<!-- END #header -->