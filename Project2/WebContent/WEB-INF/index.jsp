<%@page import="org.masterjung.util.GetImagePath"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<jsp:include page="/WEB-INF/common/Head_top.jsp"></jsp:include>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/main-index.css"/>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<script src="${pageContext.request.contextPath}/resource/js/amchart-index.js" type="text/javascript"></script>


<jsp:include page="/WEB-INF/common/Header_top.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Sidebar_Left.jsp"></jsp:include>
<!-- 콘텐츠 영역 시작-->

<c:set var="boardList" value="${requestScope.boardList}"/>
<c:set var="boardListView" value="${requestScope.boardListView}"/>
<c:set var="newsList" value="${requestScope.newsList}"/>
<c:set var="qaboardList" value="${requestScope.qaboardList}"/>
<c:set var="tipsList" value="${requestScope.tipsList}"/>

	<div class="row" id="index_row7">
	<div class="col-12">
		<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
		  <div class="carousel-inner w-100 text-center">
		  <c:choose>
		  <c:when test="${empty nick_name}">
			    <div class="carousel-item active">
			    <a href="registerform.reg">
			      <img src="resource/img/high3.jpg" class="d-block w-100 h-100" alt=" " id="index_row7_1">
			    </a>
			    </div>
			    <div class="carousel-item">
				    <a href="video.vb">
				      <img src=resource/img/high.jpg class="d-block w-100 h-100" alt=" " id="index_row7_1">
				    </a>  
			    </div>
			</c:when>
			<c:otherwise>
				<div class="carousel-item active">
				    <a href="video.vb">
				      <img src=resource/img/high.jpg class="d-block w-100 h-100" alt=" " id="index_row7_1">
				    </a>  
			    </div>
			</c:otherwise>    
		    </c:choose>
		    <div class="carousel-item">
		    <a href="gochart.cht">
		      <img src="resource/img/high2.jpg" class="d-block w-100 h-100" alt=" " id="index_row7_1">
		    </a>
		    </div>
		  </div>
		  <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
		    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
		    <span class="sr-only">Previous</span>
		  </a>
		  <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
		    <span class="carousel-control-next-icon" aria-hidden="true"></span>
		    <span class="sr-only">Next</span>
		  </a>
		</div>
	</div>
	</div>

	<div class="row" id="index_row4"> <!--index_row4 start  -->
 	<div class="col-6">
 		<h5><i class="fas fa-video"></i>&nbsp;&nbsp;인기 게임 영상</h5>&nbsp;
 	</div>
 	<div class="col-6 text-right">
 		<a href="video.vb"><button type="button" class="btn btn-dark btn-xs">더보기</button></a>
 	</div>
 </div> <!-- index_row4_end -->
 	
  <div class="row" id="index_row3"><!-- index_row3 start  -->	
 	<div class="col-3">
	 	<div class="embed-responsive embed-responsive-16by9 m-b-2">
			<iframe class="embed-responsive-item"
				src="https://www.youtube.com/embed/ptHK5zFH2BU"></iframe>	
		</div>
	</div>
	<div class="col-3">
	 	<div class="embed-responsive embed-responsive-16by9 m-b-2">
			<iframe class="embed-responsive-item"
				src="https://www.youtube.com/embed/Ux0tfIPnRyc"></iframe>
		</div>
	</div>
	<div class="col-3">
	 	<div class="embed-responsive embed-responsive-16by9 m-b-2">
			<iframe class="embed-responsive-item"
				src="https://www.youtube.com/embed/bSKZbLpZHUc"></iframe>
		</div>
	</div>
	<div class="col-3">
	 	<div class="embed-responsive embed-responsive-16by9 m-b-2">
			<iframe class="embed-responsive-item"
				src="https://www.youtube.com/embed/bA41tpkdw5U"></iframe>
		</div>
	</div>
 </div> <!-- index_row3 end  -->
	

 <div class='row' id='index_row1'> <!-- index_row1 start -->
	  <div class="col-4 ">
	    <div class="card">
	      <div class="card-body">
	        <h5 class="card-title text-center">전체 최신글</h5>
	        <hr>
	        <c:forEach var="result" items="${boardList}" varStatus="status">
	        	<p class="card-text"><i class="fas fa-pencil-alt"></i>&nbsp;<b>[${result.board_list_id}]</b>&nbsp; 
	        	<c:if test="${result.board_list_id eq '질문게시판' }"><a href="qadetail.qb?id=${result.id}">${result.title}</a></c:if>
				<c:if test="${result.board_list_id eq '이미지게시판' }"><a href="imagedetail.ib?id=${result.id}">${result.title}</a></c:if>
				<c:if test="${result.board_list_id eq '공략게시판' }"><a href="tipboarddetail.tb?id=${result.id}">${result.title}</a></c:if>
				<c:if test="${result.board_list_id eq '동영상게시판' }"><a href="videoread.vb?id=${result.id}">${result.title}</a></c:if>
				<c:if test="${result.board_list_id eq '뉴스게시판' }"><a href="newsDetail.nb?id=${result.id}">${result.title}</a></c:if>
	        	</p>
	        </c:forEach>  
	        <div class="text-center"><a href="tboard.total" class="btn btn-primary" id="index_btn">통합 게시판 바로가기</a></div>
	      </div>
	    </div>
	  </div>
	  <div class="col-4">
	    <div class="card">
	      <div class="card-body">
	        <h5 class="card-title text-center">커뮤니티 인기글</h5>
	        <hr>
	        <c:forEach var="result" items="${boardListView}" varStatus="status">
	        	<p class="card-text"><i class="fas fa-eye"></i>&nbsp;<b>[${result.board_list_id}]</b>&nbsp;
	        	 <c:if test="${result.board_list_id eq '질문게시판' }"><a href="qadetail.qb?id=${result.id}">${result.title}</a></c:if>
				<c:if test="${result.board_list_id eq '이미지게시판' }"><a href="imagedetail.ib?id=${result.id}">${result.title}</a></c:if>
				<c:if test="${result.board_list_id eq '공략게시판' }"><a href="tipboarddetail.tb?id=${result.id}">${result.title}</a></c:if>
				<c:if test="${result.board_list_id eq '동영상게시판' }"><a href="videoread.vb?id=${result.id}">${result.title}</a></c:if>
				<c:if test="${result.board_list_id eq '뉴스게시판' }"><a href="newsDetail.nb?id=${result.id}">${result.title}</a></c:if> 
	        	 &nbsp;<small><i class="fas fa-glasses"></i>&nbsp;${result.view_count}</small></p>
	        </c:forEach> 
	         <div class="text-center">
	        	<a href="#" class="btn btn-primary" id="index_btn">베스트 글 바로가기</a>
	        </div>
	      </div>
	    </div>
	  </div>
	  <div class="col-4">
	    <div class="card">
	      <div class="card-body">
	        <h5 class="card-title text-center">회원가입 현황</h5>
	        <hr>
	        <canvas id="myChart" style="height:250px"></canvas>
	        
	        <!-- <div id="chartdiv"></div> -->
	        <p class="card-text text-center"><small>(매일 00:00시 기준으로 업데이트 됩니다)</small></p>
	        
	      </div>
	    </div>
	  </div>
	
 </div> <!-- index_row1 end -->
 
 
 
