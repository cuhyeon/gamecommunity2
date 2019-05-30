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
<script src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/address.js"
	type="text/javascript"></script>

<script>
$(function(){
		var email = "<%=(String) session.getAttribute("email")%>";
		if (email != 'test') {
			$('#auth').css("visibility", "hidden");
		}

	});
</script>

<c:set var="user" value="${requestScope.userdto}" />

<div class= "row">
<div class="col-3"></div>
<div class="col-7">
<form action="memberedit.reg" method="POST"
		enctype="multipart/form-data">
<div class="row">
	<div class="col-8">
		<!-- BEGIN page-header -->
		<h1 class="page-header">
			회원 정보 수정 <small></small>
		</h1>
		<!-- END page-header -->
	</div>
	<div class="col-4">
	
		<div align="right">
			<button type="submit" class="btn btn-primary" id="edit"  disabled>Edit</button>
		</div>
	</div>

</div>

<!-- BEGIN setting-list -->
<div>
	
		<ul class="setting-list">
			<li class="setting-title">정보</li>
			<li>
				<div class="field">회원 사진</div>
				<div class="value">
					<img src="${user.user_image_path}"
						style="width: 200px; height: 150px" id="previewImage"
						name="previewImage" />
				</div>
				<div class="action">
					<div class="filebox">
						<label for="upload">이미지 업로드</label> 
							<input type="file"  class = "form-control-file"name="upload" id="upload" accept="image/gif, image/jpeg, image/png">
					</div>
				</div>

			</li>
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
				<div class="field">비밀번호</div>
				<div class="value">
					<div class="row">
						<div class="col-sm-8">
							<input type="password" class="form-control" name="password"
								id="password" value=""  maxlength = "50"/>
						</div>
						<div class="col-sm-4">
							<label><span class="text-danger"></span></label>
						</div>
					</div>
				</div>
			</li>
			<li>
				<div class="field">비밀번호 확인</div>
				<div class="value">
					<div class="row">
						<div class="col-sm-8">
							<input type="password" class="form-control" name="password2"
								id="password2" value=""  maxlength = "50"/>
						</div>
						<div class="col-sm-4">
							<label><span class="text-danger" id="passwordinfo">* 비밀번호를 입력해 주세요</span></label>
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
								id="nick_name" value="${userdto.nick_name}" readonly/>
						</div>
						<div class="col-sm-4">
							<label><span class="text-danger" id="nickinfo"></span></label>
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
								id="phone_number" value="0${userdto.phone_number}"  maxlength = "11"/>
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
								id="user_name" value="${userdto.user_name}"  maxlength = "15"/>
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
							value="${userdto.user_address}"  maxlength = "100"/></a>
					</div>
				</div>
			</li>
			<li id="auth">
				<div class="field">회원 권한</div>
				<div class="value">
					<div class="form-group">
						<select class="selectpicker form-control" data-style="btn-default"
							name="user_auth" id="user_auth">
							<option selected value="1">일반회원</option>
							<option value="2">기자회원</option>
							<option value="3">관리자</option>
						</select>
					</div>
				</div>
			</li>
		</ul>

		<br>
		<input type="text" class="form-control" name="hpath" id="hpath"
								value="${user.user_image_path}" hidden />
	</form>
</div>
</div></div>
<br>


<!-- END setting-list -->

<!-- 콘텐츠영역 끝 -->
<jsp:include page="/WEB-INF/common/Sidebar_Right.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Footer_bottom.jsp"></jsp:include>