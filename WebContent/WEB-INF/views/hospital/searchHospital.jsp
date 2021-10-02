<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<style type="text/css">

.search_title_wrapper {
	height: 50px;
	margin: 20px 0px 100px 50px;
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
	height: 300px;
	justify-content: center;
	
	
}

.map_wrapper>div{
	border: solid;
	display: flex;
	width: 90%;
	
}
.hospital_list{
	
}
.check_box{
    height: 100%;
    padding: 8px 0 10px 17px;
}
.check_box label {
    margin: 0px;
    font-size: 12px;
    font-weight: bold;
    letter-spacing: -1px;
    width: 150px;
    display: inline-block;
}

.d_search_box .last_search input.searchByName {
    text-align: center;
    width: 80px;
    height: auto;
    padding: 3px 0 3px 0;
    background: #0080c0;
    border-top: none;
    border-right: 1px solid #5c90a6;
    border-bottom: 1px solid #5c90a6;
    border-left: none;
    color: #fff;
    font-weight: bold;
    font-size: 14px;
    text-indent: 0;
    cursor: pointer;
    }
 button.searchByName{
  text-align: center;
    width: 80px;
    height: auto;
    padding: 3px 0 3px 0;
    background: #0080c0;
    border-top: none;
    border-right: 1px solid #5c90a6;
    border-bottom: 1px solid #5c90a6;
    border-left: none;
    color: #fff;
    font-weight: bold;
    font-size: 14px;
    text-indent: 0;
    cursor: pointer;
 }
 button.searchByMyLocation{
  text-align: center;
    width: 80px;
    height: auto;
    padding: 3px 0 3px 0;
    background: #0080c0;
    border-top: none;
    border-right: 1px solid #5c90a6;
    border-bottom: 1px solid #5c90a6;
    border-left: none;
    color: #fff;
    font-weight: bold;
    font-size: 14px;
    text-indent: 0;
    cursor: pointer;
 }

    
.input_l_s2 {
    text-align: center;
    width: 80px;
    height: auto;
    padding: 3px 0 3px 0;
    background: #6e6e6e;
    border-top: none;
    border-right: 1px solid #676767;
    border-bottom: 1px solid #676767;
    border-left: none;
    color: #fff;
    font-weight: bold;
    font-size: 14px;
    text-indent: 0;
    cursor: pointer;
}

table.hospital_info_table {
  border-collapse: separate;
  border-spacing: 1px;
  text-align: center;
  line-height: 1.5;
  margin: 20px 10px;
}
table.hospital_info_table th {
  width: 155px;
  padding: 10px;
  text-align: center;
  font-weight: bold;
  vertical-align: top;
  color: #fff;
  background: #6495ED ;
}
table.hospital_info_table td {
  width: 155px;
  padding: 10px;
  vertical-align: center;
  border-bottom: 1px solid #ccc;
  background: #eee;
}
.

    


</style>

