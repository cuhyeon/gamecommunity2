<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/common/Head_top.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resource/css/newsdetail.css" />
	
	<script src="${pageContext.request.contextPath}/resource/js/newsdetail.js" type="text/javascript"></script>

	
<jsp:include page="/WEB-INF/common/Header_top.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Sidebar_Left.jsp"></jsp:include>
<!-- 콘텐츠 영역 시작-->
<c:set var="board" value="${requestScope.boardId}" />
<div class="card m-b-15">
	<!-- BEGIN card -->
	<div class="card m-b-15">
		<!-- BEGIN card-header -->
		<div class="card-header card-header-inverse">
			<h4 class="card-header-title">GAME NEWS</h4>
		</div>
		<div class="card-body" id="title">
			<a href="#">${boardId.title}</a>
		</div>
		<hr>
		<div class="card-body" id="board-nav">${boardId.nick_name}
			| ${boardId.date_created} | 조회 ${boardId.view_count} | 댓글<span class="reply-count">${boardId.count}</span>
			<span class="reply-count2"></span></div>

		<div class="card-body" id="board-content">
			<c:choose>
				<c:when test="${boardId.file_path=='uploads/'}"></c:when>
				<c:otherwise>
					<img src="${boardId.file_path}" id="head-image">
				</c:otherwise>
			</c:choose>
			<br> ${boardId.content}
		</div>


	</div>

</div>
<p class="button-active">
	&nbsp;&nbsp;&nbsp;
	<c:if test="${sessionScope.nick_name == boardId.nick_name || sessionScope.user_auth==3}">
	<a id="anker" href="editboard.nb?id=${boardId.id}">
	<button type="button" class="btn btn-primary active" id="btn">
		수정
	</button>
	</a>
	<a id="anker" href="deleteboard.nb?id=${ boardId.id}">
	<button type="button" class="btn btn-primary active" id="btn">
		삭제
	</button>
	</a>
	</c:if>
	<a id="anker" onclick="event.preventDefault(); history.back();">
	<button type="button" class="btn btn-primary active" id="btn">
		목록
	</button>
	</a>
</p>
<div class="card m-b-15">
	<div class="card-body" id="board-content">
		<div class="reply-title">댓글</div>
		<hr>
		<div id="reply">
		<c:choose>
			<c:when test="${empty requestScope.dto2}"><p class="empty">작성된 댓글이 없습니다.</p></c:when>
			
			<c:otherwise>
			
				<c:forEach var = "reply" items="${requestScope.dto2}">
					<p id= "reply-title"><b>${reply.r_nick_name}</b>&nbsp;<i>${reply.date_created}</i><br></p>
					<p id="reply-content">${reply.reply_content} <c:if test="${sessionScope.nick_name == reply.r_nick_name}"><p>
																				 <a class ="update" href="deletereply.nb?id=${reply.id}&boardid=${board.id}&page=${page}">[삭제]</a></p></c:if>
					</p>
					
				</c:forEach>
			</c:otherwise>
			
		</c:choose>
		</div>
	</div>
</div>
		<div class="card m-b-15">
			<div class="card-body">
<c:choose>
	<c:when test="${sessionScope.nick_name == null}">댓글쓰기 권한이 없습니다.</c:when>
	<c:otherwise>

			
				<b class="nick_name" >${sessionScope.nick_name}</b>&nbsp;<input type="text" class="content" name="content" placeholder="댓글..." maxlength="200">
				<button type="submit" id="btn" class="reply-create">댓글작성</button>
			
	</c:otherwise>
</c:choose>

			</div>
		</div>
<!-- 콘텐츠영역 끝 -->
<jsp:include page="/WEB-INF/common/Sidebar_Right.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Footer_bottom.jsp"></jsp:include>