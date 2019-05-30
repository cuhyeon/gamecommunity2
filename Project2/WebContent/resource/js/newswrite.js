$(function() {
	$("#upload").change(function(e) {
		event.preventDefault();
		var file = e.target.files[0];
		var url = URL.createObjectURL(file);
		$("#previewImage").attr("src", url);
		$("#upload_label").text(file.name);
	});

	$("#upload_btn").click(function() {
		$("#upload").trigger("click");
	});

	CKEDITOR.replace('editor1', {
		width : 820,
		height : 300,
		enterMode : CKEDITOR.ENTER_BR
	})
		
	$("#title").keyup(function(){
		if(!($("#title").val()=="")){
			$("#submit").removeAttr("disabled");
			console.log($("#title").val());
		}else{
			$("#submit").attr("disabled","disabled");
			console.log($("#title").val());
		}
	})
});