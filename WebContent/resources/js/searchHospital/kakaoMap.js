/**
 * 
 */


selectedMenu = 'searchHosp';	

//마커를 클릭하면 장소명을 표출할 인포윈도우 입니다



//HTML5의 geolocation으로 사용할 수 있는지 확인합니다 
if (navigator.geolocation) {

// GeoLocation을 이용해서 접속 위치를 얻어옵니다
navigator.geolocation.getCurrentPosition(function(position) {
    
    var lat = position.coords.latitude, // 위도
        lon = position.coords.longitude; // 경도
    

        var infowindow = new kakao.maps.InfoWindow({zIndex:1});

        var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
        mapOption = { 
            center: new kakao.maps.LatLng(lat, lon), // 지도의 중심좌표
            level: 3 // 지도의 확대 레벨 
        };

        var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
        
      //장소 검색 객체를 생성합니다
        var ps = new kakao.maps.services.Places(map); 

        //카테고리로 병원을 검색합니다
        ps.categorySearch('HP8', placesSearchCB, {useMapBounds:true,radius:10000}); 


        // 키워드 검색 완료 시 호출되는 콜백함수 입니다
        function placesSearchCB (data, status, pagination) {
            if (status === kakao.maps.services.Status.OK) {
                for (var i=0; i<data.length; i++) {
                    displayMarker(data[i]);    
                }       
            }
        }

        // 지도에 마커를 표시하는 함수입니다
        function displayMarker(place) {
            // 마커를 생성하고 지도에 표시합니다
            var marker = new kakao.maps.Marker({
                map: map,
                position: new kakao.maps.LatLng(place.y, place.x) 
            });

            // 마커에 클릭이벤트를 등록합니다
            kakao.maps.event.addListener(marker, 'click', function() {
                // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
                infowindow.setContent('<div style="padding:5px;font-size:12px;">' + place.place_name + '</div>');
                infowindow.open(map, marker);
            });
        }
   
  });

} else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다

var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),    
    message = 'geolocation을 사용할수 없어요..'
}







function createKakaoMap(xPos,yPos,hospName) {
	
	
	
 if(xPos == "-"){
	 alert("지도 정보가 없습니다.");
	 return;
 }
 
 window.scrollTo( 0, 0 );
	
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
  mapOption = {
      center: new kakao.maps.LatLng(yPos, xPos), // 지도의 중심좌표
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
      	content: '<div>' + hospName +'</div>', 
          title: hospName, 
          latlng: new kakao.maps.LatLng(yPos, xPos)
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

	// 인포윈도우를 표시하는 클로저를 만드는 함수입니다 
	function makeOverListener(map, marker, infowindow) {
	    return function() {
	        infowindow.open(map, marker);
	    };
	}

	// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
	function makeOutListener(infowindow) {
	    return function() {
	        infowindow.close();
	    };
	}



  
} 


	  
