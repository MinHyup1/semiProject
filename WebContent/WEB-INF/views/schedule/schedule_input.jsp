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
						<form action="schedule/schedule-register" method="post" class="input_form" id="input_form">
							<span id="scheduleDateCheck" class="valid-msg"></span>
							<label>진료 날짜 : <input type="date" class='standard_date' id="schedule_date" name="schedule_date" value='${param.date}' autofocus required></label>
							<label class="search_btn">진료 병원 : <input type="text" name="hospital" placeholder="진료 병원을 기록해보세요" disabled> <button type="button" onclick="">검색</button></label>
							<label class="search_btn">처방 약국 : <input type="text" name="pharm" placeholder="방문한 약국을 기록해보세요" disabled> <button type="button" onclick="">검색</button></label>
							<label class="search_btn">처방 약 : <input type="text" name="searched_med" placeholder="처방 받은 약을 기록해보세요" disabled> <button type="button" onclick="">검색</button></label>
							<div><span>aaa</span></div><!-- 처음에는 1칸, 약 추가시 칸 추가 / 휴지통 아이콘 사용하기 -->
							<span id="startDateCheck" class="valid-msg"></span>
							<label>복용 시작일 : <input type="date" id="dose_start" class="dose_start" name="dose_start"></label>
							<span id="endDateCheck" class="valid-msg"></span>
							<label>복용 종료일 : <input type="date" id="dose_end" class="dose_end" name="dose_end"></label>
							<label>1일 복용 횟수 : <input type="number" name="dose_times" value="0" step="1" min="0" max="24">회</label>
							<div><label class="add_btn">다음 진료 알림</label>  <button type="button" onclick="addVisitNotice(event)">추가</button></div>
							<span id="dateTimeCheck" class="valid-msg"></span>
							<div class='added-notice'></div>
							<div><label class="add_btn">복용 알림 시간</label> <button type="button" class="add-time" onclick="addMedicineNotice()">추가</button></div>
							<span id="timeCheck" class="valid-msg"></span>
						</form>
					</div>
					<div class="action_icons">
						<a href="${contextPath}/schedule/schedule-main"></i><i class="far fa-times-circle"></i></a><a href="#"><i class="fas fa-eraser"></i></a><button form="input_form"><i class="far fa-save"></i></button>
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