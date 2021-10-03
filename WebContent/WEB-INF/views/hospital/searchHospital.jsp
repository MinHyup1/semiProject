<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<link
	href='${contextPath}/resources/css/searchHospital/searchHospital.css'
	rel='stylesheet' />

</head>
<body>
	<div class="main">
		<div>
			<div class="search_title_wrapper">
				<div class="map_wrapper">
					<div class="map">
						<div id="map" style="width: 100%; height: 100%;"></div>
					</div>
				</div>
				<div>
					<div id="tabNav0101">
						<form action="/searchHospital/search-hospital" method="get">
							<div class="d_search_box">

								<div class="check_wrap">

									<div class="sb_con">

										<div class="check_box">

											<label class="checkbox"><input type="checkbox"
												name="treatCheckBox" class="chk_dgsbjt_cd1" title="내과체크"
												value="01"> 내과</label> <label class="checkbox"><input
												type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1"
												title="신경과체크" value="02"> 신경과</label> <label
												class="checkbox"><input type="checkbox"
												name="treatCheckBox" class="chk_dgsbjt_cd1"
												title="정신건강의학과체크" value="03"> 정신건강의학과</label> <label
												class="checkbox"><input type="checkbox"
												name="treatCheckBox" class="chk_dgsbjt_cd1" title="외과체크"
												value="04"> 외과</label> <label class="checkbox"><input
												type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1"
												title="정형외과체크" value="05"> 정형외과</label> <label
												class="checkbox"><input type="checkbox"
												name="treatCheckBox" class="chk_dgsbjt_cd1" title="신경외과체크"
												value="06"> 신경외과</label> <label class="checkbox"><input
												type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1"
												title="흉부외과체크" value="07"> 흉부외과</label> <label
												class="checkbox"><input type="checkbox"
												name="treatCheckBox" class="chk_dgsbjt_cd1" title="성형외과체크"
												value="08"> 성형외과</label> <label class="checkbox"><input
												type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1"
												title="마취통증의학과체크" value="09"> 마취통증의학과</label> <label
												class="checkbox"><input type="checkbox"
												name="treatCheckBox" class="chk_dgsbjt_cd1" title="산부인과체크"
												value="10"> 산부인과</label> <label class="checkbox"><input
												type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1"
												title="소아청소년과체크" value="11"> 소아청소년과</label> <label
												class="checkbox"><input type="checkbox"
												name="treatCheckBox" class="chk_dgsbjt_cd1" title="안과체크"
												value="12"> 안과</label> <label class="checkbox"><input
												type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1"
												title="이비인후과체크" value="13"> 이비인후과</label> <label
												class="checkbox"><input type="checkbox"
												name="treatCheckBox" class="chk_dgsbjt_cd1" title="피부과체크"
												value="14"> 피부과</label> <label class="checkbox"><input
												type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1"
												title="비뇨기과체크" value="15"> 비뇨기과</label> <label
												class="checkbox"><input type="checkbox"
												name="treatCheckBox" class="chk_dgsbjt_cd1" title="영상의학과체크"
												value="16"> 영상의학과</label> <label class="checkbox"><input
												type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1"
												title="방사선종양학과체크" value="17"> 방사선종양학과</label> <label
												class="checkbox"><input type="checkbox"
												name="treatCheckBox" class="chk_dgsbjt_cd1" title="병리과체크"
												value="18"> 병리과</label> <label class="checkbox"><input
												type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1"
												title="진단검사의학과체크" value="19"> 진단검사의학과</label> <label
												class="checkbox"><input type="checkbox"
												name="treatCheckBox" class="chk_dgsbjt_cd1" title="결핵과체크"
												value="20"> 결핵과</label> <label class="checkbox"><input
												type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1"
												title="재활의학과체크" value="21"> 재활의학과</label> <label
												class="checkbox"><input type="checkbox"
												name="treatCheckBox" class="chk_dgsbjt_cd1" title="핵의학과체크"
												value="22"> 핵의학과</label> <label class="checkbox"><input
												type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1"
												title="가정의학과체크" value="23"> 가정의학과</label> <label
												class="checkbox"><input type="checkbox"
												name="treatCheckBox" class="chk_dgsbjt_cd1" title="응급의학과체크"
												value="24"> 응급의학과</label> <label class="checkbox"><input
												type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1"
												title="직업환경의학과체크" value="25"> 직업환경의학과</label> <label
												class="checkbox"><input type="checkbox"
												name="treatCheckBox" class="chk_dgsbjt_cd1" title="예방의학과체크"
												value="26"> 예방의학과</label> <label class="checkbox"><input
												type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1"
												title="치과체크" value="49"> 치과</label> <label class="checkbox"><input
												type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1"
												title="구강악안면외과체크" value="50"> 구강악안면외과</label> <label
												class="checkbox"><input type="checkbox"
												name="treatCheckBox" class="chk_dgsbjt_cd1" title="치과보철과체크"
												value="51"> 치과보철과</label> <label class="checkbox"><input
												type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1"
												title="치과교정과체크" value="52"> 치과교정과</label> <label
												class="checkbox"><input type="checkbox"
												name="treatCheckBox" class="chk_dgsbjt_cd1" title="소아치과체크"
												value="53"> 소아치과</label> <label class="checkbox"><input
												type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1"
												title="치주과체크" value="54"> 치주과</label> <label
												class="checkbox"><input type="checkbox"
												name="treatCheckBox" class="chk_dgsbjt_cd1" title="치과보존과체크"
												value="55"> 치과보존과</label> <label class="checkbox"><input
												type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1"
												title="구강내과체크" value="56"> 구강내과</label> <label
												class="checkbox"><input type="checkbox"
												name="treatCheckBox" class="chk_dgsbjt_cd1" title="영상치의학과체크"
												value="57"> 영상치의학과</label> <label class="checkbox"><input
												type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1"
												title="구강악안면방사선과체크" value="57"> 구강악안면방사선과</label> <label
												class="checkbox"><input type="checkbox"
												name="treatCheckBox" class="chk_dgsbjt_cd1" title="구강병리과체크"
												value="58"> 구강병리과</label> <label class="checkbox"><input
												type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1"
												title="예방치과체크" value="59"> 예방치과</label> <label
												class="checkbox"><input type="checkbox"
												name="treatCheckBox" class="chk_dgsbjt_cd1" title="한방내과체크"
												value="80"> 한방내과</label> <label class="checkbox"><input
												type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1"
												title="한방부인과체크" value="81"> 한방부인과</label> <label
												class="checkbox"><input type="checkbox"
												name="treatCheckBox" class="chk_dgsbjt_cd1" title="한방소아과체크"
												value="82"> 한방소아과</label> <label class="checkbox"><input
												type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1"
												title="한방안·이비인후·피부과체크" value="83"> 한방안·이비인후·피부과</label> <label
												class="checkbox"><input type="checkbox"
												name="treatCheckBox" class="chk_dgsbjt_cd1"
												title="한방안이비인후피부과체크" value="83"> 한방안이비인후피부과</label> <label
												class="checkbox"><input type="checkbox"
												name="treatCheckBox" class="chk_dgsbjt_cd1"
												title="한방신경정신과체크" value="84"> 한방신경정신과</label> <label
												class="checkbox"><input type="checkbox"
												name="treatCheckBox" class="chk_dgsbjt_cd1" title="침구과체크"
												value="85"> 침구과</label> <label class="checkbox"><input
												type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1"
												title="한방재활의학과체크" value="86"> 한방재활의학과</label> <label
												class="checkbox"><input type="checkbox"
												name="treatCheckBox" class="chk_dgsbjt_cd1" title="사상체질과체크"
												value="87"> 사상체질과</label> <label class="checkbox"><input
												type="checkbox" name="treatCheckBox" class="chk_dgsbjt_cd1"
												title="한방응급체크" value="88"> 한방응급</label>

										</div>
									</div>
								</div>

								<div class="last_search">
									<div class="title">병원명</div>
									<div class="sb_con">

										<input type="text" name="input" id="input" class="input_l_t1"
											value="${input}" title="기관명 적기">
										<button class="searchByName">검색</button>
										<c:if test="${not empty msg}">
											<p id="is_empty" style="color: red">${msg}</p>
										</c:if>
										<c:if test="${not empty requestScope.update}">
											<p id="is_empty" style="color: red">${requestScope.update}</p>
										</c:if>
									</div>
								</div>

							</div>
						</form>

					</div>
				</div>

				<div class="hospital_list">

					<ul class="listWrap">

						<c:if test="${not empty requestScope.hospList}">
							<table class="hospital_info_table">
								<th style="width: 5%;">NO.</th>
								<th>기관명</th>
								<th style="width: 30%;">주소</th>
								<th>전화</th>
								<th>홈페이지</th>
								<c:forEach var="i" begin="0" step="1" end="${siez -1}"
									varStatus="status">
									<tr>
										<!-- 첫번째 줄 시작 -->
										<td class="num1" valign="${status.count -1}">${status.count}</td>

										<td><a
											onclick="createKakaoMap('${requestScope.hospList[i].xPos}','${requestScope.hospList[i].yPos}','${requestScope.hospList[i].hospName}')">${requestScope.hospList[i].hospName}</a></td>
										<td>${requestScope.hospList[i].address}</td>
										<td>${requestScope.hospList[i].hospTell}</td>
										<td><c:choose>
												<c:when test="${requestScope.hospList[i].hospUrl == '-'}">
													<p style="align-content: center;">-</p></td>
										</c:when>
										<c:otherwise>
											<a href="${requestScope.hospList[i].hospUrl}" target='_blank'>
												${requestScope.hospList[i].hospUrl}</a>
											</td>
										</c:otherwise>
										</c:choose>
									</tr>
									<!-- 첫번째 줄 끝 -->
								</c:forEach>
							</table>
						</c:if>
				</div>
			</div>
			<div></div>

		</div>


	</div>

	<footer> </footer>

	</div>

</body>
<script type="text/javascript"src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=97ed58e74b2a1030e3fbd1d29e3272c9&libraries=services"></script>
<script src='${contextPath}/resources/js/searchHospital/kakaoMap.js'></script>



</html>