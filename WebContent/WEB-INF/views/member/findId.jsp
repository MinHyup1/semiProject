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