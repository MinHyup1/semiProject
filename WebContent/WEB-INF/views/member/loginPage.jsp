<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="fullscreen-bg">
<head>
<link href='${contextPath}/resources/css/member/loginPage.css' rel='stylesheet'/>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div id="wrapper">
		<div class="vertical-align-wrap">
			<div class="vertical-align-middle">
				<div class="auth-box ">
					<div class="left">
						<div class="content">
							<div class="header">
								<div class="logo text-center"><p>MEDIBOOK</p></div>
							</div>
							<form class="form-auth-small" name="kakaologin_frm" action="/member/kakaoLogin" method="post">
								<div class="form-group" id="kakaoLogin">
									<div class="kakaoBtn">
										<!-- 카카오 정보 넣어줄 input 숨김처리로 넣어놓음 -->
										<input type="hidden" name="kakaoEmail" id="kakaoEmail" />
										<input type="hidden" name="kakaoNick" id="kakaoNick" />
										<input type="hidden" name="kakaoGender" id="kakaoGender" />
										<a href="javascript:loginWithKakao();" id="custom-login-btn">
											<img src="../resources/img/kakao_login_medium_wide.png" />  <!-- 버튼 높이 조금 줄이고싶은데 실패함..  -->
											<!-- <button class="btn btn-lg kakao">카카오톡 간편 로그인</button> -->
										</a>
									</div>
								</div>
							</form>
								<a id="or">또는</a>
							<form class="form-auth-small" name="login_frm" action="/member/login" method="post">
								<div class="form-group">
									<label for="signin-id" class="control-label sr-only">아이디</label>
									<input type="text" class="form-control" name="userId" id="userId" placeholder="아이디를 입력하세요." required>
								</div>
								<div class="form-group">
									<label for="signin-password" class="control-label sr-only">비밀번호</label>
									<input type="password" class="form-control" name="password" id="password" placeholder="비밀번호를 입력하세요." required>
								</div>
								<a><button type="submit" class="btn btn-primary btn-lg btn-block">기존 회원 로그인</button></a>
								
									<span class="helper-text"><a href="/member/findId">아이디 찾기 | </a><a href="/member/findPassword">비밀번호 찾기 | </a><a href="/member/joinPage">가입하기</a></span>
								
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
    <script>
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
		  
</script>

</body>
</html>