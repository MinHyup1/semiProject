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
	<div class="title"><i class="fas fa-book-medical"></i> 병원 찾기</div>
	<div class="wrap">
		<div class="wrap_search">
			<span class="desc">> 진료받는 병원의 이름을 찾아 저장해보세요.</span>
			<div class="search">
				<input type="text", placeholder="ex) ***병원">
				<button>검색</button>
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
					<tr>
						<td>**병원</td>
						<td>**도 **시 **구 ******</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>