<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<style type="text/css">
	
	.medName{
		width: 300px;
		height: 30px;
		background-color: lightyellow;
		border-style: groove;	
	}
</style>
</head>
<body>
	<div class="main">
		<br>
		<label>검색할 약품명 : </label>
		<input type="text" class="medName">
		<button>확인</button>
	</div>

	<footer> </footer>

	<script type="text/javascript">
	selectedMenu = 'searchMedi';
</script>
</body>
</html>