<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">

	#wrapper {
		margin-top: 40px;
	}

	table {
		border: 1px solid #ddd;
		background-color: #fff;
		width: 80%;
		margin : 0 auto;
		margin-bottom: 20px;
	}
	
	.title {
		font-size: 16px;
		background-color: #f7f7f7;
		color: #4D4C4C;
	}
	
	b {
		color: #4D4C4C;
		font-size: 22px;
		font-weight: bolder;
	}
	
	.inputInfo, .check{
		font-size: 13px;
		color: #4D4C4C;
	}
	
	input {
		margin-left: 10px; 
	}
	

	* {
		margin: 0;
		padding: 0;
		box-sizing: border-box
	}
	
	body {
		min-width: 715px;
		background-color: #f7f7f7;
	}
	
	a {
		text-decoration: none;
	}

	.footBtwrap {
		display: flex;
		position: relative;
		justify-content: center;
		width: 80%;
		margin: 0 auto;
		margin-top: 15px;
		margin-bottom: 15px;
	}
	
	.footBtwrap>a {
		position: relative;
		width: 30%;
		height: 60px;
	}
	
	#out {
		left: 20%;
		width: 20%;
	}
	
	#modify{
		left: 12%;
	}
	
	#cancel{
		left: 7%;
	}
	
	.fpmgBt1, .fpmgBt2{
		display: block;
		width: 80%;
		height: 100%;
		font-size: 20px;
		text-align: center;
		line-height: 60px;
		border: 0;
		border-radius: 5px;
	}
	
	.fpmgBt1 {
		background-color: #00AAFF;
		color: #fff;
	}
	
	.fpmgBt2 {
		background-color: #fff;
		color:#888;
		border: 1.5px solid #ddd;
	}

	.fpmgBt3 {
		display: block;
		width: 40%;
		height: 30px;
		font-size: 1vh;
		text-align: center;
		line-height: 30px;
		background-color: lightgray;
		color:gray;
		border: 1px;
		border-radius: 5px;
	}

	.aTag {
		color: #4D4C4C;
		font-size: 6px;
		border-top: none;
	}

	.bTag, .code{
		border-bottom: none;
	}
	
	.ad {
	 	border-top: none;
	}
	
	
	
	
</style>
<title>Insert title here</title>
</head>
<body>
<div id="wrapper">
<form action="" id="memberInfo">
	<table border=1 width="1000" height="500" bordercolor="gray" cellspacing=0>
		<tr class="bTag">
			<td class="bTag" colspan="2" align="center"><b>회원정보수정</b>
		</tr>
		<tr class="aTag">
			<td class="aTag" colspan="2" align="right">
			<a>*필수입력사항</a></td>
		</tr>
		<tr>
			<td width="200" align="center" class="title">* 아이디</td>
			<td class="inputInfo" width="500"><input type="text" name="id" readonly/>
			</td>
		</tr>
		<tr>
			<td width="200" align="center" class="title">* 비밀번호</td>
			<td class="inputInfo" width="500"><input type="password" name="password" required>
				(특수문자+영어+숫자, 8 ~ 20 글자)</td>
		</tr>
		<tr>
			<td width="200" align="center" class="title">* 비밀번호 확인</td>
			<td class="inputInfo" width="500"><input type="password" name="passwordCheck" required>
			</td>
		</tr>
		<tr>
			<td width="200" align="center" class="title" >* 이름</td>
			<td width="500"><input type="text" name="userName" readonly/></td>
		</tr>
		<tr>
			<td width="200" align="center" class="title">* 닉네임</td>
			<td class="inputInfo" width="500"><input type="text" name="nick" required> (2글자 이상)
				<input class="check" type="button" value="중복체크"></td>
		</tr>
		<tr>
			<td width="200" align="center" class="title">* 휴대전화</td>
			<td width="500">
				<input type="text" name="tell" maxlength="20" required>
				<input class="check" type="button" value="중복체크"></td>
		</tr>
		<tr>
			<td width="200" rowspan="3" align="center" class="title">주소</td>
			<td width="200" class="code">
				<input type="text" size="6" name="addressCode1">
			     -
			    <input type="text" size="6" name="addressCode2">
				<input class="check" type="button" value="우편번호 검색"></td>
		</tr>
		<tr>
			<td class="inputInfo code ad"><input type="text" size="50" name="address1"> 기본주소</td>
		</tr>
		<tr>
			<td class="inputInfo ad"><input type="text" size="50" name="address2">
				나머지주소(선택입력가능)</td>
		</tr>
		<tr>
			<td width="200" align="center" class="title">* 이메일</td>
			<td width="500"><input type="email" size="50" name="email" maxlength="20" required>
				<input class="check" type="button" value="중복체크"></td>
		</tr>
	</table>

		<div class="footBtwrap">
			<a id="modify" href="https://www.naver.com/"><button class="fpmgBt1">회원정보수정</button></a>
			<a id="cancel" href="#"><button class="fpmgBt2">취소</button></a>
			<a id="out" href="#"><button class="fpmgBt3">회원탈퇴</button></a>
		</div>
	
</form>
</div>

</body>
</html>