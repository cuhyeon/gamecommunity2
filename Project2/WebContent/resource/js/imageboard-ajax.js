$(function(){
	
	$("#image_pagination").click(()=>{
		if(Number($("#temp").html())>0){
			console.log("temp : " + $("#temp").html());
			$.ajax({
				url:"imagelist.ibajax",
				data:{
					page:$("#temp").html()
				},
				dataType:"html",
				type:"POST",
				success:function(data){
					$("#columns").append(data);
					let page = Number($("#temp").html())+1;
					$("#temp").empty();
					$("#temp").append(page);
					
				},
				error:function(){
					console.log("오류");
					
				}
				
			});//ajax 끝
		}
			
	})	
		
	}); // 
	