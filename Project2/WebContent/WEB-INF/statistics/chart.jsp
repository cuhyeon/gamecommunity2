<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/common/Head_top.jsp"></jsp:include>
<link
	type="text/css"
	href="${pageContext.request.contextPath}/resource/css/chart.css"
	rel="stylesheet" />
<jsp:include page="/WEB-INF/common/Header_top.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Sidebar_Left.jsp"></jsp:include>
<!-- 콘텐츠 영역 시작-->

<!-- json load -->
<script src="${pageContext.request.contextPath}/resource/js/chart.js"
	type="text/javascript"></script>

<script
	src="${pageContext.request.contextPath}/resource/js/chart_positive.js"
	type="text/javascript"></script>

<script
	src="${pageContext.request.contextPath}/resource/js/chart_price.js"
	type="text/javascript"></script>
	


<!-- clumn chart script -->
<script src="https://www.amcharts.com/lib/4/core.js"></script>
<script src="https://www.amcharts.com/lib/4/charts.js"></script>
<script src="https://www.amcharts.com/lib/4/themes/dark.js"></script>
<script src="https://www.amcharts.com/lib/4/themes/animated.js"></script>




<!-- BEGIN wizard -->
<div id="rootwizard" class="wizard wizard-full-width">
	<!-- BEGIN wizard-header -->
	<div class="wizard-header">
		<ul class="nav nav-pills">
			<li class="nav-item"><a href="#tab1" data-toggle="tab"
				class="nav-link active">1. 게임 리스트</a></li>
			<li class="nav-item"><a href="#tab2" data-toggle="tab"
				class="nav-link">2. 좋아요 순위</a></li>
			<li class="nav-item"><a href="#tab3" data-toggle="tab"
				class="nav-link">3. 플레이타임 순위</a></li>
		</ul>
	</div>
	<!-- END wizard-header -->
	<!-- BEGIN wizard-form -->
	<form action="#" method="POST" name="wizard_form">
		<!-- BEGIN wizard-content -->
		<div class="wizard-content tab-content">
			<!-- BEGIN tab-pane -->
			<div class="tab-pane fade show active" id="tab1">
				<!-- BEGIN card -->
				<div class="card">
					<!-- BEGIN card-header -->
					<div class="card-header card-header-inverse">
						<h4 class="card-header-title">게임 리스트</h4>
						<div class="card-header-btn">
						</div>
					</div>
					<!-- END card-header -->
					<!-- BEGIN card-body -->
					<div class="card-body">
						<table class="table table-hover m-b-0">
							<thead>
								<tr>
									<th>#</th>
									<th>이름</th>
									<th>개발사</th>
									<th>배포사</th>
									<th>좋아요</th>
									<th>싫어요</th>
									<th>플레이 타임</th>
									<th>가격</th>
								</tr>
							</thead>
							<tbody id="tbody">
							</tbody>
						</table>
						<br>
						<div>
							<ul class="pagination m-b-10 m-t-0">
								<li class="page-item" id='lp'><a href="#" class="page-link"
									id="btnpre"> <span>&laquo;</span>
								</a></li>
								<li class="page-item active" id='l1'><a href="#"
									class="page-link" id="btn1">1</a></li>
								<li class="page-item" id='l2'><a href="#" class="page-link"
									id="btn2">2</a></li>
								<li class="page-item" id='l3'><a href="#" class="page-link"
									id="btn3">3</a></li>
								<li class="page-item" id='l4'><a href="#" class="page-link"
									id="btn4">4</a></li>
								<li class="page-item" id='l5'><a href="#" class="page-link"
									id="btn5">5</a></li>
								<li class="page-item" id='ln'><a href="#" class="page-link"
									id="btnnext"> <span>&raquo;</span>
								</a></li>
							</ul>
						</div>
					</div>
					<!-- END card-body -->
				</div>
				<!-- END card -->
			</div>
			<!-- END tab-pane -->

			<!-- BEGIN tab-pane -->
			<div class="tab-pane fade" id="tab2">

				<div class="row">
					<div class="col-6">
						<div class="card">

							<div class="card-body"
								style="background-color: #30303d; color: #fff;" id="cardchart2">

							</div>
						</div>
					</div>
					<div class="col-6">
						<div class="card">

							<div class="card-body"
								style="background-color: #30303d; color: #fff;">
								<div id="chartdiv2"></div>
							</div>

						</div>
						<div class="card">

							<div class="card-body"
								style="background-color: #30303d; color: #fff;">
								
								
								<div id="chartdiv3"></div>
							</div>

						</div>
					</div>
				</div>

				<br>


				<br> <br>
				<!-- BEGIN card -->
				<div class="card">
					<!-- BEGIN card-header -->
					<div class="card-header card-header-inverse">
						<h4 class="card-header-title">좋아요 순위</h4>
						<div class="card-header-btn">
						</div>
					</div>
					<!-- END card-header -->
					
					<!-- BEGIN card-body -->
					<div class="card-body">
						<table class="table table-hover m-b-0">
							<thead>
								<tr>
									<th>#</th>
									<th>좋아요</th>
									<th>이름</th>
									<th>개발사</th>
									<th>가격</th>
								</tr>
							</thead>
							<tbody id="tbody2">

							</tbody>
						</table>
						<br>

					</div>
					<!-- END card-body -->
				</div>
				<!-- END card -->


			</div>
			<!-- END tab-pane -->

			<!-- BEGIN tab-pane -->
			<div class="tab-pane fade" id="tab3">
			
			<div class="row">
					<div class="col-6">
						<div class="card">

							<div class="card-body"
								style="background-color: #30303d; color: #fff;" id="cardchart3">

							</div>
						</div>
					</div>
					<div class="col-6">
						<div class="card">

							<div class="card-body"
								style="background-color: #30303d; color: #fff;">
								<div id="chartdiv5"></div>
							</div>

						</div>
						<div class="card">

							<div class="card-body"
								style="background-color: #30303d; color: #fff;">
								<div id="chartdiv6"></div>
							</div>

						</div>
					</div>
				</div>
			<br><br>
								<!-- BEGIN card -->
				<div class="card">
					<!-- BEGIN card-header -->
					<div class="card-header card-header-inverse">
						<h4 class="card-header-title">좋아요 순위</h4>
						<div class="card-header-btn">
						</div>
					</div>
					<!-- END card-header -->
					<!-- BEGIN card-body -->
					<div class="card-body">
						<table class="table table-hover m-b-0">
							<thead>
								<tr>
									<th>#</th>
									<th>플레이 타임</th>
									<th>이름</th>
									<th>개발사</th>
									<th>가격</th>
								</tr>
							</thead>
							<tbody id="tbody3">

							</tbody>
						</table>
						<br>

					</div>
					<!-- END card-body -->
				</div>
				<!-- END card -->
			</div>
			<!-- END tab-pane -->

		</div>
		<!-- END wizard-content -->
	</form>
	<!-- END wizard-form -->
</div>
<!-- END wizard -->



<!-- 콘텐츠영역 끝 -->

<jsp:include page="/WEB-INF/common/Sidebar_Right.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Footer_bottom.jsp"></jsp:include>