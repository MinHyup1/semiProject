<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#wrapper { 
    border: 1px solid black;
    padding: 5px 20px;
    position: absolute;
    top: 50%;
    left: 50%;
    width: 450px; height: 250px;
    margin-left: -220px;
    margin-top : -170px;
    
    display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
} 
.form{
	width: 300px;
}
.form > div{
	background-color:lightgray;
	display: flex;
	justify-content: center;
	padding-bottom: 7px;
	align-items: center;
	min-height: 100px;
}

button{
	float: none;
	padding: 3px;
	background:lightblue;
	border: none; 
	display: inline;
	height: 50px;width: 150px;
}
</style>
</head>
<body>
<div id="wrapper">
		<h5>회원님의 아이디 찾기가 완료되었습니다.</h5>
		<form action="find" method="POST" class="form">
			<div style="min-height: 110px;">
			이름:이기정<br>
			이메일 : dlrlwjd1313@naver.com<br>
			휴대폰 번호 : 01029116322<br>
			아이디 : dlrlwjd1313<br>
			</div>
			<br>
			</form>
			<span>
		<button  id="btn_login" onclick="location.href='page-login.html'">로그인 </button>
		<button style="background-color:lightgray;"onclick="location.href='findPassword.html'"  >비밀번호 찾기 </button>
		</span><br>
	</div>  
</body>
</html>