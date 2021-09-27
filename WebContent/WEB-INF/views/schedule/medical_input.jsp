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
						<form action="schedule/renew-medical" method="post" class="input_form" id="input_form">
							<span id="scheduleDateCheck" class="valid-msg"></span>
							<label>진료 날짜 : <input type="date" class='standard_date' id="schedule_date" name="schedule_date" value='${currentSchedule.scheduleDate}' autofocus required></label>
							<label class="search_btn">진료 병원 : <input type="text" name="hospital" value='${currentSchedule.hospCode}' placeholder="진료 병원을 기록해보세요" readonly> <button type="button" onclick="">검색</button></label>
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