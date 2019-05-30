<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<jsp:include page="/WEB-INF/common/Head_top.jsp"></jsp:include>
	<script src="https://cdn.ckeditor.com/4.11.4/standard/ckeditor.js"></script>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/qaboard.css"/>
	<script src="${pageContext.request.contextPath}/resource/js/qaboard.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resource/js/qaboard-scroll.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resource/js/qaboard-img.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/resource/js/qaboard-ajax.js" type="text/javascript"></script>
<jsp:include page="/WEB-INF/common/Header_top.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Sidebar_Left.jsp"></jsp:include>
<!-- 콘텐츠 영역 시작-->
	<div class="row">
		<div class="col-6">

		</div>
		<div class="col-6">	
			<div class="text-right">
				<c:set var="write_name" value="${requestScope.result.nick_name }" />
				<c:set var="login_name" value="${nick_name }" />
				<c:choose>
					<c:when test="${write_name eq login_name}">


					</c:when>
				</c:choose>
			<c:set var="auth" value="${user_auth}"/>
	
			</div>
		</div>
	</div>
	<hr>

	<div class="row" id="qa_detail_top_row">
		<div class="col-1"></div>
			<div class="col-10">
				<div class="card">
					<div class="card-body">
						<div class="row" id="qa_detail_badge">
							<div class="col-6">

							</div>
							<div class="col-6 text-right">
								<span id="qa_detail_eye"><i class="fas fa-eye"></i>&nbsp;${requestScope.result.view_count} &nbsp;|&nbsp;</span>
								<span><i class="fas fa-comment-dots"></i></span>&nbsp;${requestScope.result.count}
							</div>
						</div>
						<div class="row"><h2>${requestScope.result.title}</h2></div>
					</div>
				</div>
			
			</div>
		<div class="col-1"></div>
	</div>
	

	<div class="row">
		<div class="col-1"></div>
		<div class="col-10 scroll">
			<div class="card">
				<div class="card-header" id="qa_detail_card_header">
					<i>작성자: ${requestScope.result.nick_name} | 작성일: ${requestScope.result.date_created} | 최근 수정일: ${requestScope.result.last_updated}</i>
				</div>
				  <div class="card-body" id="qa_detail_card">
				  	<br>
				    ${requestScope.result.content}
				  </div>
			</div>
		</div>

		<div class="col-1"></div>
	</div>
	<hr>
	<center>
						<a href="restoreOk.ad?id=${requestScope.result.id}"><button type="button" class="btn btn-black m-b-5 m-r-3" id="new_content_button">
						<i class="fas fa-trash-alt"></i>&nbsp;게시글 복원</button>
					</a>
	</center>
	

<!-- 콘텐츠영역 끝 -->
<jsp:include page="/WEB-INF/common/Sidebar_Right.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Footer_bottom.jsp"></jsp:include>