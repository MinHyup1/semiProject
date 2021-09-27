        // SDK를 초기화 합니다. 사용할 앱의 JavaScript 키를 설정해 주세요.
        /* Kakao.init('cfb699a7c2c194f945a6a34a6be6daa5'); */
		Kakao.init('8406376e4a3016276da81540af46ba74');
        
        // SDK 초기화 여부를 판단합니다.
        console.log(Kakao.isInitialized());


		  function loginWithKakao(){
			  Kakao.Auth.login({
				 
			        success: function(authObj) {
			          //로그인 성공시, API 호출
			          Kakao.API.request({
			            url: '/v2/user/me', //사용자 정보를 읽어들이는 고정된 url
			            success: function(res) {
			            
							alert('로그인성공');
							
							const email = res.kakao_account.email;
							const nick = res.properties.nickname;
							const gender = res.kakao_account.gender;
							
							console.log(email);
							console.log(nick);
							console.log(gender);
							
							document.getElementById('kakaoEmail').value = email;
							document.getElementById('kakaoNick').value = nick;
							document.getElementById('kakaoGender').value = gender;
							document.kakaologin_frm.submit();
			              	//location.href="/index";
			        	}
			          })
			        },
			        fail: function(err) {
			          alert(JSON.stringify(err));
			        }
			  });
		  }