<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<jsp:include page="/WEB-INF/common/Head_top.jsp"></jsp:include>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/qaboard.css"/>

<jsp:include page="/WEB-INF/common/Header_top.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Sidebar_Left.jsp"></jsp:include>
<!-- 콘텐츠 영역 시작-->
<div class="row">
	<div class="col-6">
		<div class="text-left">
			<h3><i class="fab fa-steam"></i>&nbsp;GAME Q&A<small>(${requestScope.totalListCount})</small></h3>
		</div>
	</div>
	<div class="col-6">	
		<div class="text-right">
		<c:set var="auth" value="${user_auth}"/>
			<c:choose>
				<c:when test="${!empty auth}">
					<a href="qawrite.qb"><button type="button" class="btn btn-black m-b-5 m-r-3" id="new_content_button">
					<i class="far fa-edit"></i>&nbsp;새 글 쓰기</button></a>
				</c:when>
				<c:otherwise>
					<a href="#inverse-modal" data-toggle="modal">
					<button type="button" class="btn btn-secondary m-b-5 m-r-3" 
						id="new_content_button">
					<i class="far fa-edit"></i>&nbsp;새 글 쓰기</button></a>
				</c:otherwise>
			</c:choose>	
		</div>
	</div>
</div>

<br>
<div class="row"><!-- 2번째 row 시작 -->
	<div class="col-1">
		<select class="form-control" id='qa_board_select' name='selected'>
			<option value=10>10</option>
			<option value=15>15</option>
			<option value=20>20</option>
			<option value=30>30</option>
		</select>

	</div>
	<div class="col-5"></div>
	<div class="col-6">
		<div class="input-group mb-3">
		  <input type="text" class="form-control" placeholder="검색어" aria-label="검색어" aria-describedby="basic-addon2">
		  <div class="input-group-append">
		    <button class="btn btn-outline-secondary" type="button"><i class="fas fa-search"></i></button>

		  </div>
		</div>
	</div>
		
</div> <!-- 2번째 row 끝  -->

<div class="row">
	<table class="table table-hover m-b-0">
		<colgroup>
			<col width="5%">
			<col width="51%">
			<col width="10%">
			<col width="10%">
			<col width="5%">
			<col width="5%">
			<col width="14%">
		</colgroup>
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
	<c:forEach var="qaboard" items="${resultList}" varStatus="status">
		  <tr>
	  		<td class="text-center">${qaboard.id}</td>
			
			<td><a href="qadetail.qb?id=${qaboard.id}">${qaboard.title}</a> 
				<c:if test="${qaboard.reply_count>0}">
					&nbsp;<span class="badge badge-primary badge-pill">${qaboard.reply_count}</span>
				</c:if>
			</td>
			<td class="text-center"><i class="fas fa-eye"></i>&nbsp;${qaboard.view_count}</td>
			<td class="text-center">
				<c:if test="${qaboard.recommand>0}"><i class="far fa-thumbs-up"></i></c:if>
				<c:if test="${qaboard.recommand<0}"><i class="far fa-thumbs-down"></i></c:if>
				<c:if test="${qaboard.recommand==0}"><i class="far fa-hand-rock"></i></c:if>
				&nbsp;${qaboard.recommand}
			</td>
			<td class="text-right"><img src="${qaboard.user_image_path }" id="qa_list_user_image_path" alt=" "/></td>
			<td class="text-left">${qaboard.nick_name}</td>
			<td class="text-center">${qaboard.date_created}</td>
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
			      <a class="page-link" href="qaboard.qb?cp=1" aria-label="Previous">
			        <span aria-hidden="true">1</span>
			      </a>
			    </li>
		    </c:if>
			<c:if test="${requestScope.curPage>1 }">
			    <li class='page-item'>
			      <a class="page-link" href="qaboard.qb?cp=${requestScope.curPage-1}" aria-label="Previous">
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
		    			<li class='page-item'><a class='page-link' href='qaboard.qb?cp=${i}'>${i}</a></li>	
		    		</c:otherwise>
		    	</c:choose>
			</c:forEach>
			<c:if test="${requestScope.curPage<requestScope.endPageBlock}">
			    <li class="page-item">
			      <a class="page-link" href="qaboard.qb?cp=${requestScope.curPage+1}" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
			      </a>
			    </li>
		    </c:if>
		    <c:if test="${requestScope.curPage < requestScope.totalPage}">
			    <li class="page-item">
			      <a class="page-link" href="qaboard.qb?cp=${requestScope.totalPage}" aria-label="Next">
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