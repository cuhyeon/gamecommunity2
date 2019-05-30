<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="/WEB-INF/common/Head_top.jsp"></jsp:include>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/resource/js/imagewrite.js" type="text/javascript"></script>

<link rel="stylesheet" href="./resource/css/imageboard.css">

<jsp:include page="/WEB-INF/common/Header_top.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Sidebar_Left.jsp"></jsp:include>


<div class="cutimagewriterow"> <!-- 폭 줄이기 -->

	<div class="card m-b-15">
		<div class="card m-b-15">	
			
			<!-- 카드헤더시작 -->
			<div class="card-header card-header-inverse">
				<h4 class="card-header-title">Image Details</h4>				
					<div align="right">
						<c:if test="${sessionScope.nick_name == requestScope.imageList.nick_name || requestScope.sessionScope.user_auth==3}">					
							<a href="imagedelete.ib?id=${requestScope.imageList.id}"><button type="button" class="btnact">
								<i class="fas fa-trash-alt"></i>&nbsp;삭제하기</button>
							</a>							
							<a href="imageedit.ib?id=${imageList.id}"><button type="button" class="btnact">
								<i class="fas fa-tools"></i>&nbsp;수정하기</button>
							</a>
						</c:if>							
							<a id="new_content_button" href="imageboard.ib?id=${imageList.id}">
								<button type="button" class="btnact"><i class="fas fa-list-ul"></i>&nbsp;목록보기</button>
							</a>
					</div>
				</div><!-- 카드헤더끝 -->
			
			<!-- 카드바디시작 -->
			<div class="card-body" id="title" align="left" style="padding: 15px 15px 0 15px;">
				<font size="5">${imageList.title}</font><br>
					<div style="padding-bottom: 5px;">
		                <font size="1" color="lightgray">
		                	<i class="fas fa-user-edit"></i>&nbsp;${imageList.nick_name}&nbsp;&nbsp;&nbsp; 
		            		<i class="far fa-clock"></i>    &nbsp;${imageList.date_created}&nbsp;&nbsp;&nbsp;
		            		<i class="far fa-eye"></i>		&nbsp;${imageList.view_count}&nbsp;&nbsp;&nbsp;
		            	</font>
	            	</div>	
	            <hr class="hr22">
	           
	           	<!-- 이미지 가져오기 -->
	            <div style="padding:0 0 0 0;">		
					<img id="detailimage" src="${requestScope.imageList.file_path}" style="width: 100%; height: auto;" readyonly/>
				</div><br>
				
				<!-- 내용가져오기 -->
				<div style="padding:0 0 0 0;">
					<font size="3" color="black">${requestScope.imageList.content}</font><br><br><br><br><br>
				</div>
				
				<div id="disqus_thread"></div>
				<script>	
					/**
					*  RECOMMENDED CONFIGURATION VARIABLES: EDIT AND UNCOMMENT THE SECTION BELOW TO INSERT DYNAMIC VALUES FROM YOUR PLATFORM OR CMS.
					*  LEARN WHY DEFINING THESE VARIABLES IS IMPORTANT: https://disqus.com/admin/universalcode/#configuration-variables*/
					/*
					var disqus_config = function () {
					this.page.url = PAGE_URL;  // Replace PAGE_URL with your page's canonical URL variable
					this.page.identifier = PAGE_IDENTIFIER; // Replace PAGE_IDENTIFIER with your page's unique identifier variable
					};
					*/
					(function() { // DON'T EDIT BELOW THIS LINE
					var d = document, s = d.createElement('script');
					s.src = 'https://gamecommunity.disqus.com/embed.js';
					s.setAttribute('data-timestamp', +new Date());
					(d.head || d.body).appendChild(s);
					})();
				</script>
				<script id="dsq-count-scr" src="//gamecommunity-1.disqus.com/count.js" async></script>
			</div><!-- 카드바디 닫기태그 -->			
			
		</div>
	</div>
	
</div><!-- 폭 줄이기 끝-->

<script type="text/javascript">
	$(document).on('click', '#btnSave', function(e){
		e.preventDefault();
		$("#form").submit();
	});

	$(document).on('click', '#btnList', function(e){
		e.preventDefault();
		location.href="${pageContext.request.contextPath}/imageboard.ib";
	});	
</script>

<script src="imageboard.js"></script>  
<jsp:include page="/WEB-INF/common/Sidebar_Right.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Footer_bottom.jsp"></jsp:include>