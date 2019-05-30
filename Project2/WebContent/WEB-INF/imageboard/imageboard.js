
/***** detail.jsp *****/

/* disqus 댓글 기능*/
	/*
	*  RECOMMENDED CONFIGURATION VARIABLES: EDIT AND UNCOMMENT THE SECTION BELOW TO INSERT DYNAMIC VALUES FROM YOUR PLATFORM OR CMS.
	*  LEARN WHY DEFINING THESE VARIABLES IS IMPORTANT: https://disqus.com/admin/universalcode/#configuration-variables*/
	/*
	var disqus_config = function () {
	this.page.url = PAGE_URL;  // Replace PAGE_URL with your page's canonical URL variable
	this.page.identifier = PAGE_IDENTIFIER; // Replace PAGE_IDENTIFIER with your page's unique identifier variable
	};
	*/
	(function() { // DON'T EDIT BELOW THIS LINE
	var d = document, s = d.createElement('script');
	s.src = 'https://gamecommunity.disqus.com/embed.js';
	s.setAttribute('data-timestamp', +new Date());
	(d.head || d.body).appendChild(s);
	})();

/* */
	$(document).on('click', '#btnSave', function(e){
		e.preventDefault();
		$("#form").submit();
	});

	$(document).on('click', '#btnList', function(e){
		e.preventDefault();
		location.href="${pageContext.request.contextPath}/imageboard.ib";
	});
	
	
	
/***** edit.jsp *****/
function readURL(input) {
	console.log("버튼클릭함1");
    if (input.files && input.files[0]) {
    var reader = new FileReader();
    reader.onload = function (e) {
            $('#cover').attr('src', e.target.result);   //cover src로 붙여지고
            $('#fileName').val(input.files[0].name);    //파일선택 form으로 파일명이 들어온다
        }
      reader.readAsDataURL(input.files[0]);
    }
}

$("#myFileUp").change(function(){
    readURL(this);
    console.log("이미지 바뀜?");
});