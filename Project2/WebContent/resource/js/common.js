$(function(){
	const floatPosition = parseInt($("#scroll_Banner").css("top"));
	
	$(window).scroll(()=>{
		const scrollTop = $(window).scrollTop();
		const newPosition = scrollTop + floatPosition+"px";
		
		$("#scroll_Banner").stop().animate({
			"top": newPosition
		},500);
	}).scroll();
	
	
	var url = $(location).attr('href');

	if(url.indexOf('.reg') != -1){
		$('#a1').attr('class' , 'active');
	} else if (url.indexOf('.cht') != -1) {
		$('#a2').attr('class' , 'active');
	} else if (url.indexOf('.nb') != -1) {
		$('#a3').attr('class' , 'active');
	} else if (url.indexOf('.total') != -1) {
		$('#a4').attr('class' , 'active');
	} else if (url.indexOf('.tb') != -1) {
		$('#a5').attr('class' , 'active');
	} else if (url.indexOf('.qb') != -1) {
		$('#a6').attr('class' , 'active');
	} else if (url.indexOf('.ib') != -1) {
		$('#a7').attr('class' , 'active');
	} else if (url.indexOf('.vb') != -1) {
		$('#a8').attr('class' , 'active');
	}
});