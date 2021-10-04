<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<style type="text/css">
.medName {
	width: 300px;
	height: 30px;
	background-color: lightyellow;
	border-style: groove;
}

table.medList {
	border-collapse: separate;
	border-spacing: 1px;
	text-align: center;
	line-height: 1.5;
	margin: 20px 10px;
}

table.medList th {
	width: 155px;
	padding: 10px;
	text-align: center;
	font-weight: bold;
	vertical-align: top;
	color: #fff;
	background: #6495ED;
}

table.medList td {
	width: 155px;
	padding: 10px;
	vertical-align: center;
	border-bottom: 1px solid #ccc;
	background: #eee;
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
	<c:choose>	
	<c:when test="${not empty requestScope.medicineList}">  
		 	<table border="1" class="medList" style ="text-align: center">
				<th>약 모양</th>
				<th>약 이름</th>
				<th>약 효능</th>
				<th>사용방법</th>
				<th>주의사항</th>
				<c:forEach var="i" begin="0" step="1" end="${size -1}" varStatus="status">
				<tr><!-- 첫번째 줄 시작 -->
					<td><img src="${requestScope.medicineList[i].medImg}"style="width: 80px; height: auto;"/></td>
				    <td>${requestScope.medicineList[i].medName}</td>
				    <td style="width: 40%;">${requestScope.medicineList[i].medEfc}</td>
				    <td style="width: 40%;">${requestScope.medicineList[i].medMethod}</td>
				    <td style="width: 40%;">${requestScope.medicineList[i].medWarn}</td>				    
				</tr><!-- 첫번째 줄 끝 -->
 			</c:forEach>
 			 </table>
 		</c:when>
 		<c:when test="${empty requestScope.medicineList}">
 		<br>
 		<table border="1" class="medList" style ="text-align: center">
 		<td style="width: 200px;">검색결과가 없습니다.</td>
 		</table>
 		</c:when>
	</c:choose>
	</div>
	<footer> </footer>

	<script type="text/javascript">
	selectedMenu = 'searchMedi';	
</script>
</body>
</html>