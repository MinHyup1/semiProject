<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
</head>
<body>
<form>
<div class="main">
	<button formaction="/test/join">회원가입</button>
	<button formaction="/test/checkId">아이디확인</button>
	<button formaction="/test/checkpassword">패스워드확인</button>
	<button formaction="/test/findId">아이디찾기</button>
	<button formaction="/test/findPassword">패스워드찾기</button>
	<button formaction="/test/memberinfo">회원정보</button>
	
	<button formaction="/test/schedule">진료스케줄</button>
	<button formaction="/test/searchHos">병원검색</button>
	<button formaction="/covid/covidInfo">코로나현황</button>

	
</div>


</form>
</body>
</html>