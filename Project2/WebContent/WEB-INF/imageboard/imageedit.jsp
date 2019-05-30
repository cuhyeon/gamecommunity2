<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import ="com.oreilly.servlet.MultipartRequest" %>    
<%@ page import ="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/common/Head_top.jsp"></jsp:include>

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/resource/js/imagewrite.js" type="text/javascript"></script>

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

<link rel="stylesheet" href="./resource/css/imageboard.css">
<jsp:include page="/WEB-INF/common/Header_top.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Sidebar_Left.jsp"></jsp:include>

<!-- 콘텐츠영역 시작 -->

<form action="imageeditok.ib?id=${requestScope.imageList.id}" enctype="multipart/form-data" method="post">   

<div class="cutimagewriterow">
	<div class="card m-b-15">	
	        
		<div class="card-header card-header-inverse" id="cardheaderbotton">
			<h4 class="card-header-title">
				<!-- <i class="fas fa-lg fa-fw m-r-10 fa-images"></i> -->Image Editing
			</h4>	
			
		    <div align="right"> 
		    	<button type="button" class="btnact" onclick="location.href='imagedelete.ib?id=${requestScope.imageList.id}'">
					<i class="fas fa-trash-alt"></i>&nbsp;삭제하기</button>	
				<button type="button" class="btnact" onclick="location.href='imagewrite.ib'">
					<i class="far fa-edit"></i>&nbsp;글쓰기</button>
				<button type="button" class="btnact" onclick="location.href='imageboard.ib'">
					<i class="fas fa-list-ul"></i>&nbsp;목록보기</button>
				<button type="submit" class="btnact" onclick="location.href='imageeditok.ib'">
					<i class="far fa-check-circle"></i>&nbsp;수정완료</button>	
			</div>					  
		</div>
	</div>	
		
		<!-- 카드바디시작 -->
			<div class="container" role="main">						
				
				<div class="mb-3">
					<label for="writer">제목</label>
					<input type="text" name="title" class="form-control" value="${requestScope.imageList.title}" maxlength="28">
				</div>		
			
				<div class="mb-3">				
					<label for="writer">작성자</label>
					<input type="text" name="nick_name" class="form-control" value="${requestScope.imageList.nick_name}" readonly/>
				</div>		
	
				<div class="mb-3">
					<label for="content">내용</label>
					<textarea class="form-control" name="editor1" rows="10">${requestScope.imageList.content}</textarea>
				</div>
				<span id="byteInfo" style="text-align:right;">0 </span> / 475bytes <br>
				
				<!-- 파일등록 -->
				<div class="form-group" style="margin: 8px 0 8px;" align="right">
					<div class="fileRegiBtn">
						<label for="myFileUp">파일 선택</label>
						<input type="file" name="fileup" id="myFileUp" accept="image/gif, image/jpeg, image/png">
					</div>
				</div>
				
				<!-- 이미지 부분 -->
				<div class="selectCover" style="padding:0 0 0 0;" readonly>		
					<img id="cover" src="${requestScope.imageList.file_path}" style="width: 100%; height: auto;" />
				</div><br>			
				
			</div>	
		</div>		
	</form>


<!-- Byte 수 체크 제한 -->
<script type="text/javascript">
function fnChkByte(obj, maxByte){
    var str = obj.value;
    var str_len = str.length;

    var rbyte = 0;
    var rlen = 0;
    var one_char = "";
    var str2 = "";

    for(var i=0; i<str_len; i++){
        one_char = str.charAt(i);
        if(escape(one_char).length > 4){
            rbyte += 2;                                         //한글2Byte
        }else{rbyte++;}                                        //영문 등 나머지 1Byte
     
        if(rbyte <= maxByte){rlen = i+1;}                       //return할 문자열 갯수
	}
    
     if(rbyte > maxByte){    	 
	  alert("메세지는 최대 " + maxByte + "byte를 초과할 수 없습니다.")
	  str2 = str.substr(0,rlen);                                  //문자열 자르기
	  obj.value = str2;
	  fnChkByte(obj, maxByte);
     }else{document.getElementById('byteInfo').innerText = rbyte;}
}

function readURL(input) {
	console.log("버튼클릭함1");
    if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function (e) {
            $('#cover').attr('src', e.target.result);   //cover src로 붙여지고
            $('#fileName').val(input.files[0].name);    //파일선택 form으로 파일명이 들어온다
        }
      reader.readAsDataURL(input.files[0]);
    }
}

$("#myFileUp").change(function(){
    readURL(this);
    console.log("이미지 바뀜?");
});

/* 삭제 알러트 */
function confirmter() {
  if(confirm("정말 삭제하시겠습니까?")){ 
   location.href="'./imagedelete.ib?id='+${requestScope.imageList.id}";
  }else{
   return false;
  }
 }
</script>

<!-- 콘텐츠영역 끝 -->
<jsp:include page="/WEB-INF/common/Sidebar_Right.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Footer_bottom.jsp"></jsp:include>