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
	<h1>의 약 품 검 색</h1>
		<form method="post" action="medicineInfo">
		<label>검색할 약품명 : </label>
		<input type="text" class="medName" name="medName">
		<button>확인</button>
		</form>
	<c:if test="${not empty requestScope.medicineList}">  
		 	<table border="1" class="medList" style ="text-align: center">
				<th>약 모양</th>
				<th>약 이름</th>
				<th>약 효능</th>
				<th>사용방법</th>
				<th>주의사항</th>
				<c:forEach var="i" begin="0" step="1" end="${size -1}" varStatus="status">
				<tr><!-- 첫번째 줄 시작 -->
				    <td><img src="${requestScope.medicineList[i].medImg}"style="width: 100px; height: auto;"/></td>
				    <td>${requestScope.medicineList[i].medName}</td>
				    <td>${requestScope.medicineList[i].medEfc}</td>
				    <td>${requestScope.medicineList[i].medMethod}</td>
				    <td>${requestScope.medicineList[i].medWarn}</td>				    
				</tr><!-- 첫번째 줄 끝 -->
 			</c:forEach>
 			 </table>
 		</c:if>
	
	</div>
	<footer> </footer>

	<script type="text/javascript">
	selectedMenu = 'searchMedi';	
</script>
</body>
</html>