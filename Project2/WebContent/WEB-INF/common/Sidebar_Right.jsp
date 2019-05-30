<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script>
	$(function(){
		$('.boxlink').click(function(){
			$('#inverse-modal').modal('hide');
		});
		
		$('.boxlink2').click(function(){
			$('#inverse-modal2').modal('hide');
		});
		
		$('#email2, #phone_number2').keyup(function(){
			if( $('#phone_number2').val() != "" && $('#email2').val() != ""){
				console.log("트루");
				$('#fbtn').prop("disabled", false);
			} else {
				console.log("페일스");
				$('#fbtn').prop("disabled", true);
				
			}
		});
		
		// 이메일 중복 확인
		$('#fbtn').click(function () {
			
			$.ajax({ //비동기 !
				
				url : "pwfind.reg",
				data:{email2 : $('#email2').val(), phone_number : $('#phone_number2').val()}, 
				type : "get",
				success : function(data){
						console.log(data);
						if ( data == 'false'){
							$('#pwinfo').html('없는 정보 입니다');
						} else {
							$('#pwinfo').html('임시 비밀번호 : ' + data);
						}
						
					
				}
				
			}); //ajax 끝
		}); // 이메일 중복 끝
	});
</script>

<!-- BEGIN modal -->
<div class="modal modal-inverse fade" id="inverse-modal"
	class="inverse-modal">
	<div class="modal-dialog">
		<div class="modal-content">

			<form class="box1" action="login.reg" method="post">
				<h1>Login</h1>
				<br> <input type="text" id="email" name="email"
					placeholder="Username" maxlength = "30"> <input type="password"
					id="password" name="password" placeholder="Password" maxlength = "20"> <br>
				<input type="submit" value="Login"> <a
					href="#inverse-modal2" class="boxlink" data-toggle="modal">Forgot
					password?</a>

			</form>
		</div>
	</div>
</div>
<!-- END modal -->

<!-- BEGIN modal -->
<div class="modal modal-inverse fade" id="inverse-modal2"
	class="inverse-modal">
	<div class="modal-dialog">
		<div class="modal-content">

			<form class="box1" action="" method="post" id="pwchk">
				<h2>Search</h2>
				<br> <input type="text" id="email2" name="email2"
					placeholder="Email" maxlength = "30">
					 <input type="text" id="phone_number2"
					name="phone_number2" placeholder="Phone_number" maxlength = "11"> <br>
				<input type="button" value="Find" id="fbtn" disabled>
				<a href="#inverse-modal" class="boxlink2" data-toggle="modal">Go Login</a>
				<div id = "pwinfo">
				</div>
			</form>
		</div>
	</div>
</div>
<!-- END modal -->

<!-- LOGOUT modal -->

<div class="modal modal-inverse fade" id="logout-modal" tabindex="-1"
	role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">로그아웃</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>정말로 로그아웃 하시겠습니까?</p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
				&nbsp;&nbsp;
				<form action="logout.reg" method="POST" name="logout">
					<button type="submit" class="btn btn-dark">로그아웃</button>
				</form>
			</div>
		</div>
	</div>
</div>
<!-- LOGOUT modal -->



</div>
<!-- 콘텐츠 컬럼 col-10 끝 -->


