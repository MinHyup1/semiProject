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
							<form class="form-auth-small" action="index.php">
								<div class="form-group">
									<a href="#"><button class="btn btn-lg kakao">카카오톡 간편 회원가입</button></a>
								</div>
								<a id="or">또는</a>
								<a><button formaction="/test/join" type="submit" class="btn btn-primary btn-lg btn-block">일반 회원가입</button></a>
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