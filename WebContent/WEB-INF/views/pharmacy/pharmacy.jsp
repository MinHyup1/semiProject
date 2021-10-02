<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<script type="text/javascript" src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=a91d097e5c5d9764f91631e0ac40e115"></script>
<style type="text/css">
.map_wrapper {
	display: flex;
	height: 300px;
	justify-content: center;

}

.map_wrapper>div{
	border: solid;
	display: flex;
	width: 90%;
	
}
table.pharmacyList {
  border-collapse: separate;
  border-spacing: 1px;
  text-align: center;
  line-height: 1.5;
  margin: 20px 10px;
}
table.pharmacyList th {
  width: 155px;
  padding: 10px;
  text-align: center;
  font-weight: bold;
  vertical-align: top;
  color: #fff;
  background: #6495ED ;
}
table.pharmacyList td {
  width: 155px;
  padding: 10px;
  vertical-align: center;
  border-bottom: 1px solid #ccc;
  background: #eee;
}
</style>

</head>
<body>
<!-- 지도를 표시할 div 입니다 -->
<div class= "main">
	<p style="font-size: 50px; display: flex;"> 약국 찾기 </p><br>
	<div class="map_wrapper">
	<div class="map">
	<div id="map" style="width:100%;height:100%;"></div>
	</div>
	</div>
		<form method="post" action="byaddress" >
		<label>검색할 지역 :     </label>
		<input type="text" class="byaddress" name="byaddress" placeholder="동이름 또는 도로명을 입력하세요">
		<button>확인</button>
		</form>
		<form method="post" action="byname" >
		<label>검색할 약국 이름 : </label>
		<input type="text" class="byname" name="byname" placeholder="약국 이름을 입력하세요">
		<button>확인</button>
		</form>
	<c:choose>	
	<c:when test="${not empty requestScope.pharmacyList}">  
		 	<table border="1" class="pharmacyList" style ="text-align: center">
				<th style="width:20%;">약국 이름</th>
				<th style="width:60%;">약국 주소</th>				
				<c:forEach var="i" begin="0" step="1" end="${size -1}" varStatus="status">
				<tr><!-- 첫번째 줄 시작 -->
				    <td><a onclick="createKakaoMap(${requestScope.pharmacyList[i].pharLat},${requestScope.pharmacyList[i].pharLon})">${requestScope.pharmacyList[i].pharName}</a></td>
				    <td>${requestScope.pharmacyList[i].pharLoc}</td>
				    				    
				</tr><!-- 첫번째 줄 끝 -->
 			</c:forEach>
 			 </table>
 		</c:when>
 		<c:when test="${empty requestScope.pharmacyList}">
 		<br>
 		<table border="1" class="medList" style ="text-align: center">
 		<th>검색결과가 없습니다.</th>
 		</table>
 		</c:when>
	</c:choose>
</div>
<script>
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 5 // 지도의 확대 레벨 
    }; 

var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다

// HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
if (navigator.geolocation) {
    
    // GeoLocation을 이용해서 접속 위치를 얻어옵니다
    navigator.geolocation.getCurrentPosition(function(position) {
        
        var lat = position.coords.latitude, // 위도
            lon = position.coords.longitude; // 경도
        
        var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
            message = '<div style="padding:5px;">현재 위치</div>'; // 인포윈도우에 표시될 내용입니다
        
       
        map.setCenter(locPosition);
            
      });
    
} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
    
    var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),    
        message = 'geolocation을 사용할수 없어요..'     
    
}      
function displayMarker(locPosition, message) {

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({  
        map: map, 
        position: locPosition
    }); 
    
    var iwContent = message, // 인포윈도우에 표시할 내용
        iwRemoveable = true;

    // 인포윈도우를 생성합니다
    var infowindow = new kakao.maps.InfoWindow({
        content : iwContent,
        removable : iwRemoveable
    });
    
    // 인포윈도우를 마커위에 표시합니다 
    infowindow.open(map, marker);
    
    // 지도 중심좌표를 접속위치로 변경합니다
    map.setCenter(locPosition);      
}
function createKakaoMap(pharLat,pharLon) {
	  
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	  mapOption = {
	      center: new kakao.maps.LatLng(pharLat, pharLon), // 지도의 중심좌표
	      level: 4, // 지도의 확대 레벨
	      mapTypeId : kakao.maps.MapTypeId.ROADMAP // 지도종류
	  }; 

	  // 지도를 생성한다 
	  var map = new kakao.maps.Map(mapContainer, mapOption); 

	  // 지도에 확대 축소 컨트롤을 생성한다
	  var zoomControl = new kakao.maps.ZoomControl();

	  // 지도의 우측에 확대 축소 컨트롤을 추가한다
	  map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
	  
	//마커를 표시할 위치와 title 객체 배열입니다 
	  var positions = [
	      {
	      	content: '<div>${requestScope.pharmacyList[0].pharName}</div>', 
	          title: '${requestScope.pharmacyList[0].pharName}', 
	          latlng: new kakao.maps.LatLng(pharLat, pharLon)
	      }
	      
	  ];
	  for (var i = 0; i < positions.length; i ++) {
		    // 마커를 생성합니다
		    var marker = new kakao.maps.Marker({
		        map: map, // 마커를 표시할 지도
		        position: positions[i].latlng // 마커의 위치
		    });

		    // 마커에 표시할 인포윈도우를 생성합니다 
		    var infowindow = new kakao.maps.InfoWindow({
		        content: positions[i].content // 인포윈도우에 표시할 내용
		    });

		    // 마커에 mouseover 이벤트와 mouseout 이벤트를 등록합니다
		    // 이벤트 리스너로는 클로저를 만들어 등록합니다 
		    // for문에서 클로저를 만들어 주지 않으면 마지막 마커에만 이벤트가 등록됩니다
		    kakao.maps.event.addListener(marker, 'mouseover', makeOverListener(map, marker, infowindow));
		    kakao.maps.event.addListener(marker, 'mouseout', makeOutListener(infowindow));
		}
	
}
</script>
</body>
</html>