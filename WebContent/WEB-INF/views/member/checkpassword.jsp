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
	margin-left: 75px;
	 background:lightblue; border: none; 
}
</style>
</head>
<body>
<div id="wrapper">
		<h5>회원님의 비밀번호 찾기가 완료되었습니다.</h5>
		<form action="login" method="POST" class="form">
			<div>
			이름:이기정<br>
			아이디 : dlrlwjd1313<br>
			비밀번호 : qawsed1234!<br>
			</div>
			<br>
			<button style="height: 50px;width: 150px;" id="btn_login" >로그인 </button>
		</form>
	</div> 
</body>
</html>