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
					<div class="form_title">진료 일정 기록</div>
					<div class="input_list">
						<form action="/schedule/schedule-register" method="post" class="input_form" id="input_form">
							<span id="scheduleDateCheck" class="valid-msg"></span>
							<label>진료 날짜 : <input type="date" class='standard_date' id="schedule_date" name="schedule_date" value=
								<c:choose>
									<c:when test="${not empty param.err}">"${scheduleForm.scheduleDate}"</c:when>
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
								<c:if test="${not empty param.err and empty scheduleFailed.hospCode and empty scheduleFailed.hospital and not empty scheduleForm.hospital}">value="${scheduleForm.hospital}"</c:if>
							placeholder="진료 병원을 기록해보세요" readonly> <button type="button" onclick="createHospitalPopup()">검색</button></label><input class="hospCode"
								<c:if test="${not empty param.err and empty scheduleFailed.hospCode and empty scheduleFailed.hospital and not empty scheduleForm.hospital}">value="${scheduleForm.hospCode}"</c:if>
							name="hospCode" id="code">
							<span id="pharmacyCheck" class="valid-msg">
								<c:choose>
									<c:when test="${not empty param.err and not empty scheduleFailed.pharmCode}">검색을 통해 약국을 지정해주세요.</c:when>
									<c:when test="${not empty param.err and not empty scheduleFailed.pharmacy}">약국명이 잘못되었습니다. 다시 검색하여 지정해주세요.</c:when>
								</c:choose>
							</span>
							<label class="search_btn">처방 약국 : <input type="text" name="pharm" class="pharm"
								<c:if test="${not empty param.err and empty scheduleFailed.pharmCode and empty scheduleFailed.pharmacy and not empty scheduleForm.pharmacy}">value="${scheduleForm.pharmacy}"</c:if>
							placeholder="방문한 약국을 기록해보세요" readonly> <button type="button" onclick="createPharmPopup()">검색</button></label><input class="pharmCode"
								<c:if test="${not empty param.err and empty scheduleFailed.pharmCode and empty scheduleFailed.pharmacy and not empty scheduleForm.pharmCode}">value="${scheduleForm.pharmCode}"</c:if>
							name="pharmCode" id="code">
							<span id="medicineCheck" class="valid-msg">
								<c:if test="${not empty param.err and not empty scheduleFailed.medicine}">처방약 지정이 잘못되었습니다. 다시 검색하여 지정해주세요.</c:if>
							</span>
							<label class="search_btn">처방 약 : <input type="text" name="searched_med" placeholder="처방 받은 약을 기록해보세요" readonly> <button type="button" onclick="createMedicinePopup()">검색</button></label>
							<div class='medi-list'>
								<c:if test="${not empty param.err and empty scheduleFailed.medicine and not empty scheduleForm.medicine}">
									<c:forEach items="${scheduleForm.medicine}" var="med" varStatus="status">
										<span><input name="medicine" value="${scheduleForm.medicine[status.index]}" readonly><input value="${scheduleForm.mediCode[status.index]}" name="mediCode" id="code"> <i class="fas fa-trash edit-trash"></i></span>
									</c:forEach>
								</c:if>
							</div><!-- 처음에는 1칸, 약 추가시 칸 추가 / 휴지통 아이콘 사용하기 -->
							<span id="startDateCheck" class="valid-msg">
								<c:if test="${not empty param.err and not empty scheduleFailed.doseStart}">날짜 입력이 잘못되었습니다.</c:if>
							</span>
							<label>복용 시작일 : <input type="date" id="dose_start" class="dose_start"
								<c:if test="${not empty param.err and empty scheduleFailed.doseStart and not empty scheduleForm.doseStart}">value="${scheduleForm.doseStart}"</c:if>
							name="dose_start"></label>
							<span id="endDateCheck" class="valid-msg">
								<c:choose>
									<c:when test="${not empty param.err and not empty scheduleFailed.isNotDoseEnd}">종료일자가 없습니다.</c:when>
									<c:when test="${not empty param.err and not empty scheduleFailed.doseEnd}">날짜 입력이 잘못되었습니다.</c:when>
									<c:when test="${not empty param.err and not empty scheduleFailed.doseEndNotLater}">종료일자는 시작일자보다 같거나 이후여야 합니다.</c:when>
								</c:choose>
							</span>
							<label>복용 종료일 : <input type="date" id="dose_end" class="dose_end"
								<c:if test="${not empty param.err and empty scheduleFailed.isNotDoseEnd and empty scheduleFailed.doseEnd and empty scheduleFailed.doseEndNotLater and not empty scheduleForm.doseEnd}">value="${scheduleForm.doseEnd}</c:if>
							name="dose_end"></label>
							<span id="doseTimesCheck" class="valid-msg">
								<c:if test="${not empty param.err and not empty scheduleFailed.doseTimes}">복용횟수는 0과 24 사이여야 합니다.</c:if>
							</span>
							<label>1일 복용 횟수 : <input type="number" name="dose_times" value=
								<c:choose>
									<c:when test="${not empty param.err and empty scheduleFailed.doseTimes and not empty scheduleForm.doseTimes}">'${scheduleForm.doseTimes}'</c:when>
									<c:otherwise>"0"</c:otherwise>
								</c:choose>
							step="1" min="0" max="24" required readonly>회</label>
							<div><label class="add_btn">다음 진료 알림</label>  <button type="button" onclick="addVisitNotice(event)">추가</button></div>
							<span id="dateTimeCheck" class="valid-msg">
								<c:choose>
									<c:when test="${not empty param.err and not empty scheduleFailed.visitNotice}">입력이 잘못된 진료 알림일자가 있습니다.</c:when>
									<c:when test="${not empty param.err and not empty scheduleFailed.visitNoticeNotLater}">진료 알림일자는 내일 이후로 지정 가능합니다.</c:when>
								</c:choose>
							</span>
							<div class='added-notice'>
								<c:if test="${not empty param.err and empty scheduleFailed.visitNotice and empty scheduleFailed.visitNoticeNotLater and not empty scheduleForm.visitNotice}">
									<c:forEach items="${scheduleForm.visitNotice}" var="time">
										<label><input type="datetime-local" class='dateTime' name="visit_notice_date" value='${time}' min="${scheduleFailed.tomorrow}" required> <i class="fas fa-trash edit-trash"></i></label>
									</c:forEach>
								</c:if>
							</div>
							<div><label class="add_btn">복용 알림 시간</label> <button type="button" class="add-time" onclick="addMedicineNotice()">추가</button></div>
							<span id="timeCheck" class="valid-msg">
								<c:if test="${not empty param.err and not empty scheduleFailed.doseNoticeTime}">복용 알림시간 입력이 잘못되었습니다.</c:if>
							</span>
							<c:if test="${not empty param.err and empty scheduleFailed.doseNoticeTime and not empty scheduleForm.doseNoticeTime}">
								<c:forEach items="${scheduleForm.doseNoticeTime}" var="time">
									<label><input type="time" class='time' name="dose_notice" value='${time}' required> <i class="fas fa-trash edit-trash dose"></i></label>
								</c:forEach>
							</c:if>
						</form>
					</div>
					<div class="action_icons">
						<a href="${contextPath}/schedule/schedule-main"></i><i class="far fa-times-circle"></i></a><a href="javascript:window.history.go()"><i class="fas fa-eraser"></i></a><button form="input_form"><i class="far fa-save"></i></button>
					</div>
				</div>
			</div>
		</div>
		
		<footer>
			
		</footer>
	</div>
<script type="text/javascript">
	selectedMenu = 'schedule';
	
	(function() {
		if(document.querySelectorAll('.edit-trash')) {
			document.querySelectorAll('.edit-trash').forEach(function(e) {
				e.addEventListener('click', function(event) {
					if(event.target.className == 'fas fa-trash edit-trash dose') {
						document.querySelector('input[type=number]').value -= 1;
					}
					event.target.parentElement.remove();
				})
			})
		}
	})();
</script>
<script src='${contextPath}/resources/js/schedule/scheduleForm.js'></script>
</body>

</html>