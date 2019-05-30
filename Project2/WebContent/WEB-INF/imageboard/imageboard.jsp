<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="/WEB-INF/common/Head_top.jsp"></jsp:include>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resource/css/imageboard.css"/>
<script src="${pageContext.request.contextPath}/resource/js/imageboard-ajax.js" type="text/javascript"></script>
<jsp:include page="/WEB-INF/common/Header_top.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Sidebar_Left.jsp"></jsp:include>

<!-- 헤더삽입 -->
<div class="cutimagewriterow">
<div class="card m-b-15">		
	<div class="card-header card-header-inverse" id="cardheaderbotton">
		<h4 class="card-header-title">Image Board<span id='temp' style="color:#1a1a1a">2</span> </h4>		
		<div align="right">
			<c:set var="auth" value="${user_auth}"/>		
				<c:if test="${sessionScope.user_auth == 1 || sessionScope.user_auth == 3}">
					<a href="<c:url value="imagewrite.ib"/>"><button type="button" class="btnact">
					<i class="far fa-edit"></i>&nbsp;글쓰기</button></a>
				</c:if>
        </div>                        				
	</div>   
</div>
    
	 <div id="columns">
	    <c:forEach var="imageList" items="${result}" varStatus="status"> 
	        <figure>
	        	<a href="imagedetail.ib?id=${imageList.id}">
	            <img src="${imageList.file_path}" id="image" alt="" /></a>          
	            <figcaption>
	                <div style="padding-bottom: 5px;">
	                <font size="3" color="balck"><a href="imagedetail.ib?id=${imageList.id}"><b>${imageList.title}</b></a><br></font>                
	                <font size="1" color="lightgray">
	                	<i class="fas fa-user-edit"></i>&nbsp;${imageList.nick_name}&nbsp;&nbsp;&nbsp; 
	            		<i class="far fa-clock"></i>    &nbsp;${imageList.date_created}&nbsp;&nbsp;&nbsp;
	            		<i class="far fa-eye"></i>		&nbsp;${imageList.view_count}&nbsp;&nbsp;&nbsp;
	            	</font><br>
	            	</div>
	                
	                <div class="txt_line">
	                <font size="2" color="gray">${imageList.content}</font>
	                </div>
	            </figcaption>              	
	        </figure>
	    </c:forEach>    
	</div>	
		
		<button class="infinitscroll" id="image_pagination">Load more images</button>
	
</div>



<script src="imageboard.js"></script> 
<jsp:include page="/WEB-INF/common/Sidebar_Right.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Footer_bottom.jsp"></jsp:include>