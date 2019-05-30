<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- BEGIN #sidebar -->
<sidebar id="sidebar" class="app-sidebar"> <!-- BEGIN scrollbar -->
<div data-scrollbar="true" data-height="100%">

	<!-- BEGIN nav -->
	<ul class="nav">
		<li class="nav-profile"><c:set var="auth" value="${user_auth}" />

			<c:choose>
				<c:when test="${!empty auth}">
					<a href="memberinfo.reg">
						<div class="profile-img">
							<img src="${user_image_path}" />
						</div>
						<div class="profile-info">
							<h4>${nick_name}<small>(${user_name})</small>
							</h4>
							<p>
								<c:choose>
									<c:when test="${user_auth == 3}">관리자</c:when>
									<c:when test="${user_auth == 2}">기자</c:when>
									<c:when test="${user_auth == 1}">일반회원</c:when>
								</c:choose>
							</p>
						</div>
					</a>
				</c:when>
				<c:otherwise>
					<div class="profile-img">
						<img src="resource/img/masterjung.jpg" />
					</div>
					<div class="profile-info">
						<h4>
							게스트<small>님</small>
						</h4>
						<p>환영합니다</p>
					</div>
				</c:otherwise>
			</c:choose></li>
		<li class="nav-divider"></li>
		<li class="nav-header">카테고리</li>
		<li id="a1"><a href="index.reg"> <span
				class="nav-icon"><i class="fas fa-home bg-gradient-orange-red"></i></span> <span
				class="nav-text">Home</span>
		</a></li>

		<li id="a2"><a href="gochart.cht"> <span class="nav-icon"><i
					class="fa fa-chart-bar bg-gradient-purple text-white"></i></span> <span
				class="nav-text">게임통계</span>
		</a></li>
		
		<li id="a3"><a href="newboard.nb"> <span class="nav-icon"><i
					class="fas fa-newspaper bg-gradient-cyan-blue-to-top"></i></span> <span
				class="nav-text">게임 뉴스</span>
		</a></li>
		
		<li id="a6"><a href="qaboard.qb"> <span class="nav-icon"><i
					class="far fa-comment bg-gradient-blue"></i> </span> <span
				class="nav-text"> 게임 Q&A</span>
		</a></li>
		<!-- 네비추가 -->

		
		<li  id="a5"><a href="tipboard.tb"> <span class="nav-icon">
		<i class="fas fa-hand-spock bg-gradient-muted"></i></span> <span class="nav-text">
		자유 게시판</span>
		</a></li>

		<li  id="a4"><a href="tboard.total"> <span class="nav-icon">
		<i class="fas fa-fire-alt bg-gradient-green text-white"></i></span> <span class="nav-text">
			게시물 모아보기</span>
		</a></li>


		<li  id="a7"><a href="imageboard.ib"> <span class="nav-icon"><i
					class="fas fa-file-image bg-orange"></i></span> <span
				class="nav-text">게임 이미지 모음</span>
		</a></li>

        <li  id="a8"><a href="video.vb"> <span class="nav-icon"><i
                    class="fab fa-youtube bg-red"></i></span> <span class="nav-text">
                    플레이 영상 모음</span>
        </a></li>
        <c:if test="${user_auth==3}">
		<!-- <li><a href="admin.ad"> <span class="nav-icon"><i
					class="fas fa-cog bg-gradient-black"></i></span> <span
				class="nav-text">[관리자] 회원목록</span>
		</a></li>
				<li><a href="admin.ad"> <span class="nav-icon"><i
					class="fas fa-cog bg-gradient-black"></i></span> <span
				class="nav-text">[관리자] 탈퇴회원목록</span>
		</a></li>
				<li><a href="admin.ad"> <span class="nav-icon"><i
					class="fas fa-cog bg-gradient-black"></i></span> <span
				class="nav-text">[관리자] 삭제 게시물목록</span>
		</a></li> -->
		
		<li class="has-sub">
						<a href="#">
							<span class="nav-icon"><i class="fa fa-cog bg-gradient-orange text-white"></i></span>
							<span class="nav-text">관리자 메뉴</span> 
							<span class="nav-caret"><b class="caret"></b></span>
						</a>
						<ul class="nav-submenu">

							<li><a href="admin.ad"><span class="nav-text">회원 목록</span></a></li>
							<li><a href="withdraw.ad"><span class="nav-text">탈퇴 회원 목록</span></a></li>
							<li><a href="deleteBoardList.ad"><span class="nav-text">삭제 게시물 목록</span></a></li>

						</ul>
					</li>
		</c:if>
		<li class="nav-divider"></li>
		<li class="nav-copyright">Copyright &copy;GROUP4 Corp. All Rights
			Reserved.</li>
	</ul>
	<!-- END nav -->
</div>
<!-- END scrollbar --> </sidebar>
<!-- END #sidebar -->

<div id="content" class="app-content margin-left0">

	<div class="row">
		<!-- 콘텐츠 row 시작  -->
		<div class="col-10" id="common_content_area">
			<!-- 콘텐츠 컬럼 col-10 시작 -->