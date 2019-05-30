<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/common/Head_top.jsp"></jsp:include>
	<script src="https://cdn.ckeditor.com/4.11.4/standard/ckeditor.js"></script>
	<script src="${pageContext.request.contextPath}/resource/js/qaboard.js" type="text/javascript"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/qaboard.css"/>
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resource/css/videowrite.css" />
<jsp:include page="/WEB-INF/common/Header_top.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Sidebar_Left.jsp"></jsp:include>
<!-- 콘텐츠 영역 시작-->
<form name="qawrite" action="videoeditok.vb?id=${requestScope.result.id}" enctype="multipart/form-data" method="POST">
	<div class="row text-center">
		<div class="col-1"></div>
			<div class="col-10">
				<h5>게시물 수정</h5>
				<hr>
			</div>
		<div class="col-1"></div>
	</div>
	<div class="row">
		<div class="col-1"></div>
		<div class="col-10">
			<input class="form-control" type="text" placeholder="비디오 게시판" readonly>
		</div>
		<div class="col-1"></div>
	</div>
	<div class="row">
		<div class="col-1"></div>
		<div class="col-10">
			<div class="form-group">
			  <input type="text" class="form-control" id="title" name="title" value ="${requestScope.result.title}" maxlength = "50">
			</div>
		</div>
		<div class="col-1"></div>
	</div>

	<div class="row">
		<div class="col-1"></div>
    		<div class="col-10">
    			<textarea name="editor1">${requestScope.result.content}</textarea>
    		</div>
    	<div class="col-1"></div>
	</div>
	<div class="row" id="write-button">	
     	<div class="col-1"></div>	 
     		<!--  <input type="file" name="upload" id="upload"> 파일 업로드 -->
		<div class="col-5"></div>
		<div class="text-left"><button type="button" class="btn btn-secondary" id="cancel-btn">취소</button></div>
		&nbsp;
			<div class="text-right"><button type="submit" class="btn btn-primary" id="enter-btn">등록</button></div>
		<div class="col-1"></div>
		</div>
		<%-- 
		<input type= "text" value="${requestScope.result.file_path}" style = "border: 0px; background-color: #d9d9d9; color:#d9d9d9;" name="vbfilepath" id="vbfilepath" readonly>
		 --%>
		
</form>
<!-- 콘텐츠영역 끝 -->
<jsp:include page="/WEB-INF/common/Sidebar_Right.jsp"></jsp:include>

<jsp:include page="/WEB-INF/common/Footer_bottom.jsp"></jsp:include>
			
			