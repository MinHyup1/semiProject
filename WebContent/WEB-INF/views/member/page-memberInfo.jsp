<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href='${contextPath}/resources/css/member/memberInfo.css' rel='stylesheet'/>
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