<div class="row" id="index_row8">
	<div class="col-12">
		<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
		  <div class="carousel-inner w-100 text-center" id="index_row8_2">
			    <div class="carousel-item active">
			    <a href="https://store.steampowered.com/" target="_blank">
			      <img src="resource/img/steam11.jpg" class="d-block w-100 h-100" alt=" " id="index_row8_1">
			    </a>
			    </div>
			    <div class="carousel-item">
				    <a href="http://bitcamp.co.kr/index.php" target="_blank">
				      <img src=resource/img/low.jpg class="d-block w-100 h-100" alt=" " id="index_row8_1">
				    </a>  
			    </div>
		    <div class="carousel-item">
		    	<a href="https://okky.kr/" target="_blank">
			      <img src="resource/img/okky.jpg" class="d-block w-100 h-100" alt=" " id="index_row8_1">
			    </a>
		    </div>
		  </div>
		</div>
	</div>
</div>
 
 
<div class="row" id="index_row5"> <!-- index_row5 start -->
	<div class="col-6">
	    <div class="card">
	      <div class="card-body">
	        <h5 class="card-title text-center">최신 뉴스</h5>
	        <hr>
	        <c:forEach var="result" items="${newsList}" varStatus="status">
	        	<p class="card-text"><i class="fas fa-tv"></i>&nbsp;<b>[${result.board_list_id}]</b>&nbsp; 
	        	<c:if test="${result.board_list_id eq '뉴스게시판' }"><a href="newsDetail.nb?id=${result.id}">${result.title}</a></c:if>
	        	</p>
	        </c:forEach>  
	        <div class="text-center"><a href="newboard.nb" class="btn btn-primary" id="index_btn">뉴스 더보기</a></div>
	      </div>
	    </div>
	  </div>
	  
	  	<div class="col-6">
	    <div class="card">
	      <div class="card-body">
	        <h5 class="card-title text-center">인기 Q&A</h5>
	        <hr>
	        <c:forEach var="result" items="${qaboardList}" varStatus="status">
	        	<p class="card-text"><i class="fas fa-reply"></i>&nbsp;<b>[${result.board_list_id}]</b>&nbsp; 
	        	<c:if test="${result.board_list_id eq '질문게시판' }"><a href="qadetail.qb?id=${result.id}">${result.title}</a></c:if>
	        	&nbsp;<small><i class="far fa-comment-dots"></i>&nbsp;${result.reply_count}</small></p>
	        </c:forEach>  
	        <div class="text-center"><a href="qaboard.qb" class="btn btn-primary" id="index_btn">Q&A 더보기</a></div>
	      </div>
	    </div>
	  </div>
