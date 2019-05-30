let isEmpty = function(value){
	if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){
		return true
	}else{
		return false
	}
};

$(function(){
	$("#enterbtn").click(function(){
		let content = $('#comment').val();
		let reply_id = $("#reply_id").html().replace("#","");
		if(content==""){
			alert("댓글을 입력해 주세요");
		}else{
			$.ajax({
				url:"vbreply.vbajax",
				data:{
					editor1:content,
					reply_id:reply_id
				},
				dataType:"html",
				type:"POST",
				success:function(data){
					console.log(data);
					$("#reply_paste").append(data);
				},
				error:function(){
					console.log("오류");
				}
				
			});//ajax 끝
			
		} //else 끝
	});
});