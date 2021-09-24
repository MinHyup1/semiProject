<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="fullscreen-bg">
<head>
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
								<div class="form-group">
									<a id="custom-login-btn" href="javascript:loginWithKakao()"><button class="btn btn-lg kakao" href="/member/index">카카오톡 간편 로그인</button></a>
								</div>
								<a id="or">또는</a>
							<form class="form-auth-small" action="/member/login" method="post">
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

					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
  <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
    <script>
        // SDK를 초기화 합니다. 사용할 앱의 JavaScript 키를 설정해 주세요.
        Kakao.init('cfb699a7c2c194f945a6a34a6be6daa5');

        // SDK 초기화 여부를 판단합니다.
        console.log(Kakao.isInitialized());
        
    </script>
<script type="text/javascript">
  function loginWithKakao() {
    Kakao.Auth.login({
      success: function(authObj) {
        alert(JSON.stringify(authObj))
      },
      fail: function(err) {
        alert(JSON.stringify(err))
      },
    })
  }
</script>
</body>
</html>