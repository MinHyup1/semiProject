<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href='${contextPath}/resources/css/member/kakaoMemberInfo.css' rel='stylesheet'/>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<title>Insert title here</title>
</head>
<body>
<div id="wrapper">
<form id="frm_memberInfo">
	<table border=1 width="1000" height="500" bordercolor="gray" cellspacing=0>
		<tr class="bTag">
			<td class="bTag" colspan="2" align="center"><b>My Page</b>
		</tr>
		<tr class="aTag">
			
		</tr>
		<tr>
			<td width="200" align="center" class="title">* 이름</td>
			<td width="500">
				&nbsp;${authentication.name}
			</td>
		</tr>
		<tr>
			<td width="200" align="center" class="title">* 닉네임</td>
			<td width="500">
				&nbsp;${authentication.nick}
			</td>
		</tr>
		<tr>
			<td width="200" align="center" class="title">* 휴대전화</td>
			<td width="500">
				&nbsp;${authentication.phone}
			</td>
		</tr>
		<tr>
			<td width="200" rowspan="3" align="center" class="title">주소</td>
			<td width="200" class="code">
			   &nbsp;${authentication.address}
			</td>
		</tr>
		<tr>
			
		</tr>
		<tr>
			
		</tr>
		<tr>
			<td width="200" align="center" class="title">* 이메일</td>
			<td width="500">
				&nbsp;${authentication.email}	
			</td>
		</tr>
	</table>
		<div class="footBtwrap">
		<c:if test="${empty authentication.password}">
			<a ><input type="button"   onclick="location.href='/member/kakaoMemberForm'" value="회원정보수정" class="fpmgBt1"></a>
		</c:if>
		
		<c:if test="${not empty authentication.password}">
			<a ><input type="button" onclick="location.href='/member/changeForm'" value="회원정보수정" class="fpmgBt1"></a>
		</c:if>
		
			<a id="cancel"href="/index"><input type="button" value="돌아가기" class="fpmgBt2"></a>
			<a style="display: none" href="/member/delete" onclick="unlinkApp()" id="out"><input value="회원탈퇴" class="fpmgBt3"></a>
		</div>
	
</form>

</div>

<script src='${contextPath}/resources/js/member/kakaoModify.js'></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script src='${contextPath}/resources/js/member/kakaoLogin.js'></script>

</body>
</html>