</div><!-- index_row5 end -->

<div class="row" id="index_row10">
	 <div class="col-6">
 		<h5><i class="fas fa-images"></i>&nbsp;&nbsp;인기 스크린샷</h5>&nbsp;
 	</div>
 	<div class="col-6 text-right">
 		<a href="imageboard.ib"><button type="button" class="btn btn-dark btn-xs">더보기</button></a>
 	</div>
</div>

<div class="row" id="index_row9">
	<div class="col-12">
		<div class="post">
			<div class="post-content">
				<ul class="img-list">
					<li class="main"><a href="resource/img/steam.jpg" target="_blank"><img src="resource/img/steam.jpg"></a></li>
					<li class="main"><a href="resource/img/steam1.jpg" target="_blank"><img src="resource/img/steam1.jpg"></a></li>
					<li class="main"><a href="resource/img/steam5.jpg" target="_blank"><img src="resource/img/steam5.jpg"></a></li>
					<li class="main"><a href="resource/img/steam3.jpg" target="_blank"><img src="resource/img/steam3.jpg"></a></li>
				</ul>
			</div>
		</div>
	</div>
</div>

<div class="row" id="index_row11"> <!-- index_row5 start -->
	<div class="col-6">
		<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
		  <div class="carousel-inner w-100 text-center" id="index_row11_2">
			    <div class="carousel-item active">
			    <a href="https://studysearch.co.kr/" target="_blank">
			      <img src="resource/img/study.jpg" class="d-block w-100 h-100" alt=" " id="index_row11_1">
			    </a>
			    </div>
			    <div class="carousel-item">
				    <a href="http://bitcamp.co.kr/index.php" target="_blank">
				      <img src="resource/img/old.jpg" class="d-block w-100 h-100" alt=" " id="index_row11_1">
				    </a>  
			    </div>
		    <div class="carousel-item">
		    	<a href="http://bitcamp.co.kr/index.php" target="_blank">
			      <img src="resource/img/bitcamp.jpg" class="d-block w-100 h-100" alt=" " id="index_row11_1">
			    </a>
		    </div>
		  </div>
		</div>
	</div>
	<div class="col-6">
	    <div class="card">
	      <div class="card-body">
	        <h5 class="card-title text-center">공략 게시판</h5>
	        <hr>
	        <c:forEach var="result" items="${tipsList}" varStatus="status">
	        	<p class="card-text"><i class="fas fa-street-view"></i>&nbsp;<b>[${result.board_list_id}]</b>&nbsp; 
	        	<c:if test="${result.board_list_id eq '공략게시판' }"><a href="tipboarddetail.tb?id=${result.id}">${result.title}</a></c:if>
	        	&nbsp;&nbsp;<small><i class="fas fa-long-arrow-alt-right"></i>&nbsp;[${result.nick_name}]&nbsp;님이 작성</small></p>
	        </c:forEach>  
	        <div class="text-center"><a href="tipboard.tb" class="btn btn-primary" id="index_btn">공략 더보기</a></div>
	      </div>
	    </div>
	  </div>
	  
</div><!-- index_row5 end -->
	
<!-- 콘텐츠영역 끝 -->
<jsp:include page="/WEB-INF/common/Sidebar_Right.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Footer_bottom.jsp"></jsp:include>