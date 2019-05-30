<%@page import="org.masterjung.dto.UserDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/common/Head_top.jsp"></jsp:include>

<link type="text/css"
	href="${pageContext.request.contextPath}/resource/css/memberedit.css"
	rel="stylesheet" />

<jsp:include page="/WEB-INF/common/Header_top.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Sidebar_Left.jsp"></jsp:include>
<!-- 콘텐츠 영역 시작-->



<c:set var="user" value="${requestScope.userdto}" />

<div class="row">
	<div class="col-3"></div>
	<div class="col-7">
		<div class="row">
			<div class="col-8">
				<!-- BEGIN page-header -->
				<h1 class="page-header">
					회원 정보 수정 <small></small>
				</h1>
				<!-- END page-header -->
			</div>


		</div>

		<!-- BEGIN setting-list -->
		<div>
			<form action="userdeleteok.ad?id=${userdto.id}" method="post">
				<ul class="setting-list">
					<li class="setting-title">정보</li>
					<li>
						<div class="field">이메일</div>
						<div class="value">
							<div class="row">
								<div class="col-sm-8">
									<input type="text" class="form-control" name="email" id="email"
										value="${userdto.email}" readonly />
								</div>
							</div>
						</div>
					</li>


					<li>
						<div class="field">닉네임</div>
						<div class="value">
							<div class="row">
								<div class="col-sm-8">
									<input type="text" class="form-control" name="nick_name"
										id="nick_name" value="${userdto.nick_name}" readonly />
								</div>
								<div class="col-sm-4">
									<label><span class="text-danger" id="nickinfo">*</span></label>
								</div>
							</div>
						</div>
					</li>
					<li>
						<div class="field">핸드폰 번호</div>
						<div class="value">
							<div class="row">
								<div class="col-sm-8">
									<input type="text" class="form-control" name="phone_number"
										id="phone_number" value="0${userdto.phone_number}" readonly />
								</div>
							</div>
						</div>
					</li>
					<li>
						<div class="field">이름</div>
						<div class="value">
							<div class="row">
								<div class="col-sm-8">
									<input type="text" class="form-control" name="user_name"
										id="user_name" value="${userdto.user_name}" readonly />
								</div>
							</div>
						</div>
					</li>
					<li>
						<div class="field">주소</div>
						<div class="value">

							<div class="form-group">
								<a href="javascript:openAddrPop();"><input type="text"
									class="form-control" name="user_address" id="user_address"
									value="${userdto.user_address}호" readonly /></a>
							</div>
						</div>
					</li>
					<li id="auth">
						<div class="field">회원 권한</div>
						<div class="value">
							<div class="form-group">
								<select class="selectpicker form-control"
									data-style="btn-default" name="user_auth" id="user_auth" readonly>
									<c:choose>
										<c:when test="${userdto.user_auth eq 1}">
											<option value ="1" selected>일반회원</option>
											<option value ="2">기자회원</option>
											<option value ="3">관리자</option>
										</c:when>
										<c:when test="${userdto.user_auth eq 2}">
											<option value ="1" >일반회원</option>
											<option value ="2" selected>기자회원</option>
											<option value ="3">관리자</option>
										</c:when>
										<c:when test="${userdto.user_auth eq 3}">
											<option value ="1">일반회원</option>
											<option value ="2">기자회원</option>
											<option value ="3" selected>관리자</option>
										</c:when>
									</c:choose>
								</select>
							</div>
						</div>
					</li>
				</ul>
				<br>
				<div align="center">
					<button type="submit" class="btn btn-primary" id="edit">회원 삭제</button>
				</div>
			
				<br>

			</form>
		</div>
	</div>
</div>
<br>


<!-- END setting-list -->

<!-- 콘텐츠영역 끝 -->
<jsp:include page="/WEB-INF/common/Sidebar_Right.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Footer_bottom.jsp"></jsp:include>