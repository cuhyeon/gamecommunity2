let isEmpty = function(value){
	if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){
		return true
	}else{
		return false
	}
};


$(function(){
	$("#enterbtn").click(function(){
		let content = CKEDITOR.instances.editor1.getData();
		let reply_id = $("#reply_id").html().replace("#","");
		if(content==""){
			alert("댓글을 입력해 주세요");
		}else{
			$.ajax({
				url:"tipreply.tipax",
				data:{
					editor1:content,
					reply_id:reply_id
				},
				dataType:"html",
				type:"POST",
				success:function(data){
					console.log(data);
					$('#reply_index').remove();
					$("#reply_paste").append(data);
				},
				error:function(){
					console.log("오류");
				}
				
			});//ajax 끝
			
		} //else 끝
	}); // 
	
	let reply_id = $("#reply_id").html().replace("#","");
	console.log("reply_id" + reply_id);
	element = document.getElementById('like');
	element.addEventListener('click', ()=>{
		$.ajax({
			url:"QaRecommand.qajax",
			data:{
				reply_id:reply_id
			},
			dataType:"html",
			type:"POST",
			success:(data)=>{
				console.log(data);
			},
			error:()=>{
				console.log(data);
			}
		})
	}, false);

	console.log("reply_id" + reply_id);
	element = document.getElementById('dislike');
	element.addEventListener('click', ()=>{
		$.ajax({
			url:"QaRecommand.qajax",
			data:{
				reply_id:reply_id
			},
			dataType:"html",
			type:"POST",
			success:(data)=>{
				console.log(data);
			},
			error:()=>{
				console.log(data);
			}
		})
	}, false);
	
	
	
});//	function() 끝