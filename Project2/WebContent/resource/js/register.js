$(function(){
	// 패스워드 정규식 확인 함수
	function idCheck (password) {
        var titleCheck = password;
        var languageCheck = /^[a-zA-Z0-9]{8,16}$/;
 
        if (languageCheck.test(titleCheck)) {
        	return false;
            } else {
            return true;
        }
    }
	
	// 이메일 정규식 확인 함수
	function emailCheck (email) {
        var titleCheck = email;
        var emailRule = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;

        if (emailRule.test(titleCheck)) {
        	return false;
            } else {
            return true;
        }
    }
	
	// 모든값 확인
 	function valueic (){
		if (!($('#password2').val()) || !($('#password').val()) || !($('#user_name').val()) || !($('#nick_name').val()) || !($('#phone_number').val()) || !($('#email').val()) || !($('#phone_number').val()) ){
			return false;
		} else {
			return true;
		}
	} 
	
	$("#login-remember-me").change(function(){
		console.log('체인지 탐');
		console.log( ($('#emailinfo').html() == '사용 가능한 email') );
		console.log( ($('#passwordinfo').html() == '비밀번호 확인 완료') );
		console.log( ($('#nickinfo').html() == '* 사용가능한 닉네임') );
		console.log( valueic() );
		console.log( $("#login-remember-me").is(":checked") );
		
/* 		// disabled 관리
		$('#password2, #password, #user_name, #nick_name, #phone_number, #email, #phone_number').keyup (function(){ */
			
			if(		
					($('#emailinfo').html() == '사용 가능한 email') &&
					($('#passwordinfo').html() == '비밀번호 확인 완료') &&
					($('#nickinfo').html() == '* 사용가능한 닉네임') &&
						valueic() &&
					$("#login-remember-me").is(":checked") ) // 조건설정 끝
			{ 	
				console.log('이프문탐');
				$('#subbtn').prop("disabled", false); //실행문	
			}else{
				console.log('엘스문탐');
				$('#subbtn').prop("disabled", true);
			} //else 끝 
			
/* 			if(valueic() && $("#login-remember-me").is(":checked")){
				//모든 값들이 채워졌을 때
				$('#subbtn').prop("disabled", false);
				
			} else {
				//모든 값들이 안 채워 졌을 때
				$('#subbtn').prop("disabled", true);
			} */
			
//		}); // disabled 관리 끝
		
	});
	
	
	

	// 비밀번호 확인
 	$('#password2, #password').keyup(function(){
/* 		console.log(idCheck($('#password').val())); //true */
		//비밀번호 일치 불일치
		if ($('#password').val() != $('#password2').val()){
			$('#passwordinfo').html('* 비밀번호 불일치');
		} else {
			//비밀번호 정규식
			if (idCheck($('#password').val())){
 				$('#passwordinfo').append('  영문, 숫자 포함 8~16자리 입력');
 			} else {
 				$('#passwordinfo').html('비밀번호 확인 완료');
 			} //비밀번호 정규식
 			
		}  //비밀번호 일치 불일치

	}); //비밀번호 확인 끝
 	
	
	// 닉네임 중복 확인
	$('#nick_name').blur(function () {
		
		$.ajax({ //비동기 !
			
			url : "nicknamechack.reg",
			data:{nick : $('#nick_name').val()}, 
			success : function(data){
				
				if (data == 'true'){
					$('#nickinfo').html('* 사용가능한 닉네임');
					
				} else {
					$('#nickinfo').html('* 사용 불가능한 닉네임');
				}
				
			}
			
		}); //ajax 끝
	}); // 닉네임 중복 끝
	
	
	// 이메일 중복 확인
	$('#email').blur(function () {
		
		$.ajax({ //비동기 !
			
			url : "email.reg",
			data:{emailchk : $('#email').val()}, 
			success : function(data){
				
				if (data == 'true'){
					
					
					//이메일 정규식
					if (emailCheck($('#email').val())){
		 				$('#emailinfo').html('  이메일 형식을 알맞게 기입하세요');
		 			} else {
		 				$('#emailinfo').html('사용 가능한 email');
		 			} //이메일 정규식
		 			
				} else if (data == 'false' || !($('#email').val())) {
					
					$('#emailinfo').html('* 사용 불가능한 email');
				}
				
			}
			
		}); //ajax 끝
	}); // 이메일 중복 끝
	
}); // 돔끝