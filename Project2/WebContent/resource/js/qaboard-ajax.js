let isEmpty = function(value){
	if( value == "" || value == null || value == undefined || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){
		return true
	}else{
		return false
	}
};

$(function(){
	$("#like").hover(()=>{
		$(this).css("background-color","blue");
	})
})

$(function(){
	$("#enterbtn").click(function(){
		let content = CKEDITOR.instances.editor1.getData();
		let reply_id = $("#reply_id").html().replace("#","");
		let total_reply1= $("#total_reply1").html();
		let total_reply2= $("#total_reply2").html();
		if(content==""){
			alert("답변을 입력해 주세요");
		}else if(content.length>255){
			alert("답변은 최대 255자를 초과할 수 없습니다");
			
		}else{
			$.ajax({
				url:"qareply.qajax",
				data:{
					editor1:content,
					reply_id:reply_id
				},
				dataType:"html",
				type:"POST",
				success:function(data){
					$('#total_reply1').empty();
					$('#total_reply2').empty();
					$('#total_reply1').append(Number(total_reply1)+1);
					$('#total_reply2').append(Number(total_reply2)+1);
					$('#reply_index').remove();
					$("#reply_paste").append(data);
					$("#reply_paste").focus();
					
				},
				error:function(){
					console.log("오류");
				}
				
			});//ajax 끝
			
		} //else 끝
	}); // 
	
	let reply_id = $("#reply_id").html().replace("#","");
	element = document.getElementById('like');
	element.addEventListener('click', ()=>{
		$.ajax({
			url:"QaRecommand.qajax",
			data:{
				reply_id:reply_id,
				like:"like"
			},
			dataType:"html",
			type:"POST",
			success:(data)=>{
				if(data!=0){
					$("#recommand_content").empty();
					$("#recommand_content").append(data);
				}else{
					alert("이미 추천하신 글입니다.");
				}
			},
			error:()=>{
				console.log(data);
			}
		})
	}, false);

	
	element = document.getElementById('dislike');
	console.log(element);
	element.addEventListener('click', ()=>{
		$.ajax({
			url:"QaRecommand.qajax",
			data:{
				reply_id:reply_id,
				like:"dislike"
			},
			dataType:"html",
			type:"POST",
			success:(data)=>{
				if(data!=0){
					$("#recommand_content").empty();
					$("#recommand_content").append(data);
				}else{
					alert("이미 비추하신 글입니다.");
				}
			},
			error:()=>{
				console.log(data);
			}
		})
	}, false);
	
	
});//	function() 끝