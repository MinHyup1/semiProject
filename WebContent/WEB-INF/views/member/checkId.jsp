<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="fullscreen-bg">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href='${contextPath}/resources/css/member/check.css' rel='stylesheet'/>
</head>
<body>
	<div id="wrapper">
		<div class="vertical-align-wrap">
			<div class="vertical-align-middle">
				<div class="auth-box">
					<h5>회원님의 아이디 찾기가 완료되었습니다.</h5>
					<form action="" method="POST" class="form">
						<table>
							<tr>
								<td class="title">이름</td>
								<td class="value">${authentication.name}</td>
							</tr>
							<tr>
								<td class="title">이메일</td>
								<td class="value">${authentication.email}</td>
							</tr>
							<tr>
								<td class="title">휴대폰 번호</td>
								<td class="value">${authentication.phone}</td>
							</tr>
							<tr>
								<td class="title">비밀번호</td>
								<td class="value">${authentication.id}</td>
							</tr>
						</table>
					</form>
					<div class="footBtwrap">
						<button id="btn_login" onclick="location.href='/member/loginPage'">로그인</button>
						<button id="findPw" onclick="location.href='/member/findPassword'">비밀번호찾기</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>