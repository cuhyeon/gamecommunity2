<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<jsp:include page="/WEB-INF/common/Head_top.jsp"></jsp:include>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/qaboard.css"/>

<jsp:include page="/WEB-INF/common/Header_top.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Sidebar_Left.jsp"></jsp:include>
<!-- 콘텐츠 영역 시작-->
<div class="row">
		<div class="text-left">
			<h3><i class="fas fa-sun"></i>&nbsp;게시물 모아보기<small>(${requestScope.totalListCount})</small></h3>
		</div>
</div>

<div class="row" id="tboard_nav_main"><!-- 2번째 row 시작 -->
	<div align="right">
		<ul class="nav nav-pills bg-light">
			<li class="nav-item"><a href="#tab1" data-toggle="tab"
				class="nav-link active">최신글순</a></li>
			<li class="nav-item"><a href="#tab2" data-toggle="tab"
				class="nav-link">추천수순</a></li>
			<li class="nav-item"><a href="#tab3" data-toggle="tab"
				class="nav-link">조회수순</a></li>
		</ul>
	</div>
		
</div> <!-- 2번째 row 끝  -->

<div class="row" id="tboard_nav">
	<table class="table table-hover m-b-0">
		<thead>
			<tr>
				<th class="text-center">#</th>
				<th class="text-center">제목</th>
				<th class="text-center">조회수</th>
				<th class="text-center">&nbsp;추천수</th>
				<th class="text-center" colspan="2">글쓴이</th>
				<th class="text-center">발행일</th>
			</tr>
		</thead>
		<tbody>
	<c:set var="resultList" value="${requestScope.result}"/>
	<c:forEach var="result" items="${resultList}" varStatus="status">
		  <tr>
	  		<td class="text-center">${result.id}</td>
			
			<td><b>[${result.board_list_id}]</b>&nbsp;&nbsp;
				<c:if test="${result.board_list_id eq '질문게시판' }"><a href="qadetail.qb?id=${result.id}">${result.title}</a></c:if>
				<c:if test="${result.board_list_id eq '이미지게시판' }"><a href="imagedetail.ib?id=${result.id}">${result.title}</a></c:if>
				<c:if test="${result.board_list_id eq '공략게시판' }"><a href="tipboarddetail.tb?id=${result.id}">${result.title}</a></c:if>
				<c:if test="${result.board_list_id eq '동영상게시판' }"><a href="videoread.vb?id=${result.id}">${result.title}</a></c:if>
				<c:if test="${result.board_list_id eq '뉴스게시판' }"><a href="newsDetail.nb?id=${result.id}">${result.title}</a></c:if>
				<c:if test="${result.reply_count>0}">
					&nbsp;<span class="badge badge-primary badge-pill">${result.reply_count}</span>
				</c:if>
			</td>
			<td class="text-center"><i class="fas fa-eye"></i>&nbsp;${result.view_count}</td>
			<td class="text-center">
				<c:if test="${result.recommand>0}"><i class="far fa-thumbs-up"></i></c:if>
				<c:if test="${result.recommand<0}"><i class="far fa-thumbs-down"></i></c:if>
				<c:if test="${result.recommand==0}"><i class="far fa-hand-rock"></i></c:if>
				&nbsp;${result.recommand}
			</td>
			<td class="text-right"><img src="${result.user_image_path }" id="qa_list_user_image_path" alt=" "/></td>
			<td class="text-left">${result.nick_name}</td>
			<td class="text-center">${result.date_created}</td>
		  </tr>
	</c:forEach>	
		</tbody>
	</table>
</div>
<hr>
	<nav>
		<ul class='pagination justify-content-center'>
			<c:if test="${requestScope.startPageBlock>1}">
			    <li class='page-item'>
			      <a class="page-link" href="tboard.total?cp=1" aria-label="Previous">
			        <span aria-hidden="true">1</span>
			      </a>
			    </li>
		    </c:if>
			<c:if test="${requestScope.curPage>1 }">
			    <li class='page-item'>
			      <a class="page-link" href="tboard.total?cp=${requestScope.curPage-1}" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
			      </a>
			    </li>
		    </c:if>
		    <c:forEach var="i" begin="${requestScope.startPageBlock}" end="${requestScope.endPageBlock}" step="1">
		    	<c:choose>
		    		<c:when test="${requestScope.curPage eq i }">
		    			<li class='page-item active'><a class='page-link' href='#'>${i}</a></li>
		    		</c:when>
		    		<c:otherwise>
		    			<li class='page-item'><a class='page-link' href='tboard.total?cp=${i}'>${i}</a></li>	
		    		</c:otherwise>
		    	</c:choose>
			</c:forEach>
			<c:if test="${requestScope.curPage<requestScope.totalPage}">
			    <li class="page-item">
			      <a class="page-link" href="tboard.total?cp=${requestScope.curPage+1}" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
		    </c:if>
		    <c:if test="${requestScope.curPage < requestScope.totalPage && requestScope.totalPage-requestScope.pageSize>0}">
			    <li class="page-item">
			      <a class="page-link" href="tboard.total?cp=${requestScope.totalPage}" aria-label="Next">
			        <span aria-hidden="true">${requestScope.totalPage}</span>
			      </a>
			    </li>
		    </c:if>
		    
		</ul>
	</nav>



<!-- 콘텐츠영역 끝 -->
<jsp:include page="/WEB-INF/common/Sidebar_Right.jsp"></jsp:include>
<script src="${pageContext.request.contextPath}/resource/js/qaboard-page.js" type="text/javascript"></script>
<jsp:include page="/WEB-INF/common/Footer_bottom.jsp"></jsp:include>