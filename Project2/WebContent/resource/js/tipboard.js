$(function(){
	CKEDITOR.replace( 'editor1',{
		height:350,
		enterMode:CKEDITOR.ENTER_BR
	} );
	
	$("#empListSearch").on("keyup",()=>{
		word = $("#title").val();
		if(word.replace(" ","").length==0||word.replace(" ","").length==null){
			alert("제목을 입력해주세요.");
			$("#title").focus();
		}else{
			$("#enter-btn").attr("class","btn btn-primary");
		}
	});

	
	function check(){
		if(!tipboardwrite.title.val){
			alert("제목을 입력해주세요.");
			tipboardwrite.title.focus();
	        return false;
		}
	}
});

