<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<jsp:include page="/WEB-INF/common/Head_top.jsp"></jsp:include>
<script src="https://cdn.ckeditor.com/4.11.4/standard/ckeditor.js"></script>
<script src="${pageContext.request.contextPath}/resource/js/tipboard.js" type="text/javascript"></script>
<jsp:include page="/WEB-INF/common/Header_top.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Sidebar_Left.jsp"></jsp:include>
<!-- 콘텐츠 영역 시작-->
<ul class="breadcrumb">
<h4> </h4>
</ul>

<h1 class="page-header"></h1>

<form name="tipboardedit" action="tipboardeditok.tb?id=${requestScope.board.id}" method ="POST" >
	<!-- BEGIN card -->	
	<div class="card">
		<!-- BEGIN card-header -->
		<div class="card-header card-header-inverse">
			<div class="col-11">
			<div class="form-group">
			  <input type="text" class="form-control" id="title" name="title" value="${requestScope.board.title}" maxlength = "50">
			</div>
			</div>
			&nbsp;&nbsp;&nbsp;
			<div class="card-header-btn">
				<a href="#" data-toggle="card-expand" class="btn btn-success"
					data-original-title="" title="" data-init="true"><i
					class="fa fa-expand"></i></a> <a href="#" data-toggle="card-refresh"
					class="btn btn-warning" data-original-title="" title=""><i
					class="fa fa-redo"></i></a> <a href="#" data-toggle="card-remove"
					class="btn btn-danger" data-original-title="" title=""
					data-init="true"><i class="fa fa-trash-alt"></i></a>
			</div>
		</div>
		<!-- END card-header -->
		
		<!-- BEGIN editor1note -->
		<div class="note-editor note-frame card">
			<div class="row">
				<div class="col-12">
					<textarea name="editor1">${requestScope.board.content}</textarea>
				</div>	
			</div>
		</div>
		<!-- END editor1note -->
		
		<!-- BEGIN card-footer -->
		<div class="card-footer" id="write-button">
			<div class="text-right"><button type="submit" class="btn btn-primary ml-auto">Save</button>&nbsp;</div>
			<div class="text-right"><button type="button" class="btn btn-secondary" id="cancelBtn" onclick="location.href='tipboard.tb'">Cancel</button></div>
		</div>
		<!-- END card-footer -->
	</div>
	
	<!-- END card -->
</form>

<!-- 콘텐츠영역 끝 -->
<jsp:include page="/WEB-INF/common/Sidebar_Right.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Footer_bottom.jsp"></jsp:include>