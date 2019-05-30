<%@page import="org.masterjung.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/common/Head_top.jsp"></jsp:include>

<link type="text/css"
	href="${pageContext.request.contextPath}/resource/css/memberinfo.css"
	rel="stylesheet" />

<jsp:include page="/WEB-INF/common/Header_top.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Sidebar_Left.jsp"></jsp:include>
<!-- 콘텐츠 영역 시작-->

<c:set var="user" value="${requestScope.userdto}" />
<div class= "row">
<div class="col-3"></div>
<div class="col-7">
<div class="row">
	<div class="col-8">
		<!-- BEGIN page-header -->
		<h1 class="page-header">
			회원정보 <small>${userdto.user_name}님의 정보</small>
		</h1>
		<!-- END page-header -->
	</div>

	<div class="col-4">
		<div align="right">
			<button type="button" class="btn btn-primary active"
				onclick="location.href='membereditview.reg'">Edit</button>
		</div>
	</div>
</div>

<!-- BEGIN setting-list -->

<ul class="setting-list">
	<li class="setting-title">회원 정보</li>
	<li>
		<div class="field">회원 사진</div>
		<div class="value">
			<img src="${user.user_image_path}"
				style="width: 200px; height: 150px" id="previewImage"
				name="previewImage" />
		</div>
	</li>
	<li>
		<div class="field">이름</div>
		<div class="value">${userdto.user_name}</div>
	</li>
	<li>
		<div class="field">이메일</div>
		<div class="value">${userdto.email}</div>
	</li>
	<li>
		<div class="field">닉네임</div>
		<div class="value">${userdto.nick_name}</div>
	</li>
	<li>
		<div class="field">핸드폰 번호</div>
		<div class="value">0${userdto.phone_number}</div>
	</li>
	<li>
		<div class="field">주소</div>
		<div class="value">${userdto.user_address}</div>
	</li>
	<li>
		<div class="field">회원 등급</div>
		<div class="value">
			<c:choose>
				<c:when test="${userdto.user_auth eq 1}"> 일반 회원</c:when>
				<c:when test="${userdto.user_auth eq 2}"> 기자 회원</c:when>
				<c:when test="${userdto.user_auth eq 3}"> 관리자</c:when>
			</c:choose>
		</div>
	</li>
</ul>
</div>
</div>

<br>


<!-- END setting-list -->

<!-- 콘텐츠영역 끝 -->
<jsp:include page="/WEB-INF/common/Sidebar_Right.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Footer_bottom.jsp"></jsp:include>