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
.check_box{
	overflow: hidden;
    height: 104px;
    padding: 8px 0 10px 17px;
    border: 1px solid #d7d7d7;
    overflow-y: scroll;
}
.check_box label {
    margin: 0px;
    font-size: 12px;
    font-weight: bold;
    letter-spacing: -1px;
    width: 150px;
    display: inline-block;
}

.d_search_box .last_search input.input_l_s1 {
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
    


</style>

</head>
<body>


<div class="main">
 <div>
 	<div class="search_title_wrapper">
	 	<p>의료기관 찾기</p><br>
	 	<button id="search_hospName_bnt">병원이름으로 검색</button><button id="search_f_bnt">내 위치로 검색</button>
 		<div class="map_wrapper">
 		<div class="map"><div id="map" style="width:100%;height:100%;"></div></div>
 		<div><div id="tabNav0101" style="display: block;">
		<div class="d_search_box">
			<div class="select_b">
				<div class="title">지역선택</div>
				<div class="sb_con">
					<select name="sido_cd" id="sido_cd" class="sc-select02" title="시도 선택" style="position: absolute; top: -999px; left: -999px; opacity: 0; font-size: 11.999px;"><option value="">=시도=</option><option value="110000">서울</option><option value="210000">부산</option><option value="220000">인천</option><option value="230000">대구</option><option value="240000">광주</option><option value="250000">대전</option><option value="260000">울산</option><option value="310000">경기</option><option value="320000">강원</option><option value="330000">충북</option><option value="340000">충남</option><option value="350000">전북</option><option value="360000">전남</option><option value="370000">경북</option><option value="380000">경남</option><option value="390000">제주</option><option value="410000">세종시</option></select><div class="sc-select02 customSelectBox" style="width: 111px; position: relative; display: inline-block; vertical-align: top;"><a href="javascript:void(0)" class="customSelectBoxInner" style="display: inline-block; vertical-align: top; width: 93px; position: absolute; left: 0px; top: 0px;">=시도=</a><span class="customSelectBoxArrow" style="display: inline-block; vertical-align: top; position: absolute; right: 0px;"></span><ul class="customSelectBoxOption" style="margin: 0px; padding: 5px 0px; list-style: none; position: absolute; top: 27px; left: -1px; overflow-y: auto; display: none;"><li><a href="javascript:void(0)" rel="" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">=시도=</a></li><li><a href="javascript:void(0)" rel="110000" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">서울</a></li><li><a href="javascript:void(0)" rel="210000" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">부산</a></li><li><a href="javascript:void(0)" rel="220000" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">인천</a></li><li><a href="javascript:void(0)" rel="230000" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">대구</a></li><li><a href="javascript:void(0)" rel="240000" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">광주</a></li><li><a href="javascript:void(0)" rel="250000" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">대전</a></li><li><a href="javascript:void(0)" rel="260000" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">울산</a></li><li><a href="javascript:void(0)" rel="310000" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">경기</a></li><li><a href="javascript:void(0)" rel="320000" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">강원</a></li><li><a href="javascript:void(0)" rel="330000" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">충북</a></li><li><a href="javascript:void(0)" rel="340000" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">충남</a></li><li><a href="javascript:void(0)" rel="350000" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">전북</a></li><li><a href="javascript:void(0)" rel="360000" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">전남</a></li><li><a href="javascript:void(0)" rel="370000" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">경북</a></li><li><a href="javascript:void(0)" rel="380000" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">경남</a></li><li><a href="javascript:void(0)" rel="390000" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">제주</a></li><li><a href="javascript:void(0)" rel="410000" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">세종시</a></li></ul></div>
					<select name="sggu_cd" id="sggu_cd" class="sc-select02" title="시군구 선택" style="position: absolute; top: -999px; left: -999px; opacity: 0; font-size: 11.999px;">
						
					<option value="">=시군구=</option></select><div class="sc-select02 customSelectBox" style="width: 111px; position: relative; display: inline-block; vertical-align: top;"><a href="javascript:void(0)" class="customSelectBoxInner" style="display: inline-block; vertical-align: top; width: 93px; position: absolute; left: 0px; top: 0px;">=시군구=</a><span class="customSelectBoxArrow" style="display: inline-block; vertical-align: top; position: absolute; right: 0px;"></span><ul class="customSelectBoxOption" style="margin: 0px; padding: 5px 0px; list-style: none; position: absolute; top: 27px; left: -1px; overflow-y: auto; display: none;"><li><a href="javascript:void(0)" rel="" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">=시군구=</a></li></ul></div>
					<select name="emdong_nm" id="emdong_nm" class="sc-select02" title="읍면동선택" style="position: absolute; top: -999px; left: -999px; opacity: 0; font-size: 11.999px;">
						
					<option value="">=읍면동=</option></select><div class="sc-select02 customSelectBox" style="width: 111px; position: relative; display: inline-block; vertical-align: top;"><a href="javascript:void(0)" class="customSelectBoxInner" style="display: inline-block; vertical-align: top; width: 93px; position: absolute; left: 0px; top: 0px;">=읍면동=</a><span class="customSelectBoxArrow" style="display: inline-block; vertical-align: top; position: absolute; right: 0px;"></span><ul class="customSelectBoxOption" style="margin: 0px; padding: 5px 0px; list-style: none; position: absolute; top: 27px; left: -1px; overflow-y: auto; display: none;"><li><a href="javascript:void(0)" rel="" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">=읍면동=</a></li></ul></div>
				</div>
			</div>
			<div class="select_b">
				<div class="title">의료기관선택</div>
				<div class="sb_con">
					<select id="cl_cd1" name="cl_cd1" class="sc-select02" title="의료기관선택" style="position: absolute; top: -999px; left: -999px; opacity: 0; font-size: 11.999px;">
						
					<option value="">=전체=</option><option value="75">보건의료원</option><option value="71">보건소</option><option value="72">보건지소</option><option value="73">보건진료소</option><option value="11">종합병원</option><option value="21">병원</option><option value="28">요양병원</option><option value="31">의원</option><option value="41">치과병원</option><option value="51">치과의원</option><option value="61">조산원</option><option value="92">한방병원</option><option value="93">한의원</option><option value="01">상급종합</option></select><div class="sc-select02 customSelectBox" style="width: 111px; position: relative; display: inline-block; vertical-align: top;"><a href="javascript:void(0)" class="customSelectBoxInner" style="display: inline-block; vertical-align: top; width: 93px; position: absolute; left: 0px; top: 0px;">=전체=</a><span class="customSelectBoxArrow" style="display: inline-block; vertical-align: top; position: absolute; right: 0px;"></span><ul class="customSelectBoxOption" style="margin: 0px; padding: 5px 0px; list-style: none; position: absolute; top: 27px; left: -1px; overflow-y: auto; display: none;"><li><a href="javascript:void(0)" rel="" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">=전체=</a></li><li><a href="javascript:void(0)" rel="75" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">보건의료원</a></li><li><a href="javascript:void(0)" rel="71" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">보건소</a></li><li><a href="javascript:void(0)" rel="72" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">보건지소</a></li><li><a href="javascript:void(0)" rel="73" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">보건진료소</a></li><li><a href="javascript:void(0)" rel="11" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">종합병원</a></li><li><a href="javascript:void(0)" rel="21" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">병원</a></li><li><a href="javascript:void(0)" rel="28" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">요양병원</a></li><li><a href="javascript:void(0)" rel="31" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">의원</a></li><li><a href="javascript:void(0)" rel="41" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">치과병원</a></li><li><a href="javascript:void(0)" rel="51" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">치과의원</a></li><li><a href="javascript:void(0)" rel="61" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">조산원</a></li><li><a href="javascript:void(0)" rel="92" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">한방병원</a></li><li><a href="javascript:void(0)" rel="93" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">한의원</a></li><li><a href="javascript:void(0)" rel="01" class="customSelectBox-option-rel" style="display: block; white-space: nowrap; padding-left: 9px; padding-right: 9px;">상급종합</a></li></ul></div>
				</div>
			</div>
			<div class="check_wrap">
				<div class="title">진료과목선택</div>
				<div class="sb_con">
					<div class="btn_line">
						<a href="javascript:dgsbjt_cd_all(true, 1);"><img src="/images/common/ck_con_btn_1.gif" alt="전체선택"></a>
						<a href="javascript:dgsbjt_cd_all(false, 1);"><img src="/images/common/ck_con_btn_2.gif" alt="선택취소"></a>
					</div>
					<div class="check_box">
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="내과체크" value="01"> 내과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="신경과체크" value="02"> 신경과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="정신건강의학과체크" value="03"> 정신건강의학과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="외과체크" value="04"> 외과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="정형외과체크" value="05"> 정형외과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="신경외과체크" value="06"> 신경외과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="흉부외과체크" value="07"> 흉부외과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="성형외과체크" value="08"> 성형외과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="마취통증의학과체크" value="09"> 마취통증의학과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="산부인과체크" value="10"> 산부인과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="소아청소년과체크" value="11"> 소아청소년과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="안과체크" value="12"> 안과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="이비인후과체크" value="13"> 이비인후과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="피부과체크" value="14"> 피부과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="비뇨기과체크" value="15"> 비뇨기과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="영상의학과체크" value="16"> 영상의학과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="방사선종양학과체크" value="17"> 방사선종양학과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="병리과체크" value="18"> 병리과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="진단검사의학과체크" value="19"> 진단검사의학과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="결핵과체크" value="20"> 결핵과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="재활의학과체크" value="21"> 재활의학과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="핵의학과체크" value="22"> 핵의학과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="가정의학과체크" value="23"> 가정의학과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="응급의학과체크" value="24"> 응급의학과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="직업환경의학과체크" value="25"> 직업환경의학과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="예방의학과체크" value="26"> 예방의학과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="치과체크" value="49"> 치과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="구강악안면외과체크" value="50"> 구강악안면외과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="치과보철과체크" value="51"> 치과보철과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="치과교정과체크" value="52"> 치과교정과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="소아치과체크" value="53"> 소아치과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="치주과체크" value="54"> 치주과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="치과보존과체크" value="55"> 치과보존과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="구강내과체크" value="56"> 구강내과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="영상치의학과체크" value="57"> 영상치의학과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="구강악안면방사선과체크" value="57"> 구강악안면방사선과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="구강병리과체크" value="58"> 구강병리과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="예방치과체크" value="59"> 예방치과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="한방내과체크" value="80"> 한방내과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="한방부인과체크" value="81"> 한방부인과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="한방소아과체크" value="82"> 한방소아과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="한방안·이비인후·피부과체크" value="83"> 한방안·이비인후·피부과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="한방안이비인후피부과체크" value="83"> 한방안이비인후피부과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="한방신경정신과체크" value="84"> 한방신경정신과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="침구과체크" value="85"> 침구과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="한방재활의학과체크" value="86"> 한방재활의학과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="사상체질과체크" value="87"> 사상체질과</label>
					
					<label class="checkbox"><input type="checkbox" class="chk_dgsbjt_cd1" title="한방응급체크" value="88"> 한방응급</label>
					
					</div>
				</div>
			</div>
			
			<div class="last_search">
				<div class="title">기관명</div>
				<div class="sb_con">
					<input name="yadm_nm" id="yadm_nm" onkeypress="if(event.keyCode == 13){$('#cpage').val(1); go_search(1); return false;}" class="input_l_t1" title="기관명 적기">
					<input type="button" value="검색" onclick="go_search(1);" class="input_l_s1">
					<input type="button" value="초기화" onclick="frmReset1();" class="input_l_s2">
				</div>
			</div>
			
		</div>
	</div></div>
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
<script type="text/javascript" src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=97ed58e74b2a1030e3fbd1d29e3272c9"></script>
<script type="text/javascript">


var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
mapOption = {
    center: new kakao.maps.LatLng(37.56679, 126.97877), // 지도의 중심좌표
    level: 3, // 지도의 확대 레벨
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
    	content: '<div>카카오</div>', 
        title: '카카오', 
        latlng: new kakao.maps.LatLng(37.56700, 126.97877)
    },
    {
        title: '생태연못', 
        latlng: new kakao.maps.LatLng(37.56800, 126.97877)
    },
    {
        title: '텃밭', 
        latlng: new kakao.maps.LatLng(37.56900, 126.97877)
    },
    {
        title: '근린공원',
        latlng: new kakao.maps.LatLng(37.56100, 126.97877)
    }
];

// 지도에 마커를 생성하고 표시한다
for (var i = 0; i < positions.length; i ++) {
    
    // 마커 이미지의 이미지 크기 입니다
    
    // 마커 이미지를 생성합니다    
    
    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
        map: map, // 마커를 표시할 지도
        position: positions[i].latlng, // 마커를 표시할 위치
        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
    });
    marker.setMap(map);
}

selectedMenu = 'searchHosp';

</script>

</body>


</html>