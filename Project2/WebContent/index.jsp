<%@page import="org.masterjung.util.GetImagePath"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/common/Head_top.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resource/css/index.css" />
	<%
	request.getSession().invalidate();
%>
<script src="${pageContext.request.contextPath}/resource/js/index.js" type="text/javascript"></script>
<jsp:include page="/WEB-INF/common/Header_top.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Sidebar_Left.jsp"></jsp:include>
<!-- 콘텐츠 영역 시작-->

 <div class="loading" style = "text-align:center"><img id="loading-image" src="resource/img/ajax-loader.gif" alt="Loading..."  /><br>
 <img id="loading-image" src="resource/img/loading.gif" alt="Loading..."  /></div>

<!-- 콘텐츠영역 끝 -->
<jsp:include page="/WEB-INF/common/Sidebar_Right.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Footer_bottom.jsp"></jsp:include>