//$(function(){
//	var imgCount = $("img").length;
//	
//	if(imgCount>1){
//		for(var i=1; i<imgCount;i++){
//			var div = document.getElementById("qa_detail_reply_div"); // 이미지를 감싸는 div
//			var img = document.images[i]; // 이미지
//			console.log(img);
//			console.log(img.height);
//			console.log(img.width);
//			var divAspect = 100 / 100;
//			var imgAspect = img.height / img.width;
//			 console.log(imgAspect);
//			if (imgAspect <= divAspect) {
//			    var imgWidthActual = div.offsetHeight / imgAspect;
//			    var imgWidthToBe = div.offsetHeight / divAspect;
//			    var marginLeft = -Math.round((imgWidthActual - imgWidthToBe) / 2);
//			    img.style.cssText = 'visibility:visible; width: auto; height: 100%; margin-left: '+ marginLeft + 'px;'
//			} else {
//			    img.style.cssText = 'visibility:visible; width: 100%; height: auto; margin-left: 0;';
//			}
//		}
//
//	}
//
//});