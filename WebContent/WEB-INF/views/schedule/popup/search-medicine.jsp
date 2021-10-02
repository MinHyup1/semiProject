<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>의약품 검색</title>
<link rel="stylesheet" href="${contextPath}/resources/css/all.css">
<link rel="stylesheet" href="${contextPath}/resources/css/schedule/popup/popup-for-medicine.css">
</head>
<body>
	<div class="title"><i class="fas fa-book-medical"></i> 의약품 찾기</div>
	<div class="wrap">
		<div class="wrap_search">
			<span class="desc">> 복용하고 있는 약 이름을 찾아 저장해보세요.</span>
			<div class="search">
				<form action="/Medicine/medicineInfoInPopup" class="search">
					<input type="text" name="medName" placeholder="ex) 타이레놀...">
					<button onclick="searchMed()">검색</button>
				</form>
			</div>
		</div>
		<div>
			<table>
				<thead>
					<tr>
						<th class="name" style="width: 40%">약 이미지</th>
						<th class="address" style="width: 60%">약 이름</th>
					</tr>
				</thead>
				<tbody>
					<!-- 검색된 병원 리스트 -->
					<c:choose>
						<c:when test="${empty medicineList}">
							<tr><td colspan="2">검색된 결과가 없습니다.</td></tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${medicineList}" var="med">
								<tr class='line'>
									<td><a class='medImg'><img src="${med.medImg}"style="width: 100px; height: auto;"/></a></td>
									<td><a class='medName'>${med.medName}</a><span class="code">${med.medNum}</span></td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>
</body>
<script src='${contextPath}/resources/js/schedule/popup/search-medicine.js'></script>
</html>