<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import ="com.oreilly.servlet.MultipartRequest" %>    
<%@ page import ="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/common/Head_top.jsp"></jsp:include>

<!-- jQuery -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/resource/js/imagewrite.js" type="text/javascript"></script>

<link rel="stylesheet" href="./resource/css/imageboard.css">
<jsp:include page="/WEB-INF/common/Header_top.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Sidebar_Left.jsp"></jsp:include>

<!-- 콘텐츠영역 시작 -->
<form action="imagewriteok.ib" enctype="multipart/form-data" method="post">

<div class="cutimagewriterow">

<div class="card m-b-15">

	<!-- 카드헤더 시작  -->
	<div class="card-header card-header-inverse" id="cardheaderbotton">
		<h4 class="card-header-title">Posting a photo</h4>				
			<div align="right">
				<a href="imageboard.ib?id=${imageList.id}">				
					<button type="button" class="btnact"><i class="fas fa-list-ul"></i>&nbsp;목록보기</button>&nbsp;&nbsp;
				</a>
					<button type="submit" class="btnact"><i class="far fa-edit"></i>&nbsp;등록</button>
			</div>
		</div>
	</div>	
	
		<article>
			<div class="container" role="main">	
									
				<div class="mb-3">
				
					<label for="title">제목</label>
					<input type="text" name="title" class="form-control" placeholder="제목을 입력해 주세요" maxlength="28"><br>
					
					<label for="content">내용</label>
					<textarea class="form-control" name="editor1" id="summernote" rows="5" placeholder="내용을 입력해 주세요" 
				 			  onKeyUp="javascript:fnChkByte(this,'475')"></textarea>
					<span id="byteInfo" style="text-align:right;">0 </span> / 475bytes
					<br><br>
					
					<!-- 파일등록 -->
					<div class="form-group" style="margin: 8px 0 8px;" align="right">
						<div class="fileRegiBtn">
							<label for="myFileUp"><i class="far fa-check-circle"></i>&nbsp;파일 선택
								<input type="file" name="fileup" id="myFileUp" accept="image/gif, image/jpeg, image/png">								
							</label>													
						</div>						
					</div>
									
					<!-- 커버이미지 들어오는 부분 (임시로 이미지를 넣어줬다)-->
					<div class="selectCover" style="padding-left: 0;">
						<img src="upload/default-img.gif" id="cover" style="width:100%; height:auto"/>
					</div><br>			
						
				</div>		
			</div>
		</article>
	</div>
</form>	

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

//파일 미리보기 
function readURL(input) {
	console.log("버튼클릭함1");
    if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function (e) {
            $('#cover').attr('src', e.target.result);   
            $('#fileName').val(input.files[0].name);    //파일선택 form으로 파일명이 들어온다
        }
      reader.readAsDataURL(input.files[0]);
    }
}

$("#myFileUp").change(function(){
    readURL(this);
    console.log("이미지 바뀜?");
});
</script>

<script type="text/javascript">
function fileCheck(obj) {
    pathpoint = obj.value.lastIndexOf('.');
    filepoint = obj.value.substring(pathpoint+1,obj.length);
    filetype = filepoint.toLowerCase();
    if(filetype=='jfif' ||filetype=='jpg' || filetype=='gif' || filetype=='png' || filetype=='jpeg' || filetype=='bmp' || filetype==null) {// 정상적인 이미지 확장자 파일일 경우 ...
    } else {
        alert('이미지 파일만 선택할 수 있습니다.');
        parentObj  = obj.parentNode
        node = parentObj.replaceChild(obj.cloneNode(true),obj);
        return false;    }
    if(filetype=='bmp') {
        upload = confirm('BMP 파일은 웹상에서 사용하기엔 적절한 이미지 포맷이 아닙니다.\n그래도 계속 하시겠습니까?');
        if(!upload) return false;
    }
}
</script>

<!-- 콘텐츠영역 끝 -->
<jsp:include page="/WEB-INF/common/Sidebar_Right.jsp"></jsp:include>
<jsp:include page="/WEB-INF/common/Footer_bottom.jsp"></jsp:include>