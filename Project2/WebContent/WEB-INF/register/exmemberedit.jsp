<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="org.masterjung.dto.UserDto"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/common/Head_top.jsp"></jsp:include>

<jsp:include page="/WEB-INF/common/Header_top.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Sidebar_Left.jsp"></jsp:include>
<!-- 콘텐츠 영역 시작-->
<script>
	$(function() {
		$("#upload").change(function(e) {
			event.preventDefault();
			var file = e.target.files[0];
			var url = URL.createObjectURL(file);
			$("#previewImage").attr("src", url);

		});

		CKEDITOR.replace('editor1', {

			width : 820,
			height : 300,
			enterMode : CKEDITOR.ENTER_BR
		})
	});
</script>
<c:set var="user" value="${requestScope.userdto}" />
<!-- BEGIN page-header -->
<h1 class="page-header">
	회원정보 <small>page header description goes here...</small>
</h1>
여기서 노세용!
<form action="memberedit.reg" method="POST"
	enctype="multipart/form-data">
	<ul class="setting-list">
		<li class="setting-title">GENERAL</li>
		<li>
			<div class="field">User image</div>
			<div class="value">
				<img
					src="<c:choose><c:when test ="${user.user_image_path != null}">${user.user_image_path}</c:when>    
                                                                                                          <c:otherwise>uploads/500x261.jpg</c:otherwise></c:choose>"
					style="width: 100px; height: 100px" id="previewImage"
					name="previewImage" />
			</div>
			<div class="action">
				<input type="file" class="form-control-file" id="upload"
					name="upload">
			</div>
		</li>
		<li>
			<div class="field">Email</div>
			<div class="col-sm-9">
				<input type="text" class="form-control" name="email" id="email"
					value="${userdto.email}" readonly/>
			</div>
		</li>
		
	</ul>
	<div align="right">
		<button type="submit" class="btn btn-primary">Edit</button>
	</div>
</form>
<!-- 콘텐츠영역 끝 -->
<jsp:include page="/WEB-INF/common/Sidebar_Right.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Footer_bottom.jsp"></jsp:include>