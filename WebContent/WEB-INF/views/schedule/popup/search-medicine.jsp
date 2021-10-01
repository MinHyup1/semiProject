<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>병원 검색</title>
<link rel="stylesheet" href="${contextPath}/resources/css/all.css">
<link rel="stylesheet" href="${contextPath}/resources/css/schedule/popup/popup.css">
</head>
<body>
	<div class="title"><i class="fas fa-book-medical"></i> 의약품 찾기</div>
	<div class="wrap">
		<div class="wrap_search">
			<span class="desc">> 복용하고 있는 약 이름을 찾아 저장해보세요.</span>
			<div class="search">
				<input type="text", placeholder="ex) 타이레놀...">
				<button onclick="searchMed()">검색</button>
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
					<tr>
						<td>이미지</td>
						<td>*****약</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
<script src='${contextPath}/resources/js/schedule/popup/search-medicine.js'></script>
</html>