<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link href='${contextPath}/resources/css/schedule/calendar/main.css' rel='stylesheet'/>
<script src='${contextPath}/resources/js/schedule/calendar/render.js'></script>
<script src='${contextPath}/resources/js/schedule/calendar/main.js'></script>
<script src='${contextPath}/resources/js/schedule/calendar/locales-all.js'></script>
<link href='${contextPath}/resources/css/schedule/schedule_main.css' rel='stylesheet'/>
<script type="text/javascript" src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=97ed58e74b2a1030e3fbd1d29e3272c9"></script>
<script type="text/javascript" src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=97ed58e74b2a1030e3fbd1d29e3272c9&libraries=services,clusterer,drawing"></script>
<style type="text/css">



.search_title_wrapper {
	height: 50px;
	margin: 100px 0px 100px 50px;
}
.search_title_wrapper>p {
	font-size: 50px;
}
.search_title_wrapper>button {
	width: 300px;
	align-content: center;
}

.map_wrapper {
	display: flex;
	width: 1200px;
	border: solid;
	margin-right: 0px;
	height: 300px;
	
}

.map_wrapper>div{
	border: solid;
	display: flex;
	width: 50%;
	
}
.hospital_list{
	border: solid;
	
}



</style>

</head>
<body>


<div class="main">
 <div>
 	<div class="search_title_wrapper">
	 	<p>의료기관 찾기</p><br>
	 	<button id="search_hospName_bnt">병원이름으로 검색</button><button id="search_f_bnt">내 위치로 검색</button>
 		<div class="map_wrapper">
 		<div class="map">지도</div>
 		<div><p>진료과목 (선택)</p></div>
 		</div>
 		
 		<div class="hospital_list">병원 주소 목록</div>
 	</div>
 	<div>
 		
 	
 	</div>
 	
 </div>
 
	
</div>
		
		<footer>
		</footer>
	</div>

<script type="text/javascript">
selectedMenu = 'searchHosp';


</script>

</body>


</html>