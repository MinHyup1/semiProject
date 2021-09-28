<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<script type="text/javascript" src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=a91d097e5c5d9764f91631e0ac40e115"></script>
</head>
<body>

<div id="map" style="width:1000px;height:800px;"></div>

<script>

let latlng = () => {
	if(!navigator.geolocation){
		return;
	}else{
		return new Promise((resolve,reject)=>{
			navigator.geolocation.getCurrentPosition((position) => {
				resolve(position.coords);
			});
		})
	}
}

let drawMap = async () => {
	
	let coords = await latlng();
	
	var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스
	var options = { //지도를 생성할 때 필요한 기본 옵션
		center: new kakao.maps.LatLng(coords.latitude, coords.longitude), //지도의 중심좌표.
		level: 3 //지도의 레벨(확대, 축소 정도)
	};

	var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	mapOption = { 
	    center: new kakao.maps.LatLng(coords.latitude, coords.longitude), // 지도의 중심좌표
	    level: 5 // 지도의 확대 레벨
	};

	var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

	//일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
	var mapTypeControl = new kakao.maps.MapTypeControl();

	//지도에 컨트롤을 추가해야 지도위에 표시됩니다
	//kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
	map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

	//지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
	var zoomControl = new kakao.maps.ZoomControl();
	map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);

	// 장소 검색 객체를 생성합니다
	var ps = new kakao.maps.services.Places(map); 

	// 카테고리로 은행을 검색합니다
	ps.categorySearch('PM9', placesSearchCB, {useMapBounds:true}); 


	map.setMapTypeId(kakao.maps.MapTypeId.ROADMAP);
	
}

drawMap();

 
</script>
</body>
</html>