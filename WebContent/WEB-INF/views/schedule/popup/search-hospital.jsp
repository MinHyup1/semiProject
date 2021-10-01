<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>병원 검색</title>
<link rel="stylesheet" href="${contextPath}/resources/css/all.css">
<link rel="stylesheet" href="${contextPath}/resources/css/schedule/popup/popup.css">
</head>
<body>
	<div class="title"><i class="fas fa-book-medical"></i> 병원 찾기</div>
	<div class="wrap">
		<div class="wrap_search">
			<span class="desc">> 진료받는 병원의 이름을 찾아 저장해보세요.</span>
			<div class="search">
				<form action="/HospitalController/searchByHospitalNameInPopup" class="search">
					<input type="text" name="input" placeholder="ex) ***병원">
					<button onclick="searchHosp()">검색</button>
				</form>
			</div>
		</div>
		<div>
			<table>
				<thead>
					<tr>
						<th class="name">병원명</th>
						<th class="address">병원 주소</th>
					</tr>
				</thead>
				<tbody>
					<!-- 검색된 병원 리스트 -->
					<c:choose>
						<%-- <c:when test="${}"> --%>
					</c:choose>
					<tr>
						<td>${hospList[0]}</td>
						<td>**도 **시 **구 ******</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
<script src='${contextPath}/resources/js/schedule/popup/search-hospital.js'></script>
</html>