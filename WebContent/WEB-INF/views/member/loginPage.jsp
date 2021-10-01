<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="fullscreen-bg">
<head>
<script type="text/javascript" src='${contextPath}/resources/js/htmlElementSelector.js'></script>
<script type="text/javascript" src='${contextPath}/resources/js/webUtil.js'></script>
<link href='${contextPath}/resources/css/member/loginPage.css' rel='stylesheet'/>
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
								
									<span class="helper-text"><a href="/member/findId" onclick='openGoogle()' id="a1">아이디 찾기 | </a><a href="/member/findPassword" id="a2">비밀번호 찾기 | </a><a href="/member/joinPage" id="a3">가입하기</a></span>
								
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<button onclick='openNaver()'>네이버</button>
<button onclick='openDaum()'>다음</button>
<button onclick='openGoogle()'>구글</button>
<script type="text/javascript">
   let daumWindow;
   
   let positionX = screen.width/2 - 300;
   let positionY = screen.height/2 - 150;
   

   let openGoogle = () =>{
      let google = createPopup({
                  url:'/member/findId',
                  name:'',
                  width:522,
                  height:302
               });
   }
   
</script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script src='${contextPath}/resources/js/member/kakaoLogin.js'></script>



</body>
</html>