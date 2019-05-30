<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/common/Head_top.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resource/css/video.css" />
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script
	src="${pageContext.request.contextPath}/resource/js/videoajax.js"
	type="text/javascript"></script>
<jsp:include page="/WEB-INF/common/Header_top.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Sidebar_Left.jsp"></jsp:include>



<div class="row">
	<div class="col-6">
		<div class="text-left">
			<h3>
				<i class="fas fa-lg fa-fw m-r-10 fa-video"></i><span
					class="text-inverse">Play of the Game video </span>
			</h3>

		</div>
	</div>
	<div class="col-4">
		<div style="color: #d9d9d9;">
			<div id="reply_id">#${requestScope.result.id}</div>
		</div>
	</div>
	<div class="col-2">
		<div class="text-right">
			<a href="video.vb"><button type="button"
					class="btn btn-black m-b-5 m-r-3" id="new_content_button">
					<i class="far fa-edit"></i>&nbsp;목록
				</button></a>
		</div>
	</div>
</div>
<hr>



<table>
	<tr height="250px">
		<td colspan="2">
			<div style="text-align: center;">
				<c:choose>
					<c:when test="${requestScope.result.file_path=='upload/'}"></c:when>
					<c:otherwise>
						<video width="790" height="480" controls>
							<source src="${requestScope.result.file_path}" type="video/mp4"
								style="display: block; margin: 0px auto;">
						</video>
					</c:otherwise>
				</c:choose>
			</div>
		</td>
	</tr>
	<tr>
		<td colspan="2"><br>
			<div class="row">

				&ensp;
				<div style="font-family: '맑은고딕'; font-size: 22px;">
					${requestScope.result.title}&nbsp;</div>

			</div> <br>
			<div style="font-family: '맑은고딕'; font-size: 14px; color: #505050;">
				조회수 ${requestScope.result.view_count} 회
				<div style="text-align: right;">
					<i class="fas fa-lg fa-fw m-r-10 fa-thumbs-up"></i> <span
						class="text-inverse">${requestScope.result.vote_count}</span>
					&ensp;
				</div>
			</div>
			<hr></td>
	</tr>

	<tr>
		<td width="12%"><img
			src='${pageContext.request.contextPath}/upload/img_avatar2.png'
			style="border-radius: 100px;" width="80" height="80" alt=' '></td>
		<td width="80%" rowspan="2">
			<div style="font-family: '맑은고딕'; font-size: 15px;">
				<b>${requestScope.result.nick_name}</b>
			</div>
			<div style="font-family: '맑은고딕'; font-size: 13px; color: #505050;">
				게시일 : ${requestScope.result.date_created}</div> <br>
			<div>${requestScope.result.content}</div>
		</td>
	</tr>
	<tr>
		<td></td>
	</tr>
</table>




<hr>
<div class="col-md-6 col-sm-6 col-xs-6 m-b-10 text-ellipsis">
	<i class="fas fa-lg fa-fw m-r-10 fa-angle-down"></i> <span
		class="text-inverse"><b>코멘트 [${requestScope.result.count}] 개</b> </span>
</div>
<br>

<c:set var="resultList" value="${requestScope.resultList}" />

<!-- 댓글 시작 -->



<c:forEach var="qa" items="${resultList}" varStatus="status">

	<div class="row">
		<div class="col-1">
			<div class="text-left">

				<div>
					<img src='${qa.u_user_image_path}' style="border-radius: 100px;"
						width="61" height="61" alt=' '>
				</div>
			</div>
		</div>
		<div class="col-11">
			<div class="row">
				<div style="font-family: '맑은고딕'; font-size: 15px;">
					&emsp;${qa.r_nick_name}</div>
				<div style="font-family: '맑은고딕'; font-size: 13px; color: #505050;">
					&emsp;${qa.date_created}</div>
			</div>
			<br>
			<div>&emsp;${qa.reply_content}</div>
		</div>
	</div>
	<br>

</c:forEach>





<div id='reply_paste'>

</div>





<c:set var="auth" value="${user_auth}" />
<c:choose>
	<c:when test="${!empty auth}">
		<tr>
			<td colspan="2">
				<div class="row">
					<div class="text-left">
						<div class="form-group">
							<textarea class="form-control" name="comment" id="comment"
								rows="4" cols="95" placeholder="댓글 내용을 작성하세요" maxlength = "200"></textarea>
						</div>
					</div>
					<div class="text-right">
						<button type="submit" class="btn btn-default m-b-5 m-r-3"
							style="height: 96px; width: 80px;" id="enterbtn">등록</button>
					</div>
				</div>
			</td>

		</tr>
	</c:when>
	<c:otherwise>
		<tr>
			<td colspan="2">
				<div class="row">
					<div class="text-left">
						<div class="form-group">
							<textarea class="form-control" name="comment" id="comment"
								rows="4" cols="95" placeholder="비회원은 댓글을 작성하실 수 없습니다" disabled></textarea>
						</div>
					</div>
					<div class="text-right">
						<button type="button" class="btn btn-default m-b-5 m-r-3"
							style="height: 96px; width: 80px;">등록</button>
					</div>
				</div>
			</td>

		</tr>
	</c:otherwise>
</c:choose>


<c:set var="auth" value="${user_auth}" />
<c:choose>
	<c:when test="${!empty auth}">
		<div class="row" id="write-button">
			<div class="col-5">
				<div class="text-left">
					<a href="videodelete.vb?id=${requestScope.result.id}">
						<button type="button" class="btn btn-secondary" id="cancel-btn">삭제</button>
					</a>
				</div>
			</div>
			<div class="col-7">
				<div class="text-right">
					<a href="videoedit.vb?id=${requestScope.result.id}">
						<button type="button" class="btn btn-primary" id="enter-btn">수정</button>
					</a>
				</div>
			</div>
			<div class="col-1"></div>

		</div>
	</c:when>
	<c:otherwise>
	</c:otherwise>
</c:choose>




<!-- 콘텐츠영역 끝 -->
<jsp:include page="/WEB-INF/common/Sidebar_Right.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Footer_bottom.jsp"></jsp:include>