</head>
<body>
<div class="main">
 <div>
 	<div class="search_title_wrapper">
 		<div class="map_wrapper">
 		<div class="map"><div id="map" style="width:100%;height:100%;">
 		</div>
 		</div></div>
 		<div><div id="tabNav0101" >
 		<form action="/HospitalController/searchByName" method="get">
		<div class="d_search_box">
		
			<div class="check_wrap">
			
				<div class="sb_con">
				
					<div class="check_box">
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="내과체크" value="01"> 내과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="신경과체크" value="02"> 신경과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="정신건강의학과체크" value="03"> 정신건강의학과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="외과체크" value="04"> 외과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="정형외과체크" value="05"> 정형외과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="신경외과체크" value="06"> 신경외과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="흉부외과체크" value="07"> 흉부외과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="성형외과체크" value="08"> 성형외과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="마취통증의학과체크" value="09"> 마취통증의학과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="산부인과체크" value="10"> 산부인과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="소아청소년과체크" value="11"> 소아청소년과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="안과체크" value="12"> 안과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="이비인후과체크" value="13"> 이비인후과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="피부과체크" value="14"> 피부과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="비뇨기과체크" value="15"> 비뇨기과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="영상의학과체크" value="16"> 영상의학과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="방사선종양학과체크" value="17"> 방사선종양학과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="병리과체크" value="18"> 병리과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="진단검사의학과체크" value="19"> 진단검사의학과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="결핵과체크" value="20"> 결핵과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="재활의학과체크" value="21"> 재활의학과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="핵의학과체크" value="22"> 핵의학과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="가정의학과체크" value="23"> 가정의학과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="응급의학과체크" value="24"> 응급의학과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="직업환경의학과체크" value="25"> 직업환경의학과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="예방의학과체크" value="26"> 예방의학과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="치과체크" value="49"> 치과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="구강악안면외과체크" value="50"> 구강악안면외과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="치과보철과체크" value="51"> 치과보철과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="치과교정과체크" value="52"> 치과교정과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="소아치과체크" value="53"> 소아치과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="치주과체크" value="54"> 치주과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="치과보존과체크" value="55"> 치과보존과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="구강내과체크" value="56"> 구강내과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="영상치의학과체크" value="57"> 영상치의학과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="구강악안면방사선과체크" value="57"> 구강악안면방사선과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="구강병리과체크" value="58"> 구강병리과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="예방치과체크" value="59"> 예방치과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="한방내과체크" value="80"> 한방내과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="한방부인과체크" value="81"> 한방부인과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="한방소아과체크" value="82"> 한방소아과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="한방안·이비인후·피부과체크" value="83"> 한방안·이비인후·피부과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="한방안이비인후피부과체크" value="83"> 한방안이비인후피부과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="한방신경정신과체크" value="84"> 한방신경정신과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="침구과체크" value="85"> 침구과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="한방재활의학과체크" value="86"> 한방재활의학과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="사상체질과체크" value="87"> 사상체질과</label>
					
					<label class="checkbox"><input type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1" title="한방응급체크" value="88"> 한방응급</label>
					
					</div>
				</div>
			</div>
			
			<div class="last_search">
				<div class="title">병원명</div>
				<div class="sb_con">
					
					<input type ="text"  name="input"id="input"  class="input_l_t1" value="${input}" title="기관명 적기">
					<button class="searchByName" >검색</button> 
					<c:if test="${not empty msg}"><p id="is_empty" style="color: red">${msg}</p></c:if>
				</div>
			</div>
			
		</div>
		</form>
		
	</div></div>
 		
 		<div class="hospital_list">
 		
 		<ul class="listWrap">
 			
 			
 		<c:if test="${not empty requestScope.hospList}">  
		 	<table class="hospital_info_table" >
				<th style="width: 5%;" >NO.</th>
				<th>기관명</th>
				<th style="width: 30%;">주소</th>
				<th>전화</th>
				<th>홈페이지</th>
				<c:forEach var="i" begin="0" step="1" end="${siez -1}" varStatus="status">
				<tr><!-- 첫번째 줄 시작 -->
				    <td class="num1" valign="${status.count -1}">${status.count}</td>
				   
				    <td><a onclick="createKakaoMap('${requestScope.hospList[i].xPos}','${requestScope.hospList[i].yPos}','${requestScope.hospList[i].hospName}')">${requestScope.hospList[i].hospName}</a></td>
				    <td>${requestScope.hospList[i].address}</td>
				    <td>${requestScope.hospList[i].hospTell}</td>
				    <td>
				    <c:choose>
				    	<c:when test="${requestScope.hospList[i].hospUrl == '-'}">
					    	<p style="align-content: center;">-</p></td>
				    	</c:when>
				    	<c:otherwise>
				    		<a href="${requestScope.hospList[i].hospUrl}" target='_blank'>
					   		 ${requestScope.hospList[i].hospUrl}</a></td>
					   	</c:otherwise>
				    </c:choose>
				</tr><!-- 첫번째 줄 끝 -->
 			</c:forEach>
 			 </table>
 		</c:if>
 		
 		
 		</div>
 	</div>
 	<div>
 		
 	
 	</div>
 	
 </div>
 
	
</div>
		
		<footer>
		</footer>
		
		</div>
<script type="text/javascript" src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=97ed58e74b2a1030e3fbd1d29e3272c9&libraries=services"></script>
<script type="text/javascript">

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


	  

</script>

</body>


</html>