<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href='${contextPath}/resources/css/member/find.css' rel='stylesheet'/>
</head>
<body>
	<div id="wrapper">
		<div class="vertical-align-wrap">
			<div class="vertical-align-middle">
				<div class="auth-box">
					<h1>비밀번호 찾기</h1>
					<form action="/member/findPassword-info" method="POST" class="passwordform">
						<!-- <div>
							<label>인증 방법</label>
							<label>이메일<input type="radio" id="email_check" name="check" required></label>
							<label>phone<input type="radio" id="phone_check" name="check" required></label><br>
							<br>
						</div> -->
						<div>
							<label>아이디</label>
							<input type="text" id="userId" name="userId" size="23" maxlength="20" placeholder="id" required>
						</div>
						<div>
							<label>이름</label>
							<input type="text" id="userName" name="userName" size="23" maxlength="20" placeholder="name" required>
						</div>
						<div>
							<label>이메일</label>
							<input type="email" id="email" name="email" size="23" maxlength="20" placeholder="email" required>
						</div>
						<div>
							<label>휴대폰 번호</label>
							<input type="tel" id="tell" name="tell" size="23" maxlength="20" placeholder="tell" required>
						</div>
						<button type="submit">확인</button>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>