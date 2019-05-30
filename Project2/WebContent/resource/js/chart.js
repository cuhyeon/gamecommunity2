$(function() {
	function addComma(num) {
		  var regexp = /\B(?=(\d{3})+(?!\d))/g;
		  return num.toString().replace(regexp, ',');
	}
	
	var change = function (a) {
		  $('#l1, #l2, #l3, #l4, #l5').removeClass('page-item active');
		  if(a == 1) {
			  $('#l1').addClass("page-item active");
		  } else if (a==2){
			  $('#l2').addClass("page-item active");
		  } else if (a==3){
			  $('#l3').addClass("page-item active");
		  }else if (a==4){
			  $('#l4').addClass("page-item active");
		  }else if (a==5){
			  $('#l5').addClass("page-item active");
		  }
		};
	
	var num = 0;
	var page = 1;
	var text = "";
	
	var high;
	var low;
	
	var name = [];
	var developer = [];
	var publisher = [];
	var positive = [];
	var negative = [];
	var averagetimearr = [];
	var price = [];
	var price1 = []
	
	var tempobj = {};
		$.ajax({
			
			url : "gamerank.cht",
			dataType : "text",
			success : function(data) {
				high = 20;
				low = 0;
				var cdata = JSON.parse(data);
				
				$.each(cdata, function(key, value){
					name.push(value.name);
					developer.push(value.developer);
					publisher.push(value.publisher);
					positive.push(addComma(value.positive + ""));
					negative.push(addComma(value.negative+ ""));
					averagetimearr.push(addComma(value.median_forever + "") + " min");
					price.push(addComma(value.price+"")+"￠");
					price1.push(value.price);
					
				});
				
				
				
				$("#tbody").empty();
				
				for(var i = low; i < high; i++){
	                num++;
	                text += "<tr>"
	                		+ "<td>" + num + "</td>"
	                		+ "<td>" + name[i] + "</td>"
	                		+ "<td>" + developer[i] + "</td>"
	                		+ "<td>" + publisher[i] + "</td>"
	                		+ "<td>" + positive[i] + "</td>"
	                		+ "<td>" + negative[i] + "</td>"
	                		+ "<td>" + averagetimearr[i] + "</td>"
	                		+ "<td>" + price[i] + "</td></tr>"
	             }
				
				$("#tbody").html(text);
			},
			error : function(xhr, status, error) {
				console.log("에러");
			}

		});
		
		$("#btn1").click(function(){
			$("#tbody").empty();
			page = 1;
			num = 0;
			text = "";
			high = 20;
			low = 0;
			for(var i = low; i < high; i++){
	            num++;
	            text += "<tr>"
	            		+ "<td>" + num + "</td>"
	            		+ "<td>" + name[i] + "</td>"
	            		+ "<td>" + developer[i] + "</td>"
	            		+ "<td>" + publisher[i] + "</td>"
	            		+ "<td>" + positive[i] + "</td>"
	            		+ "<td>" + negative[i] + "</td>"
	            		+ "<td>" + averagetimearr[i] + "</td>"
	            		+ "<td>" + price[i] + "</td></tr>"
	         }
			$("#tbody").html(text);
			change(1);
		});
		
		
	$("#btn2").click(function(){
		$("#tbody").empty();
		page = 2;
		num = 20;
		text = "";
		low = 20;
		high = 40;
		
		for(var i = low; i < high; i++){
            num++;
            text += "<tr>"
            		+ "<td>" + num + "</td>"
            		+ "<td>" + name[i] + "</td>"
            		+ "<td>" + developer[i] + "</td>"
            		+ "<td>" + publisher[i] + "</td>"
            		+ "<td>" + positive[i] + "</td>"
            		+ "<td>" + negative[i] + "</td>"
            		+ "<td>" + averagetimearr[i] + "</td>"
            		+ "<td>" + price[i] + "</td></tr>"
         }
		$("#tbody").html(text);
		change(2);
	});
	
	$("#btn3").click(function(){
		$("#tbody").empty();
		page = 3;
		num = 40;
		text = "";
		low = 40;
		high = 60;
		for(var i = low; i < high; i++){
            num++;
            text += "<tr>"
            		+ "<td>" + num + "</td>"
            		+ "<td>" + name[i] + "</td>"
            		+ "<td>" + developer[i] + "</td>"
            		+ "<td>" + publisher[i] + "</td>"
            		+ "<td>" + positive[i] + "</td>"
            		+ "<td>" + negative[i] + "</td>"
            		+ "<td>" + averagetimearr[i] + "</td>"
            		+ "<td>" + price[i] + "</td></tr>"
         }
		$("#tbody").html(text);
		change(3);
	});
	
	$("#btn4").click(function(){
		$("#tbody").empty();
		page = 4;
		num = 60;
		text = "";
		low = 60;
		high = 80;
		for(var i = low; i < high; i++){
            num++;
            text += "<tr>"
            		+ "<td>" + num + "</td>"
            		+ "<td>" + name[i] + "</td>"
            		+ "<td>" + developer[i] + "</td>"
            		+ "<td>" + publisher[i] + "</td>"
            		+ "<td>" + positive[i] + "</td>"
            		+ "<td>" + negative[i] + "</td>"
            		+ "<td>" + averagetimearr[i] + "</td>"
            		+ "<td>" + price[i] + "</td></tr>"
         }
		$("#tbody").html(text);
		change(4);
	});
	
	$("#btn5").click(function(){
		$("#tbody").empty();
		page = 5;
		num = 80;
		text = "";
		low = 80;
		high = 100;
		for(var i = low; i < high; i++){
            num++;
            text += "<tr>"
            		+ "<td>" + num + "</td>"
            		+ "<td>" + name[i] + "</td>"
            		+ "<td>" + developer[i] + "</td>"
            		+ "<td>" + publisher[i] + "</td>"
            		+ "<td>" + positive[i] + "</td>"
            		+ "<td>" + negative[i] + "</td>"
            		+ "<td>" + averagetimearr[i] + "</td>"
            		+ "<td>" + price[i] + "</td></tr>"
         }
		$("#tbody").html(text);
		change(5);
	});
	
	$("#btnpre").click(function(){
		if(page != 1) {
			$("#tbody").empty();
			page --;
			text = "";
			low -= 20;
			high -= 20;
			num -=40;
			for(var i = low; i < high; i++){
	            num++;
	            text += "<tr>"
	            		+ "<td>" + num + "</td>"
	            		+ "<td>" + name[i] + "</td>"
	            		+ "<td>" + developer[i] + "</td>"
	            		+ "<td>" + publisher[i] + "</td>"
	            		+ "<td>" + positive[i] + "</td>"
	            		+ "<td>" + negative[i] + "</td>"
	            		+ "<td>" + averagetimearr[i] + "</td>"
	            		+ "<td>" + price[i] + "</td></tr>"
	         }
			$("#tbody").html(text);
			change(page);
		}
		
	});
	
	$("#btnnext").click(function(){
		if(page != 5) {	
			$("#tbody").empty();
			page ++;
			
			text = "";
			low += 20;
			high += 20;

			for(var i = low; i < high; i++){
	            num++;
	            text += "<tr>"
	            		+ "<td>" + num + "</td>"
	            		+ "<td>" + name[i] + "</td>"
	            		+ "<td>" + developer[i] + "</td>"
	            		+ "<td>" + publisher[i] + "</td>"
	            		+ "<td>" + positive[i] + "</td>"
	            		+ "<td>" + negative[i] + "</td>"
	            		+ "<td>" + averagetimearr[i] + "</td>"
	            		+ "<td>" + price[i] + "</td></tr>"
	         }
			$("#tbody").html(text);
			change(page);
		} 
		
	});
	
});
