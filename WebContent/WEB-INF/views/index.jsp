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
	
	<button formaction="/covid/covidInfo">코로나현황 최신화하기</button><br>
	
	<iframe src="${contextPath}/covid/covid" width="100%" height="800px"></iframe>
	
</div>
<script type="text/javascript"></script>
setTimeout(function () {
location.reload(true);
}, 100);
</form>
</body>
</html>