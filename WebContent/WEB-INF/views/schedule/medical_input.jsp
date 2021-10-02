<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link href='${contextPath}/resources/css/schedule/calendar/main.css' rel='stylesheet'/>
<script defer src='${contextPath}/resources/js/schedule/calendar/render.js'></script>
<script src='${contextPath}/resources/js/schedule/calendar/main.js'></script>
<script src='${contextPath}/resources/js/schedule/calendar/locales-all.js'></script>
<link href='${contextPath}/resources/css/schedule/schedule_record_form.css' rel='stylesheet'/>
</head>
<!-- <body> -->
		<div class="main">
			<div class="main-content">
				<div class="wrap_calendar">
					<div id='calendar'></div>
				</div>
				<div class="wrap_form">
					<div class="form_title">진료  기록 수정</div>
					<div class="input_list">
						<form action="/schedule/renew-medical" method="post" class="input_form" id="input_form">
							<span id="scheduleDateCheck" class="valid-msg"></span>
							<label>진료 날짜 : <input type="date" class='standard_date' id="schedule_date" name="schedule_date" value=
								<c:choose>
									<c:when test="${not empty param.err}">'${scheduleForm.scheduleDate}'</c:when>
									<c:otherwise>'${currentSchedule.medical.scheduleDate}'</c:otherwise>
								</c:choose>
							autofocus required></label>
							<span id="hospitalCheck" class="valid-msg">
								<c:choose>
									<c:when test="${not empty param.err and not empty scheduleFailed.noHospital}">진료 받은 병원을 입력하세요.</c:when>
									<c:when test="${not empty param.err and not empty scheduleFailed.hospCode}">검색을 통해 병원을 지정해주세요.</c:when>
									<c:when test="${not empty param.err and not empty scheduleFailed.hospital}">병원명이 잘못되었습니다. 다시 검색하여 지정해주세요.</c:when>
								</c:choose>
							</span>
							<label class="search_btn">진료 병원 : <input type="text" name="hospital" class="hospital" value=
								<c:choose>
									<c:when test="${not empty param.err and empty scheduleFailed.noHospital and empty scheduleFailed.hospCode and empty scheduleFailed.hospital and not empty scheduleForm.hospital}">value="${scheduleForm.hospital}"</c:when>
									<c:otherwise>'${currentSchedule.hospName}'</c:otherwise>
								</c:choose>
							placeholder="진료 병원을 기록해보세요" readonly> <button type="button" onclick="createHospitalPopup()">검색</button></label><input class="hospCode" name="hospCode" value=
								<c:choose>
									<c:when test="${not empty param.err and empty scheduleFailed.noHospital and empty scheduleFailed.hospCode and empty scheduleFailed.hospital and not empty scheduleForm.hospital}">value="${scheduleForm.hospCode}"</c:when>
									<c:otherwise>'${currentSchedule.medical.hospCode}'</c:otherwise>
								</c:choose>
							id="code">
						</form>
					</div>
					<div class="action_icons">
						<a href="${contextPath}/schedule/schedule-main"></i><i class="far fa-times-circle"></i></a><button form="input_form"><i class="far fa-save"></i></button>
					</div>
				</div>
			</div>
		</div>
		
		<footer>
			
		</footer>
	</div>
<script type="text/javascript">
	selectedMenu = 'schedule';
</script>
<script src='${contextPath}/resources/js/schedule/scheduleForm.js'></script>
</body>

</html>