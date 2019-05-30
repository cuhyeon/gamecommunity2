<%@page import="org.masterjung.dto.BoardDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/common/Head_top.jsp"></jsp:include>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resource/css/videolist.css" />
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript">
	function PageMove(page) {
		location.href = "video.vb?page=" + page;
	}
</script>
<jsp:include page="/WEB-INF/common/Header_top.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Sidebar_Left.jsp"></jsp:include>


<c:set var="boardlist" value="${requestScope.videolist}" />


<div class="row">
	<div class="col-6">
		<div class="text-left">
			<h3>
				<i class="fas fa-lg fa-fw m-r-10 fa-video"></i><span
					class="text-inverse">Play of the Game video</span>
			</h3>
		</div>
	</div>
	<div class="col-6">
		<div class="text-right">
		
			<c:set var="auth" value="${user_auth}" />
			<c:choose>
				<c:when test="${!empty auth}">
					<button type="button" onclick="location.href='videowrite.vb'"
						class="btn btn-black m-b-5 m-r-3" id="new_content_button">
						<i class="far fa-edit"></i>&nbsp;새 글 쓰기
					</button>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</div>








<hr>

<table class="table m-b-0">
	<tbody>
		<c:forEach var="board" items="${boardlist}" varStatus="status">
			<tr style="cursor: pointer;"
			onmouseover="this.style.backgroundColor='#e9e9e9'" onmouseout="this.style.backgroundColor='#ddd'"
				onclick="location.href='videoread.vb?id=${board.id}'">

				<td class="type2" width="40%" >
					<%-- 										 <video width="350" height="220" muted>
                           				 <source src="${pageContext.request.contextPath}/resource/img/오리사.mp4" type="video/mp4" style="display: block; margin: 0px auto;"
										 id = "vdimg"></video> --%> <c:choose>
						<c:when test="${board.file_path=='upload/'}"></c:when>
						<c:otherwise>
							<video width="100%" height="250" muted>
								<source src="${board.file_path}" type="video/mp4"
									style="display: block; margin: 0px auto;">
							</video>
						</c:otherwise>
					</c:choose>

				</td>

				<td width="50%">

					<div style="font-family: '맑은고딕'; font-size: 30px;">
						${board.title}</div>
					<div style="color: #505050; font-size: 13px;">
						${board.nick_name} • 조회수 ${board.view_count} 회 • 추천수
						${board.vote_count} 회 • ${board.date_created}</div>
						<br><div>${board.content}</div>
					<p id="cutcontenthere"></p>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
				<div class="sorter">
					<ul class="pagination m-b-10 m-t-0">
						<c:if test="${paging.pageNo!=1}">
							<li class="page-item" id='lp'><a class="page-link"
								id="btnpre" href="javascript:PageMove(${paging.prevPageNo})">이전</a></li>
						</c:if>
						<c:forEach var="i" begin="${paging.startPageNo}"
							end="${paging.endPageNo}" step="1">
							<c:choose>
								<c:when test="${i eq paging.pageNo}">
									<li class="page-item active" id='l${i}'><a a
										class="page-link" id="btn${i}"
										href="javascript:PageMove(${i})">${i}</a></li>
								</c:when>
								<c:otherwise>
									<li class="page-item" id='l${i}'><a class="page-link"
										id="btn${i}" href="javascript:PageMove(${i})">${i}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<c:if test="${paging.pageNo!=paging.endPageNo}">
							<li class="page-item" id='ln'><a class="page-link"
								id="btnnext" href="javascript:PageMove(${paging.nextPageNo})">다음</a></li>
						</c:if>
					</ul>
				</div>

<!-- END #content -->


<jsp:include page="/WEB-INF/common/Sidebar_Right.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Footer_bottom.jsp"></jsp:include>