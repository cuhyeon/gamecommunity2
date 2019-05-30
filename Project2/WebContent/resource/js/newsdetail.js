$(function(){
	$.urlParam = function(name){
	    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
	    if (results==null){
	       return null;
	    }
	    else{
	       return results[1] || 0;
	    }
	}

	$(".reply-create").click(function(){
		
		$(".empty").empty();
		let content = $('.content').val();
		var id = $('.nick_name').text();
		
		if(content==""){
			alert("댓글을 입력해 주세요");
		}else{
			$.ajax({
				url:"newsreply.najax",
				data:{content:content, id:id, board_id:$.urlParam('id'),page:$.urlParam('page')},
				dataType:"html",
				type:"post",
				success:function(data){
					$('#reply').append(data);
				},
				error:function(){
					console.log("오류");
				}
			});
		}
	});
});