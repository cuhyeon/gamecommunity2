<%@page import="org.masterjung.util.GetImagePath"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/common/Head_top.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resource/css/admindeleteboardlist.css" />
<jsp:include page="/WEB-INF/common/Header_top.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Sidebar_Left.jsp"></jsp:include>
<!-- 콘텐츠 영역 시작-->
<script>
$(function(){
	
})
</script>


<!-- BEGIN card -->
				<div class="card">
					<!-- BEGIN card-header -->
					<div class="card-header card-header-inverse">
						<h4 class="card-header-title">삭제 게시물  ${requestScope.deleteboardCount} 개</h4>
						<div class="card-header-btn">
							<a href="#" data-toggle="card-expand" class="btn btn-success"><i
								class="fa fa-expand"></i></a> <a href="#" data-toggle="card-refresh"
								class="btn btn-warning"><i class="fa fa-redo"></i></a>
						</div>
					</div>
					<!-- END card-header -->

					<!-- BEGIN card-body -->
					<div class="card-body">
						<table class="table table-hover m-b-0">
							<thead>
							<tr>
								<th>카테고리</th>
								<th>번호</th>
								<th>제목</th>
								<th>글쓴이</th>
								<th>관리</th>
							</tr>
						</thead>
						<tbody>
							 <c:forEach var="deleteboardList" items="${requestScope.deleteboardList}">
								<tr>
									<td><b><c:choose>
												<c:when test ="${deleteboardList.board_list_id == 1}">[질문게시판]</c:when>
												<c:when test ="${deleteboardList.board_list_id == 2}">[공략게시판]</c:when>
												<c:when test ="${deleteboardList.board_list_id == 3}">[뉴스게시판]</c:when>
												<c:when test ="${deleteboardList.board_list_id == 4}">[사진게시판]</c:when>
												<c:when test ="${deleteboardList.board_list_id == 5}">[영상게시판]</c:when>
												<c:when test ="${deleteboardList.board_list_id == 6}">[정보게시판]</c:when>
											</c:choose></b></td>
									<td>${deleteboardList.id}</td>
									<td>${deleteboardList.title}</td>
									<td>${deleteboardList.nick_name}</td>
									<td><a id = "anker" href="restore.ad?id=${deleteboardList.id}">[복원]</a></td>
								</tr>
							</c:forEach> 
						</table>
						<br>
						
						<nav>
		<ul class='pagination'>
			<c:if test="${requestScope.curPage>1 }">
			    <li class='page-item'>
			      <a class="page-link" href="deleteBoardList.ad?cp=${requestScope.curPage-1}" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
		    </c:if>
		    <c:forEach var="i" begin="1" end="${requestScope.endPageBlock}" step="1">
		    	<c:choose>
		    		<c:when test="${requestScope.curPage eq i }">
		    			<li class='page-item active'><a class='page-link' href='#'>${i}</a></li>
		    		</c:when>
		    		<c:otherwise>
		    			<li class='page-item'><a class='page-link' href='deleteBoardList.ad?cp=${i}'>${i}</a></li>	
		    		</c:otherwise>
		    	</c:choose>
			</c:forEach>
			<c:if test="${requestScope.curPage < requestScope.endPageBlock}">
			    <li class="page-item">
			      <a class="page-link" href="deleteBoardList.ad?cp=${requestScope.curPage+1}" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
		    </c:if>
		    
		</ul>
	</nav>
				
					</div>
					<!-- END card-body -->
				</div>
				<!-- END card -->

	
<!-- 콘텐츠영역 끝 -->
<jsp:include page="/WEB-INF/common/Sidebar_Right.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Footer_bottom.jsp"></jsp:include>