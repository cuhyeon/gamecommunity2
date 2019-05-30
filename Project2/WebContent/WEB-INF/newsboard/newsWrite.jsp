<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/common/Head_top.jsp"></jsp:include>
<script src="https://cdn.ckeditor.com/4.11.4/standard/ckeditor.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resource/css/newswrite.css" />
<script src="${pageContext.request.contextPath}/resource/js/newswrite.js" type="text/javascript"></script>



<jsp:include page="/WEB-INF/common/Header_top.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Sidebar_Left.jsp"></jsp:include>
	<div class="card m-b-10" id="wrapper">
	<!-- BEGIN card -->
	<div class="card m-b-10" id="wrapper">
			<div class="card-header card-header-inverse">
			<h4 class="card-header-title">GAME NEWS</h4>

		</div>
<div class="row justify-content-md-center" id = "wrapper" >
	<form id="news-write-form" action="writeOk.nb" enctype="multipart/form-data" method="post">

		<!-- BEGIN card-header -->

		<div class="content"  >
		<br>
			<table id="form-table">
			
				<tr>
					<td><label class="input-group-text">분류</label></td>
					<td>&nbsp;게임 뉴스게시판</td>
					<td rowspan=6><img src="upload/default-img.gif"
						id="previewImage" name="image" /></td>
				</tr>


				<tr>

					<td><span class="input-group-text" id="inputGroupFileAddon01">메인</span></td>

					<td><input type="file" class="form-control-file" id="upload"
						name="upload" value="upload/500x261.jpg" accept="image/gif, image/jpeg, image/png"></td>
					<td></td>
				</tr>
				<tr>
					<td><label class="input-group-text">별명</label></td>
					<td><input type="text" class="form-control"  value="${sessionScope.nick_name}"
						id="nick_name" name="nick_name" readonly></td>
					<td></td>
				</tr>
				<tr>
					<td><label class="input-group-text">등급</label></td>
					<td><input type="text" class="form-control" value="기자"
						readonly></td>
				</tr>
			</table>
			<br>

			<table>
				<tr>
					<td><label class="input-group-text">제목</label></td>
					<td><input type="text" class="form-control" name="title"
						id="title" placeholder="제목을 입력하세요" maxlength="150"></td>
				</tr>
			</table>

		</div>
		<br>
		<div class="row justify-content-md-left">
			<div class="col_c">
				<div class="input-group">

					<textarea class="form-control" name="editor1"></textarea>

				</div>
			</div>
		</div>

		<div class="row justify-content-md-center">
			<button type="submit" class="btn btn-outline-secondary" id="submit"disabled>등록</button>
		</div>
	</form>
</div>
</div>
</div>
<jsp:include page="/WEB-INF/common/Sidebar_Right.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Footer_bottom.jsp"></jsp:include>
