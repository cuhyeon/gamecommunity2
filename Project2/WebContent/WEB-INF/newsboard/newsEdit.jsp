<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/common/Head_top.jsp"></jsp:include>
<script src="https://cdn.ckeditor.com/4.11.4/standard/ckeditor.js"></script>
<link rel="stylesheet" type="text/css"
    href="${pageContext.request.contextPath}/resource/css/newswrite.css" />
<script src="${pageContext.request.contextPath}/resource/js/newswrite.js" type="text/javascript"></script>
<jsp:include page="/WEB-INF/common/Header_top.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Sidebar_Left.jsp"></jsp:include>
<c:set var="dto" value="${requestScope.dto}"/>
    <div class="card m-b-15">
    <!-- BEGIN card -->
    <div class="card m-b-15">
            <div class="card-header card-header-inverse">
            <h4 class="card-header-title">GAME NEWS</h4>
        </div>
<div class="row justify-content-md-center">
    <form id="news-write-form" action="eidtOk.nb?id=${dto.id}" enctype="multipart/form-data" method="post">
        <!-- BEGIN card-header -->
        <div class="content">
        <br>
            <table id="form-table">
            
                <tr>
                    <td><label class="input-group-text">분류</label></td>
                    <td>&nbsp;게임 뉴스게시판</td>
                    <td rowspan=6><img src="<c:choose><c:when test="${dto.file_path=='uploads/'}">upload/default-img.gif</c:when>
                                                                          <c:otherwise>${dto.file_path}</c:otherwise></c:choose>"
                        id="previewImage" name="image" /></td>
                </tr>
                <tr>
                    <td><span class="input-group-text" id="inputGroupFileAddon01">메인</span></td>
                    <td>
                        <div id="upload_label"><c:choose><c:when test="${dto.file_path != 'uplaods/' }">${dto.file_path}</c:when>
                                                                        <c:otherwise></c:otherwise></c:choose></div>
                        <button type="button" id="upload_btn"  class="btn btn-outline-secondary">선택</button>
                        <input type="file" class="form-control-file" id="upload" name="upload"  style="display:none;">
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td><label class="input-group-text">이름</label></td>
                    <td><input type="text" class="form-control"  name="nick_name" value="${dto.nick_name}"
                        readonly></td>
                    <td></td>
                </tr>
                <tr>
                    <td><label class="input-group-text">등급</label></td>
                    <td><input type="text" class="form-control" value="기자"
                        readonly></td>
                </tr>
                <tr>
                    <td><label class="input-group-text">날짜</label></td>
                    <td><input type="text" class="form-control" value="${dto.date_created}"
                        readonly></td>
                </tr>
            </table>
            <br>
            <table>
                <tr>
                    <td><label class="input-group-text">제목</label></td>
                    <td><input type="text" class="form-control" name="title"
                        id="title" placeholder="제목을 입력하세요" value="${dto.title}" maxlength="150"></td>
                </tr>
            </table>
        </div>
        <br>
        <div class="row justify-content-md-left">
            <div class="col_c">
                <div class="input-group">
                    <textarea class="form-control" name="editor1" >${dto.content}</textarea>
                </div>
            </div>
        </div>
        <div class="row justify-content-md-center">
            <button type="submit" class="btn btn-outline-secondary">수정</button>
        </div>
    </form>
</div>
</div>
</div>
<jsp:include page="/WEB-INF/common/Sidebar_Right.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Footer_bottom.jsp"></jsp:include>