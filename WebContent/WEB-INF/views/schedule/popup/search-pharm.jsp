<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>약국 검색</title>
<link rel="stylesheet" href="${contextPath}/resources/css/all.css">
<link rel="stylesheet" href="${contextPath}/resources/css/schedule/popup/popup.css">
</head>
<body>
	<div class="title"><i class="fas fa-book-medical"></i> 약국 찾기</div>
	<div class="wrap">
		<div class="wrap_search">
			<span class="desc">> 약을 처방받은 약국의 이름을 찾아 저장해보세요.</span>
			<div class="search">
				<form action="/pharmacy/searchByNameInPopup" class="search">
					<input type="text" name="byname" placeholder="ex) ***약국">
					<button onclick="searchPharm()">검색</button>
				</form>
			</div>
		</div>
		<div>
			<table>
				<thead>
					<tr>
						<th class="name">약국명</th>
						<th class="address">약국 주소</th>
					</tr>
				</thead>
				<tbody>
					<!-- 검색된 병원 리스트 -->
					<c:choose>
						<c:when test="${empty pharmacyList}">
							<tr><td colspan="2" style="text-align: center">검색된 결과가 없습니다.</td></tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${pharmacyList}" var="pharm">
								<tr class='line'>
									<td><a class='hospName'>${pharm.pharName}</a><span class="code">${pharm.pharCode}</span></td>
									<td><a class='hospAddr'>${pharm.pharLoc}</a></td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</div>
</body>
<script src='${contextPath}/resources/js/schedule/popup/search-pharm.js'></script>
</html>