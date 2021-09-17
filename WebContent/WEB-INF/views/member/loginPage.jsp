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
							<form class="form-auth-small" action="">
								<div class="form-group">
									<a href="#"><button class="btn btn-lg kakao">카카오톡 간편 로그인</button></a>
								</div>
								<a id="or">또는</a>
								<div class="form-group">
									<label for="signin-id" class="control-label sr-only">아이디</label>
									<input type="text" class="form-control" id="signin-email" placeholder="아이디를 입력하세요.">
								</div>
								<div class="form-group">
									<label for="signin-password" class="control-label sr-only">비밀번호</label>
									<input type="password" class="form-control" id="signin-password" placeholder="비밀번호를 입력하세요.">
								</div>
								<a href="#"><button type="submit" class="btn btn-primary btn-lg btn-block">기존 회원 로그인</button></a>
								
									<span class="helper-text"><a href="#">아이디 찾기 | </a><a href="#">비밀번호 찾기 | </a><a href="#">가입하기</a></span>
								
							</form>
						</div>
					</div>

					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>