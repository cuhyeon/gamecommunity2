<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/common/Head_top.jsp"></jsp:include>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resource/css/newsmain.css" />
<script type="text/javascript">
	function PageMove(page) {
		location.href = "newboard.nb?page=" + page;
	}
</script>
<jsp:include page="/WEB-INF/common/Header_top.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Sidebar_Left.jsp"></jsp:include>
<!-- 콘텐츠 영역 시작-->
<div class="card m-b-15" id="wrapper">
	<!-- BEGIN card -->
	<div class="card m-b-15" id="wrapper">
		<!-- BEGIN card-header -->
		<div class="card-header card-header-inverse" id="wrapper">
			<h4 class="card-header-title" id="wrapper">@ GAME NEWS</h4>

		</div>
		<!-- END card-header -->
		<!-- BEGIN card-body -->
		<div class="card-body" id="wrapper">
			<div class="table-responsive">
				<table class="table">
					<tbody>
						<c:forEach var="boardList" items="${requestScope.boardList}">
							<tr style="cursor: pointer;"
			onmouseover="this.style.backgroundColor='#e9e9e9'" onmouseout="this.style.backgroundColor='white'"
				onclick="location.href='newsDetail.nb?id=${boardList.id}&page=${paging.pageNo}'">
								<td class="list"><img src="${boardList.file_path}" id="image"></td>
								<td><a id = "headline">${boardList.title}</a><b
										class="count"> ${boardList.reply_count}</b>
								 <br>${boardList.date_created} | 조회 ${boardList.view_count}</td>

							</tr>
						</c:forEach>

					</tbody>

				</table>
				<hr>
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

				<p class="button-active">
					&nbsp;&nbsp;&nbsp;
					<c:if
						test="${sessionScope.user_auth == 2 || sessionScope.user_auth == 3}">
						<a id="anker" href="moveWrite.nb">
							<button type="button" class="btn btn-primary active" id="btn">
								글쓰기</button>
						</a>
					</c:if>
				</p>
			</div>
		</div>
	</div>
</div>

<!-- 콘텐츠영역 끝 -->
<jsp:include page="/WEB-INF/common/Sidebar_Right.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Footer_bottom.jsp"></jsp:include>