<div class="col-2" id="scroll_Banner_col">
	<!-- 콘텐츠 컬럼 col-2 시작 -->
	<div id="scroll_Banner">
		<!-- BEGIN card -->
		<div class="card mb-3">
			<!-- BEGIN card-header -->
			<div class="card-header card-header-inverse">
				<h4 class="card-header-title">
					<i class="fab fa-earlybirds"></i>&nbsp;게이머 필수템!
				</h4>
			</div>
			<!-- END card-header -->
			<!-- BEGIN widget-list -->
			<div class="widget-list">
				<a target="_blank" href="https://search.shopping.naver.com/detail/detail.nhn?nv_mid=15571010856&adId=nad-a001-02-000000044788466&channel=
				nshop.npla&query=MXVERTICAL&NaPm=ct%3Djvp2mnoo%7Cci%3D0yq0000LTXHqIGPCkLj5%7Ctr%3Dpla%7Chk%3De82c2aa9a9b53cf4d1d600a8f80ca9cf2f293040"
				 class="widget-list-item">

					<div class="widget-list-media">
						<img src="resource/img/mouse.jpg" style="width:36px; height:36px">
					</div>
					<div class="widget-list-content">
						<p>Logitech</p>
						<h4>MX VERTICAL<br><small><i class="fas fa-won-sign"></i>&nbsp;119,000</small></h4>
						<ul class="widget-rating">
							<li class="active"><i class="fa fa-star"></i></li>
							<li class="active"><i class="fa fa-star"></i></li>
							<li class="active"><i class="fa fa-star"></i></li>
							<li class="active"><i class="fa fa-star"></i></li>
							<li class="active"><i class="fa fa-star"></i></li>
							<li class="text">(1,133)</li>
						</ul>
					</div>
				</a> <a target="_blank" href="https://search.shopping.naver.com/detail/detail.nhn?nv_mid=
				10128900544&cat_id=50002933&frm=NVSHATC&query=K70+LUX+%EC%A0%81%EC%B6%95&NaPm=
				ct%3Djvp2ljk8%7Cci%3Dcf2e7bb6d73a7c4bba9a24fa57344855f5c52513%7Ctr%3Dslsl%7Csn%3D95694%7Chk%3Dcb7b24b8c0e654a3004f8d6cf21251201e51ecf0"
				 class="widget-list-item">
					<div class="widget-list-media">
						<img src="resource/img/keyboard.jpg" style="width:36px; height:36px">
					</div>
					<div class="widget-list-content">
						<p>CORSAIR</p>
						<h4>K70 LUX 적축<br><small><i class="fas fa-won-sign"></i>&nbsp;174,990</small></h4>
						<ul class="widget-rating">
							<li class="active"><i class="fa fa-star"></i></li>
							<li class="active"><i class="fa fa-star"></i></li>
							<li class="active"><i class="fa fa-star"></i></li>
							<li class="active"><i class="fa fa-star"></i></li>
							<li><i class="fa fa-star"></i></li>
							<li class="text">(1,674)</li>
						</ul>
					</div>
				</a> <a target="_blank" href="https://search.shopping.naver.com/detail/detail.nhn?nv_mid=
				17126005203&cat_id=50000153&frm=NVSHATC&query=ASUS+%ED%9C%B4%EB%8C%80%EC%9A%A9%EB%AA%A8%EB%8B%88%ED%84%B0&NaPm=
				ct%3Djvp2ka1c%7Cci%3Dfbcde48dc3a0241bd33fa338eb452a6615e907ca%7Ctr%3Dslsl%7Csn%3D95694%7Chk%3D705cfe6131f62e8c41fc3d029cef2c2ee5e301d2" 
				class="widget-list-item">
					<div class="widget-list-media">
						<img src="resource/img/asus.jpg" style="width:36px; height:36px">
					</div>
					<div class="widget-list-content">
						<p>ASUS</p>
						<h4>ZenScreen MB16AP<br><small><i class="fas fa-won-sign"></i>&nbsp;439,000</small></h4>
						<ul class="widget-rating">
							<li class="active"><i class="fa fa-star"></i></li>
							<li class="active"><i class="fa fa-star"></i></li>
							<li class="active"><i class="fa fa-star"></i></li>
							<li class="active"><i class="fa fa-star"></i></li>
							<li><i class="fa fa-star"></i></li>
							<li class="text">(76)</li>
						</ul>
					</div>

				</a> <a target="_blank" href="https://search.shopping.naver.com/detail/detail.nhn?nv_mid=18571615807&cat_id=50000153&frm=NVSCMOD&query=C49RG90&NaPm=
				ct%3Djvp2iluo%7Cci%3Dbf8abe7a2e74ac54dcef78de6bacc432f042930c%7Ctr%3Dsls%7Csn%3D95694%7Chk%3D22a0b0ef1facb11bcc170f672839834432f88dd2"
				 class="widget-list-item">
					<div class="widget-list-media">
						<img src="resource/img/monitor.jpg" style="width:36px; height:36px">
					</div>
					<div class="widget-list-content">
						<p>삼성</p>
						<h4>C49RG90<br><small><i class="fas fa-won-sign"></i>&nbsp;1,327,770</small></h4>
						<ul class="widget-rating">
							<li class="active"><i class="fa fa-star"></i></li>
							<li class="active"><i class="fa fa-star"></i></li>
							<li class="active"><i class="fa fa-star"></i></li>
							<li class="active"><i class="fa fa-star"></i></li>
							<li class="active"><i class="fa fa-star"></i></li>
							<li class="text">(35)</li>
						</ul>
					</div>
				</a> <a target="_blank" href="https://www.apple.com/kr/shop/buy-mac/imac-pro/3.2ghz-1tb#" class="widget-list-item">
					<div class="widget-list-media">
						<img src="resource/img/imacpro.jpg" style="width:36px; height:36px">
					</div>
					<div class="widget-list-content">
						<p>APPLE</p>
						<h4>iMac CTO 5K FULL option<br><small><i class="fas fa-won-sign"></i>&nbsp;16,731,500</small></h4>
						<ul class="widget-rating">
							<li class="active"><i class="fa fa-star"></i></li>
							<li class="active"><i class="fa fa-star"></i></li>
							<li class="active"><i class="fa fa-star"></i></li>
							<li class="active"><i class="fa fa-star"></i></li>
							<li><i class="fa fa-star"></i></li>
							<li class="text">(3)</li>
						</ul>
					</div>
				</a>
			</div>
			<!-- END widget-list -->
		</div>
		<!-- END card -->


	</div>
	<!-- 스크롤 -->
</div>
<!-- 콘텐츠 컬럼 col-2 끝 -->

</div>
<!--콘텐츠 row 끝  -->

</div>


<div id="footer" class="app-footer ">Copyright &copy;GROUP4 Corp.
	All Rights Reserved.</div>
<a href="#" data-click="scroll-top" class="btn-scroll-top fade"><i
	class="fa fa-arrow-up"></i></a>
<!-- 위로 가기 버튼 -->
</div>
<!-- Header_top.jsp에  맨위 app 사이드바 닫는용-->