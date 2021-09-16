<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<link href='${contextPath}/resources/css/schedule/calendar/main.css' rel='stylesheet'/>
<script src='${contextPath}/resources/js/schedule/calendar/render.js'></script>
<script src='${contextPath}/resources/js/schedule/calendar/main.js'></script>
<script src='${contextPath}/resources/js/schedule/calendar/locales-all.js'></script>
<link href='${contextPath}/resources/css/schedule/schedule_main.css' rel='stylesheet'/>
</head>
<body>
<div class="main-content">
				<div class="select_menu">
					<div class="top_menu">
						<div class="top_btn menu_btn">
							<a class="btn_desc"><span class="desc">진료 내역을 기록하고 알림까지 설정해보세요.</span>
							<span class="tit top_tit">진료 일정 기록</span></a>
							<!-- <a class="menu_btn"><span>진료 일정 기록</span></a> -->
						</div>
					</div>
					<div class="bottom_menu">
						<div class="bottom_btn_left bottom_btn menu_btn">
							<a class="btn_desc"><span class="tit bottom_left_tit">진료 알림 등록</span>
							<span class="desc">예약된 진료 일정 알림을 등록해보세요.</span></a>
						</div>
						<div class="bottom_btn_right bottom_btn menu_btn">
							<a class="btn_desc"><span class="tit bottom_right_tit">복용 알림 등록</span>
							<span class="desc">복용 기간, 시간을 등록하고 알림을 받아보세요.</span></a>
						</div>
					</div>
				</div>
				<div class="wrap_calendar">
					<div id='calendar'></div>
				</div>
				<div class="wrap_form">
					<div class="form_title">이번달 등록 일정</div>
					<div class="schedule_list">
						<table border="1" class="schedule_table">
							<thead>
								<th>2020.09월</th>
							</thead>
							<tbody>
								<td>
									<div></div>1일
								</td>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
		
		<footer>
			
		</footer>
	</div>
</body>
</html>