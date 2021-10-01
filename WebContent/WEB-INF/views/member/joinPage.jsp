<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="fullscreen-bg">
<head>
<link href='${contextPath}/resources/css/member/joinPage.css' rel='stylesheet'/>
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
								<p class="joinInfo">회원가입</p>
								<p class="lead">카카오톡으로 간편하게 회원가입 하세요.</p>
							</div>
							<form class="form-auth-small" name="kakaologin_frm" action="/member/kakaoLogin" method="post">
								<div class="form-group" id="kakaoLogin">
									<div class="kakaoBtn">
										<!-- 카카오 정보 넣어줄 input 숨김처리로 넣어놓음 -->
										<input type="hidden" name="kakaoId" id="kakaoId" />
										<input type="hidden" name="kakaoEmail" id="kakaoEmail" />
										<input type="hidden" name="kakaoNick" id="kakaoNick" />
										<input type="hidden" name="kakaoGender" id="kakaoGender" />
										<a href="javascript:loginWithKakao();" id="custom-login-btn">
											<img src="../resources/img/kakao_login_medium_wide.png" />
											<!-- <button class="btn btn-lg kakao">카카오톡 간편 로그인</button> -->
										</a>
									</div>
								</div>
							</form>
							<form class="form-auth-small">
								
								<a id="or">또는</a>
								<a><button formaction="/member/basicJoin" type="submit" class="btn btn-primary btn-lg btn-block">일반 회원가입</button></a>
							</form>
						</div>
					</div>

					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
	
  <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
  <script src='${contextPath}/resources/js/member/kakaoLogin.js'></script>
  
</body>
</html>