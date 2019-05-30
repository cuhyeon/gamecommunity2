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
	<script src="${pageContext.request.contextPath}/resource/js/qaboard-detail.js" type="text/javascript"></script>
<jsp:include page="/WEB-INF/common/Header_top.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Sidebar_Left.jsp"></jsp:include>
<!-- 콘텐츠 영역 시작-->

	<div class="row">
		<div class="col-6">
			<div class="text-left">
				<h3><i class="fab fa-steam"></i>&nbsp;GAME Q&A</h3>
			</div>
		</div>
		<div class="col-6">	
			<div class="text-right">
				<c:set var="write_name" value="${requestScope.result.nick_name}" />
				<c:set var="login_name" value="${nick_name}" />
				<c:choose>
					<c:when test="${write_name eq login_name}">
					<a href="qadelteok.qb?id=${requestScope.result.id}"><button type="button" class="btn btn-black m-b-5 m-r-3" id="new_content_button">
						<i class="fas fa-trash-alt"></i>&nbsp;게시글 삭제</button>
					</a>
					<a href="qawritedit.qb?id=${requestScope.result.id}"><button type="button" class="btn btn-black m-b-5 m-r-3" id="new_content_button">
						<i class="fas fa-cog"></i>&nbsp;게시글 수정</button>
					</a>
					</c:when>
				</c:choose>
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

	<div class="row text-center">
		<div class="col-12">
			<c:choose>
			<c:when test="${empty login_name}">
				<div class="alert alert-primary" role="alert">
				   <a href="#inverse-modal" data-toggle="modal" class="alert-link">로그인</a>하신 후, <b>새 글 쓰기</b>와 <b>추천</b>기능을 사용할 수 있습니다
				</div>
			</c:when>
			<c:otherwise>
				<hr>
			</c:otherwise>
			</c:choose>
		</div>
	</div>

	<div class="row" id="qa_detail_top_row">
		<div class="col-1"></div>
			<div class="col-10">
				<div class="card">
					<div class="card-body">
						<div class="row" id="qa_detail_badge">
							<div class="col-6">
								<span class="badge badge-secondary" id="reply_id">#${requestScope.result.id}</span>&nbsp;
								<span class="badge badge-pill badge-primary"><i class="fab fa-steam"></i>&nbsp;Stream Q&A</span>
							</div>
							<div class="col-6 text-right">
								<span id="qa_detail_eye"><i class="fas fa-eye"></i>&nbsp;${requestScope.result.view_count} &nbsp;|&nbsp;</span>
								<span><i class="fas fa-comment-dots"></i></span>&nbsp;<span id="total_reply1">${requestScope.result.count}</span>
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
		<div class="col-9 scroll">
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
		<div class="col-1" id="qa_detail_vote">
			<div class="card text-center bg-light mb-3">
				<div class="card-body" id="qa_detail_vote_body">
					<c:if test="${!empty login_name}">
						<h3 id='like'><small class="text-muted"><i class="fas fa-chevron-up"></i></small></h3>
					</c:if>
						<h3 id='recommand_content'>${requestScope.result.recommend}</h3>
					<c:if test="${!empty login_name}">
						<h3 id='dislike'><small class="text-muted"><i class="fas fa-chevron-down"></i></small></h3>
					</c:if>
				</div>
			</div>
		</div>
		<div class="col-1"></div>
	</div>
	<hr>
	
	<div class="row">
		<div class="col-1"></div>
   		<div class="col-9" id='reply_paste'>	
   			<div class="card">
   				<div class="card-header">총&nbsp;<div class="text-primary" id="total_reply2">${requestScope.result.count}</div>개의 답변이 있습니다</div>
			</div> 			 
		
			<!-- 카드 헤더 끝 -->
		<c:set var="resultList" value="${requestScope.resultList}"/>
		<c:choose>
			<c:when test="${requestScope.result.count ne 0}">
			<c:forEach var="qa" items="${resultList}" varStatus="status">
			<div id='qa_boarder'></div>
				<div class="card">
					<div class="row no-gutters" id='qa_no_gutters'>
						<div class='col-2 clearfix' id='qa_detail_reply_div' >
					    	<img src='${qa.u_user_image_path}' class='card-img rounded-circle border' alt=' ' id='qa_detail_reply_img'>
					    </div>
					    <div class='col-10' id='qa_detail_reply_div_content'>
					    	<div class='text-left'>&nbsp;<i class="far fa-user"></i> ${qa.r_nick_name}<c:if test="${qa.r_nick_name eq login_name}">&nbsp;
					    		<span class="qaboard_writer"><b>[글쓴이]</b></span></c:if>
					    	</div>
					    	<div class='text-left'>&nbsp;<i class="far fa-clock"></i> ${qa.date_created}</div>
					    	<c:if test="${qa.r_nick_name eq login_name}">
					    		&nbsp;
						    	<button type='button' class='btn btn-primary btn-xs btn-modify' id='${qa.id}'>수정</button>
								<button type='button' onclick='location.href="qadeletereply.qb?replyid=${qa.id}&boardid=${requestScope.result.id}"' class='btn btn-secondary btn-xs btn-delete' id='${qa.id}'>삭제</button>
							</c:if>
					    </div>
					</div>
					<div id='qa_boarder'></div>
					<div class='row'>
						<div class="card-body text-dark">
							${qa.reply_content}
						</div>
					</div>
				</div>
				
			</c:forEach>
			</c:when>
			<c:otherwise>
			<div id='qa_boarder' id='reply_paste'></div>
				<div class="card border-dark mb-3" id="reply_index">
					<div class="card-body text-dark">첫번째 답변에 주인공이 되보세요!</div>
				</div>
				
			</c:otherwise>
		</c:choose>
   		</div>
	   	<div class="col-2"></div>
	</div>
	
	<div class="row">
		<div class="col-1"></div>
   		<div class="col-9">	
  
			<c:choose>
				<c:when test="${!empty user_auth }">
		 			<!-- 카드 헤더 시작 -->
		   			<div class="card" id='qa_answer'>
		   				<div class="card-header"><b>${nick_name}</b>님 답변을 작성할 수 있습니다</div>
					</div> 			 
					<!-- 카드 헤더 끝 -->
						<textarea name="editor1" id="editor1"></textarea>
						<div class="text-right">
							<button type="submit" class="btn btn-primary" id="enterbtn">답변 등록</button>
						</div>
				</c:when>
				<c:otherwise>
					<div class="card" id='qa_answer'>
		   				<div class="card-header"><a href="#inverse-modal" data-toggle="modal"><b>로그인</b></a>을 하시면 답변을 작성할 수 있습니다.</div>
					</div>
				</c:otherwise>	
			</c:choose>
   		</div>
	   	<div class="col-2"></div>
	</div>

<!-- 콘텐츠영역 끝 -->
<jsp:include page="/WEB-INF/common/Sidebar_Right.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Footer_bottom.jsp"></jsp:include>