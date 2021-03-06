 // SDK를 초기화 합니다. 사용할 앱의 JavaScript 키를 설정해 주세요.
Kakao.init('8406376e4a3016276da81540af46ba74');

// SDK 초기화 여부를 판단합니다.
console.log(Kakao.isInitialized());

function loginWithKakao(){
	Kakao.Auth.login({	 
		success: function(authObj) {
			Kakao.Auth.setAccessToken(authObj.access_token);
			console.log(authObj);
			//로그인 성공시, API 호출
			Kakao.API.request({
				url: '/v2/user/me', //사용자 정보를 읽어들이는 고정된 url
				success: function(res) {
				
				
				const id = res.id;		
				const email = res.kakao_account.email;
				const nick = res.properties.nickname;
				const gender = res.kakao_account.gender;
				alert(nick+' 님 환영합니다!');	
				console.log(email);
				console.log(nick);
				console.log(gender);
				console.log(id);
							
				document.getElementById('kakaoId').value = id;
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

function unlinkApp() {
	Kakao.API.request({
		url: '/v1/user/unlink',
		success: function(response) {
			console.log(response);
			alert('success: ' + JSON.stringify(response));
			alert("카카오 계정 연결이 끊겼습니다.");
			location.href="/member/delete";
      	},
		fail: function(error) {
    		console.log(error);
        	alert('fail: ' + JSON.stringify(error));
      	},
    });
	//Kakao.Auth.setAccessToken(undefined);
	
}

function kakaoLogout() {
    if (!Kakao.Auth.getAccessToken()) {
      alert('Not logged in.');
      return;
    }
    Kakao.Auth.logout(function() {
      alert('로그아웃 완료!');
      location.href="/member/logout";
	})
 }