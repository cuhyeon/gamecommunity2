	$(document).on('click', '#btnSave', function(e){
		e.preventDefault();
		$("#form").submit();
	});

	$(document).on('click', '#btnList', function(e){
		e.preventDefault();
		location.href="${pageContext.request.contextPath}/imageboard.ib";
	});	
	
	
	$(function() {
		$("#myFileUp").change(function(e) {
			event.preventDefault();
			var file = e.target.files[0];
			var url = URL.createObjectURL(file);
			$("#previewImage").attr("src", url);
		});
	});
	
	
