<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href='${contextPath}/resources/css/member/check.css' rel='stylesheet'/>
<style>
button{height: 50px;width: 150px;}
</style>
</head>
<body>
<div id="wrapper">
		<h5>회원님의 아이디 찾기가 완료되었습니다.</h5>
		<form action="find" method="POST" class="form">
			<div style="min-height: 110px;">
			이름: ${authentication.name}<br>
			이메일 : ${authentication.email}<br>
			휴대폰 번호 : ${authentication.phone}<br>
			아이디 : ${authentication.id}<br>
			</div>
			<br>
			</form>
			<span>
		<button  id="btn_login" onclick="location.href='loginPage.jsp'">로그인 </button>
		<button style="background-color:lightgray;"onclick="location.href='findPassword.html'"  >비밀번호 찾기 </button>
		</span><br>
	</div>  
</body>
</html>