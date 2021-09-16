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
h1{
	font-size: 25px;
	padding-bottom: 20px;
}
.form{
	width: 300px;
}
.form > div{
	display: flex;
	justify-content: center;
	padding-bottom: 7px;
	align-items: center;
}
label{
	flex: 1;
	text-align: left;
}
button{
	width: 85px;
	float: right;
	padding: 3px;
}
input {
	padding: 5px;
}

</head>
<body>
<div id="wrapper">
		<h1>아이디 찾기</h1>
		<form action="login" method="POST" class="form">
			<div>
				<label>이름</label>
				<input type="text" id="id" name="id" maxlength="20" placeholder="username" required>
			</div>
			<div>
				<label>이메일</label>
				<input type="email" id="email" name="email" maxlength="20" placeholder="email" required>
			</div>			<div>
				<label>휴대폰 번호</label>
				<input type="tel" id="tell" name="tell" maxlength="20" placeholder="tell" required>
			</div>
			<button style="height: 50px;width: 315px; background:lightblue;border: none;"submit" id="btn_login" >확인 </button>
		</form>
	</div>  
</body>
</html>