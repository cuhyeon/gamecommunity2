<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/common/Head_top.jsp"></jsp:include>

<script
	src="${pageContext.request.contextPath}/resource/js/register.js"
	type="text/javascript"></script>
	
<jsp:include page="/WEB-INF/common/Header_top.jsp"></jsp:include>

<div id="content" class="app-content0 bg-dark">

	<div class="row">
		<!-- 콘텐츠 row 시작  -->
		<div class="col-10">
			<!-- 콘텐츠 컬럼 col-10 시작 -->
			<!-- 콘텐츠 영역 시작-->

			<div id="content" class="app-content">
				<div class="register">
					<!-- BEGIN register-content -->
					<div class="register-content1">
						<!-- BEGIN register-brand -->
						<div class="register-brand">
							<a href="#"><b>4</b>Team</a>
						</div>
						<!-- END register-brand -->
						<h3 class="m-b-20">
							<span>회원가입</span>
						</h3>
						<p class="m-b-20">
							회원가입 완료 후 작성하신 <b>이메일</b>과 <b>비밀번호</b>로 로그인이 가능합니다
						</p>
						<!-- BEGIN register-form -->
						<form action="register.reg" method="POST" name="register_form">
							<!-- BEGIN row -->
							<div class="row row-space-20">
								<!-- BEGIN col12 -->
								<div class="col-md-8">
									<div class="form-group">
										<label>이름 <span class="text-danger" >*</span></label> <input
											type="text" class="form-control" value="" id="user_name"
											name="user_name"  maxlength = "15"/>
									</div>
									<div class="form-group">
										<label>닉네임 <span class="text-danger" id="nickinfo">*</span></label> <input
											type="text" class="form-control" value="" id="nick_name"
											name="nick_name" maxlength = "30" />
									</div>
									<div class="form-group">
										<label>휴대폰 번호 <span class="text-danger">*</span></label>
										<input type="text" class="form-control" value=""
											id="phone_number" name="phone_number"  maxlength = "11"/>
									</div>
									<div class="form-group">
										<label>이메일 <span class="text-danger" id="emailinfo">*</span></label>
										<input type="text" class="form-control" value="" id="email" 
											name="email"  maxlength = "50"/>
									</div>
									<div class="form-group">
										<label>비밀번호 <span class="text-danger" >*</span></label> <input
											type="password" class="form-control" value="" id="password"
											name="password" maxlength = "20" />
									</div>
									<div class="form-group">
										<label>비밀번호 확인 <span class="text-danger" id="passwordinfo">*</span></label> <input
											type="password" class="form-control" value="" id="password2"
											name="password2"  maxlength = "20"/>
									</div>
								</div>
								<!-- END col-12 -->
							</div>
							<!-- END row -->
							<div class="m-b-30">
								<div class="checkbox-inline">
									<input type="checkbox" id="login-remember-me" class = "ischecked"value="2">
									<label for="login-remember-me">
										가입하고 싶으면 동의하세요!
									</label>
								</div>
							</div>
							<div class="d-flex align-items-center">
								<button type="submit"
									class="btn btn-primary width-200 btn-rounded" id="subbtn" disabled>회원가입</button>
								<span class="m-l-20 text-white-transparent-5">
								 	Already have an Admin ID? &nbsp; <a href="page_login.html">로그인</a>
								</span>
							</div>
						</form>
						<!-- END register-form -->
					</div>
					<!-- END register-content -->
				</div>
				<!-- END register -->
			</div>

			<!-- 콘텐츠영역 끝 -->
		</div>
		<!-- 콘텐츠 컬럼 col-10 끝 -->
	</div>
</div>
<!-- 콘텐츠 컬럼 col-2 끝 -->

</div>
<!--콘텐츠 row 끝  -->


<div id="footer" class="app-footer0 bw">Copyright &copy;GROUP4
	Corp. All Rights Reserved.</div>
<a href="#" data-click="scroll-top" class="btn-scroll-top fade"><i
	class="fa fa-arrow-up"></i></a>
<!-- 위로 가기 버튼 -->
</div>
<!-- Header_top.jsp에  맨위 app 사이드바 닫는용-->

<!-- ================== BEGIN BASE JS ================== -->
<script src="${pageContext.request.contextPath}/resource/js/app.min.js"
	type="text/javascript"></script>

<!-- ================== END BASE JS ================== -->
</body>
</html>