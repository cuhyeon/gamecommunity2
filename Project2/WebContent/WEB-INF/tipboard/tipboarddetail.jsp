<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/WEB-INF/common/Head_top.jsp"></jsp:include>
<script src="https://cdn.ckeditor.com/4.11.4/standard/ckeditor.js"></script>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resource/css/tipboard.css" />
<script
	src="${pageContext.request.contextPath}/resource/js/tipboard_reply.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/resource/js/tipboard_ajax.js"
	type="text/javascript"></script>
<jsp:include page="/WEB-INF/common/Header_top.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Sidebar_Left.jsp"></jsp:include>

<!-- Board Detail Use Variable Start-->
<c:set var="board" value="${requestScope.board}" />
<!-- ..result -->
<c:set var="write_name" value="${requestScope.board.nick_name}" />
<c:set var="login_name" value="${nick_name}" />
<c:set var="auth" value="${user_auth}" />
<!-- Board Detail Use Variable End-->


<!-- Header Start -->
<div class="row">
	<div class="col-5">
		<div class="text-left">
			<h5>
				<i class="fas fa-lg fa-fw m-r-10 fa-info"></i> <span
					class="text-inverse"><B>BOARD</B></span>
			</h5>
		</div>
	</div>

	<div class="col-7">
		<div class="text-right" id="write-button">
			<!-- List Start -->
			<a href="tipboard.tb"><button type="button"
					class="btn btn-black m-b-5 m-r-3" id="new_content_button">
					<i class="far fa-edit"></i>&nbsp;목록
				</button></a>
			<!-- List End-->

	
					<!-- Edit Start -->
					<c:if test="${sessionScope.nick_name == requestScope.board.nick_name || sessionScope.user_auth==3}">
					<a href="tipboardedit.tb?id=${board.id}"><button type="button"
							class="btn btn-black m-b-5 m-r-3" id="enter-btn">
							<i class="far fa-edit"></i>수정
						</button></a>
					<!-- Edit End-->


					<!-- Delete Start -->
					<a href="tipdeleteok.tb?id=${board.id}"><button type="button"
							class="btn btn-black m-b-5 m-r-3" id="cancel-btn">
							<i class="far fa-edit"></i>삭제
						</button></a>
					<!-- Delete End-->
					</c:if>
		
		</div>
	</div>
</div>
<h6></h6>
<!-- Header End -->


<!-- body Start -->
<div class="col-1"></div>
<div class="row">
	<div class="col-12">

		<div class="card ">
			<div class="card-header card-header-inverse">
				<div class="col-12">
					<h4>${requestScope.board.title}</h4>
				</div>
			</div>
		</div>


		<div class="card">
			<div class="card-body" id="detail_card">
				<div class="row">
					<div class="col-8">
						<i>작성자: ${requestScope.board.nick_name}</i>
					</div>
					<div class="col-2">
						<i>작성일: ${requestScope.board.date_created}</i>
					</div>
					<div class="col-2">
						<i>수정일: ${requestScope.board.last_updated}</i>
					</div>

				</div>
				<hr>
				${requestScope.board.content}
			</div>
			<br>
		</div>
		<h1></h1>
	</div>
	<div class="col-1"></div>
</div>
<!-- @@@@@@@@@@@@@@@@@@@@@ reply Start 11 @@@@@@@@@@@@@@@@@@@@ -->
<div class="card ">
	<div class="card-header card-header-inverse">
		<div class="card-body text-dark">
			<div style="color: white" id="">
				<div class="row">
					<div class="col-10">
						<h4>&nbsp;Comment ${requestScope.board.count}</h4>
						<!-- Comment -->
					</div>
					<div class="col-2">
						<H4>${nick_name}</H4>
					</div>
				</div>
			</div>
		</div>
	</div>

	<h4></h4>

	<div class="col-12">
		<!-- reply form Start -->
		<div class="article-reply">
			<s_rp>
			<div class="reply-wrap">
				<s_rp_input_form>
				<div class="reply-form">
					<fieldset>
						<c:choose>
							<c:when test="${!empty user_auth }">
								<legend class="sr-only">댓글쓰기 폼</legend>
								<dl class="reply-write">
									<dd>
										<textarea class="textarea1" id="editor1" name="editor1"
											placeholder="여러분의 소중한 댓글을 입력해주세요" title="댓글 내용 이력"></textarea>
									</dd>
								</dl>
								<button type="submit" class="reply-btn" id="enterbtn">
									Comment Send</button>
							</c:when>

							<c:otherwise>
								<legend class="sr-only">댓글쓰기 폼</legend>
								<dl class="reply-write">
									<dd>
										<textarea name="" id="" placeholder="로그인을 하면 댓글 작성이 가능합니다"
											title="댓글 내용 이력" readonly></textarea>
									</dd>
								</dl>
								<button type="submit" class="reply-btn" id="enterbtn">
									Comment Send</button>
							</c:otherwise>
						</c:choose>
					</fieldset>
				</div>
				</s_rp_input_form>
			</div>
			</s_rp>
		</div>
		<!-- reply form End -->


		<!-- Reply Use Variable Start-->
		<c:set var="boardList" value="${requestScope.boardList}" />
		<!-- Reply Use Variable End-->

		<!-- reply print Start -->
		<div id="reply_paste" class="col-12">
			<c:forEach var="tip" items="${boardList}" varStatus="status">
				<div class="card-body text-dark">
					<div class="row">
						<div class="col-10" id="reply_paste">
							&nbsp;&nbsp;&nbsp;<i><b>작성자:</b>${tip.r_nick_name}</i>
							&nbsp;&nbsp;&nbsp; <i></i>
						</div>
						<div class="col-2">
							&nbsp;&nbsp;<i><b>작성일:${tip.date_created}</b></i>
						</div>
					</div>
					<div class="row">
						<div class="col-12">
							<br>${tip.reply_content}
						</div>
					</div>
					<!-- reply print End -->
					<hr>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
<span class="badge badge-secondary" id="reply_id">#${requestScope.board.id}</span>
&nbsp;

<!--##################### Reply End ##################### -->

<!-- @@@@@@@@@@@@@@@@@@@@@ reply Start22 @@@@@@@@@@@@@@@@@@@@ -->

<!-- reply End -->

<!-- 콘텐츠영역 끝 -->
<jsp:include page="/WEB-INF/common/Sidebar_Right.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Footer_bottom.jsp"></jsp:include>