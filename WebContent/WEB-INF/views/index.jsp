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
	
	<button formaction="/covid/covidInfo">코로나현황</button><br>

	<iframe src="${contextPath}/covid/covid" width="100%" height="800px"></iframe>
	
</div>


</form>
</body>
</html>