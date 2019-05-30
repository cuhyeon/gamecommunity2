$(function() {
		$("#upload").change(function(e) {
			event.preventDefault();

			var file = e.target.files[0];
			var url = URL.createObjectURL(file);
			$("#previewImage").attr("src", url);
		});
		
///////////////////////////////////////////////////////////////
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
			if (!($('#password2').val()) || !($('#password').val()) || !($('#user_name').val()) || !($('#phone_number').val()) ){
				return false;
			} else {
				return true;
			}
		}
		
	 // 비밀번호 확인
	 	$('#password2, #password').keyup(function(){
/* 			console.log(idCheck($('#password').val())); //true  */
			//비밀번호 일치 불일치
			if ($('#password').val() != $('#password2').val()){
				$('#passwordinfo').html('* 비밀번호 불일치');
			} else {
				//비밀번호 정규식
				if (idCheck($('#password').val())){
	 				$('#passwordinfo').html('  영문, 숫자 포함 8~16자리 입력');
	 			} else {
	 				$('#passwordinfo').html('* 비밀번호 확인 완료');
	 			} //비밀번호 정규식
	 			
			}  //비밀번호 일치 불일치

		}); //비밀번호 확인 끝
	 	
		
/*		// 닉네임 중복 확인
		$('#nick_name').keyup(function () {
			
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
*/		
		
		$("#password2, #password, #nick_name, #phone_number, #user_address, #user_auth").keyup(function(){
				console.log(valueic());
				if(		
						($('#passwordinfo').html() == '* 비밀번호 확인 완료') &&
							valueic() )
				{ 	
					$('#edit').prop("disabled", false); 
				}else{
					console.log('엘스문탐');
					$('#edit').prop("disabled", true);
				} //else 끝 
			
		});
	
	});
	
	//우편번호 검색 팝업
    function openAddrPop() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = ''; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    fullAddr = data.roadAddress;

                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    fullAddr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                if (data.userSelectedType === 'R') {
                    //법정동명이 있을 경우 추가한다.
                    if (data.bname !== '') {
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if (data.buildingName !== '') {
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' (' + extraAddr + ')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                $("#user_address").prop('value', fullAddr);
            }
        }).open();
    } 	

    