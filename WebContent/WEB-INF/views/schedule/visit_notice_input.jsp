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
					<div class="form_title">
						<c:choose>
							<c:when test="${not empty param.edit}">진료 알림 수정</c:when>
							<c:otherwise>진료 알림 등록</c:otherwise>
						</c:choose>
					</div>
					<div class="input_list">
						<form action="/schedule/visit-notice-register" method="post" class="input_form" id="input_form">
							<span id="scheduleDateCheck" class="valid-msg">
								<c:if test="${not empty param.err and not empty scheduleFailed.visitNoticeNotLater}">진료 알림일자는 내일 이후로 지정 가능합니다.</c:if>
							</span>
							<label>알림 날짜 : <input type="date" class='standard_date' id="schedule_date" name="schedule_date" value=
								<c:choose>
									<c:when test="${not empty param.err}">'${scheduleForm.scheduleDate}'</c:when>
									<c:when test="${not empty param.edit}">'${currentSchedule.notice_date}'</c:when>
									<c:otherwise>'${param.date}'</c:otherwise>
								</c:choose>
							autofocus required></label>
							<span id="hospitalCheck" class="valid-msg">
								<c:choose>
									<c:when test="${not empty param.err and not empty scheduleFailed.hospCode}">검색을 통해 병원을 지정해주세요.</c:when>
									<c:when test="${not empty param.err and not empty scheduleFailed.hospital}">병원명이 잘못되었습니다. 다시 검색하여 지정해주세요.</c:when>
								</c:choose>
							</span>
							<label class="search_btn">진료 병원 : <input type="text" name="hospital" class="hospital"
								<c:choose>
									<c:when test="${not empty param.err and empty scheduleFailed.hospCode and empty scheduleFailed.hospital and not empty scheduleForm.hospital}">value="${scheduleForm.hospital}"</c:when>
									<c:when test="${not empty param.edit and not empty currentSchedule.visit.hospCode}">value='${currentSchedule.hospName}'</c:when>
								</c:choose>
							placeholder="진료 병원을 기록해보세요" readonly> <button type="button" onclick='createHospitalPopup()'>검색</button></label>
							<input class="hospCode" name="hospCode"
								<c:choose>
									<c:when test="${not empty param.err and empty scheduleFailed.hospCode and empty scheduleFailed.hospital and not empty scheduleForm.hospital}">value="${scheduleForm.hospCode}"</c:when>
									<c:when test="${not empty param.edit and not empty currentSchedule.visit.hospCode}">value='${currentSchedule.visit.hospCode}'</c:when>
								</c:choose>
							id="code">
							<span id="timeCheck" class="valid-msg">
								<c:choose>
									<c:when test="${not empty param.err and not empty scheduleFailed.noNoticeTime}">알림시간을 입력하세요.</c:when>
									<c:when test="${not empty param.err and not empty scheduleFailed.noticeTime}">알림시간 입력이 잘못되었습니다.</c:when>
								</c:choose>
							</span>
							<label>알림 시간 : <input type="time" class='time'
								<c:choose>
									<c:when test="${not empty param.err and empty scheduleFailed.noNoticeTime and empty scheduleFailed.noticeTime and not empty scheduleForm.noticeTime}">value='${scheduleForm.noticeTime}'</c:when>
									<c:when test="${not empty param.edit}">value='${currentSchedule.notice_time}'</c:when>
								</c:choose>
							 name="visit_notice_time" required></label>
						</form>
					</div>
					<div class="action_icons">
						<a href="${contextPath}/schedule/schedule-main"></i><i class="far fa-times-circle"></i></a>
						<c:choose>
							<c:when test="${not empty param.edit}"><button form="input_form" formaction="${contextPath}/schedule/renew-visit"><i class="far fa-save"></i></button></c:when>
							<c:otherwise><a href="javascript:window.history.go()"><i class="fas fa-eraser"></i></a><button form="input_form"><i class="far fa-save"></i></button></c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
		
		<footer>
			
		</footer>
	</div>
<script type="text/javascript">
	selectedMenu = 'schedule';
	
	let today = new Date();
	let tomorrow = new Date(today.getFullYear(), today.getMonth(), today.getDate()+1);
	let yyyy = tomorrow.getFullYear();
	let mm = tomorrow.getMonth()+1;
	let dd = tomorrow.getDate();
	
	if(dd < 10) dd = '0' + dd;
	if(mm < 10) mm = '0' + mm;
	
	let minDate = yyyy + '-' + mm + '-' + dd;
	
	document.querySelector('.standard_date').min = minDate;
</script>
<script src='${contextPath}/resources/js/schedule/scheduleForm.js'></script>
</body>

</html>