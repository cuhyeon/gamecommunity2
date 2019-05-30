<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>  
<jsp:include page="/WEB-INF/common/Head_top.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resource/css/tipboard.css" />
<jsp:include page="/WEB-INF/common/Header_top.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Sidebar_Left.jsp"></jsp:include>

<script type = "text/javascript">
function PageMove(page){
    location.href = "admin.ad?page="+page;
 }
</script>



<!-- 콘텐츠 영역 시작-->
<div class="row">
	<div class="col-6">
		<div class="text-left">
			<h4>
				<i class="fas fa-lg fa-fw m-r-10 fa-info"></i> <span
					class="text-inverse"><B>BOARD</B></span>
			</h4>
		</div>
	</div>

	<c:set var="auth" value="${user_auth}" />
	<c:choose>
		<c:when test="${!empty auth}">
			<div class="col-6">
				<div class="text-right">
				<a href="tipwrite.tb">
					<button type="button" class="btn btn-black m-b-5 m-r-3"
						id="new_content_button">
						<i class="far fa-edit"></i>&nbsp;새 글 쓰기
					</button></a>
				</div>
			</div>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
</div>


<!-- END page-header -->
			<!-- BEGIN card -->
			<div class="card ">
				<!-- BEGIN card-header -->
				<div class="card-header card-header-inverse">
					<h1 class="card-header-title">
					
					</h1>
					<div class="card-header-btn">
						<a href="#" data-toggle="card-expand" class="btn btn-success"><i class="fa fa-expand"></i></a>
						<a href="#" data-toggle="card-refresh" class="btn btn-warning"><i class="fa fa-redo"></i></a>
					</div>
				</div>
				<!-- END card-header -->
				<div class="table-responsive">
					<!-- BEGIN table -->
					<table id="datatables-default" class="table table-dark m-b-0">
						<thead>
							<tr style="text-align: center;">
								<th width="5%" class="no-sort"style="text-align: center;">#</th>
								<th width="55%">제목</th>
								<th width="15%">작성자</th>
								<th width="11%">작성일</th>
								<th width="9%">조회</th>
							</tr>
						</thead>
						<tbody>
							<c:set var="boardList" value="${requestScope.board}"/>
							<c:forEach var="tipboard" items="${boardList}" varStatus="status">
								  <tr id="tabletitle">
							  		<td >${tipboard.id}</td>
									<td><a href="tipboarddetail.tb?id=${tipboard.id}"><B>${tipboard.title}</B> </a>
									&nbsp;
									<span class="badge badge-primary badge-pill"></span></td>
									<td>${tipboard.nick_name}</td>
									<td>${tipboard.date_created}</td>
									<td>${tipboard.view_count}</td>
								  </tr>
							</c:forEach>
						</tbody>
					</table>
					<!-- END table -->
				</div>
			</div>
			<br>
			<nav>
		<ul class='pagination'>
			<c:if test="${requestScope.curPage>1 }">
			    <li class='page-item'>
			      <a class="page-link" href="tipboard.tb?cp=${requestScope.curPage-1}" aria-label="Previous">
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
		    			<li class='page-item'><a class='page-link' href='tipboard.tb?cp=${i}'>${i}</a></li>	
		    		</c:otherwise>
		    	</c:choose>
			</c:forEach>
			<c:if test="${requestScope.curPage<requestScope.endPageBlock}">
			    <li class="page-item">
			      <a class="page-link" href="tipboard.tb?cp=${requestScope.curPage+1}" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
		    </c:if>
		    
		</ul>
	</nav>
<!-- 콘텐츠영역 끝 -->
<jsp:include page="/WEB-INF/common/Sidebar_Right.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Footer_bottom.jsp"></jsp:include>