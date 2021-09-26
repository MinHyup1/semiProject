(function() {
	var calendar;
	var prevSelected = undefined;
	var standardDate = new Date(); /* 일정 날짜로 선택된 날짜 */
	var scheduleDate; /* standard 날짜의 셀 */
	var scheduleList = sessionStorage.getItem('schedule');
	console.dir(scheduleList);

document.addEventListener('DOMContentLoaded', function() {
    //var initialLocaleCode = 'ko';
    var localeSelectorEl = document.getElementById('locale-selector');
    var calendarEl = document.getElementById('calendar');
	
	if(!document.querySelector('.select_menu')) {
		standardDate = document.querySelector('.standard_date').value;
	}
	
    calendar = new FullCalendar.Calendar(calendarEl, {
		
	  initialDate: standardDate,
		
	  headerToolbar: {
        left: 'today',
        center: 'title',
        right: 'prevYear,prev,next,nextYear'
      },
      titleFormat: {
    	year: 'numeric',
    	month: 'long'
      },
      buttonText: {
    	today: 'today'  
      },
      dayMaxEvents: true, // allow "more" link when too many events
	  
	  dateClick: function(info) {
		if(prevSelected) {
			prevSelected.style.backgroundColor = '';
			if(standardDate != Date.now() && info.dayEl.dataset.date == standardDate) {
				return;
			}
		}
		/*alert('Date : ' + info.dateStr);*/
		prevSelected = info.dayEl
		info.dayEl.style.backgroundColor = '#c9d7e8';
	  },
	  
	  eventClick: function(info) {
		console.dir("id : " + info.event.id);
		console.dir("groupId : " + info.event.groupId);
		console.dir("start : " + info.event.start);
		console.dir("end : " + info.event.end);
		console.dir("title : " + info.event.title);
		console.dir("color : " + info.event.color);
		console.dir("backgroundColor : " + info.event.backgroundColor);
		console.dir("hospital : " + info.event.extendedProps.hospital);
	  },
	  events: scheduleList
	  
      /*events: [
		{
			id: 'list_id',
			groupId: 'schedule_id',
			start: '2021-09-21T10:30:00',
			end: '2021-09-25T17:39:15',
			allDay: true,
			//startTime: '10:30:00',
			//endTime: '17:39:15',
			title: '복용 알림',
			backgroundColor: 'purple',
			color: 'white'
		},
		{
			id: 'list_id',
			groupId: 'schedule_id',
			start: '2021-09-07',
			allDay: true,
			title: '병원 진료',
			backgroundColor: 'orange',
			color: 'white'
		},
		{
			id: 'list_id',
			groupId: 'schedule_id',
			start: '2021-09-07',
			allDay: true,
			title: '병원 진료',
			backgroundColor: 'grey',
			color: 'white'
		},
        {
          title: 'All Day Event',
          start: '2020-09-01'
        },
        {
          title: 'Long Event',
          start: '2020-09-07',
          end: '2020-09-10'
        },
        {
          groupId: 999,
          title: 'Repeating Event',
          start: '2020-09-09T16:00:00'
        },
        {
          groupId: 999,
          title: 'Repeating Event',
          start: '2020-09-16T16:00:00'
        },
        {
          title: 'Conference',
          start: '2020-09-11',
          end: '2020-09-13'
        },
        {
          title: 'Meeting',
          start: '2020-09-12T10:30:00',
          end: '2020-09-12T12:30:00'
        },
        {
          title: 'Lunch',
          start: '2020-09-12T12:00:00'
        },
        {
          title: 'Meeting',
          start: '2020-09-12T14:30:00'
        },
        {
          title: 'Happy Hour',
          start: '2020-09-12T17:30:00'
        },
        {
          title: 'Dinner',
          start: '2020-09-12T20:00:00'
        },
        {
          title: 'Birthday Party',
          start: '2020-09-13T07:00:00'
        },
        {
          title: 'Click for Google',
          url: 'http://google.com/',
          start: '2020-09-28'
        }
      ]*/
    });
	calendar.addEvent(
		event = {
				"id": 'list_id',
				"groupId": 'schedule_id',
				"start": '2021-09-02',
				//end: '2021-09-02',
				//allDay: true,
				"title": '진료 알림',
				"backgroundColor": 'green',
				"color": 'white',
				"hospital": '병원'
			}
	);
	
    calendar.render();
	
	if(!document.querySelector('.select_menu')) {
		document.querySelectorAll('.fc-scrollgrid-sync-table>tbody>tr').forEach(e => {
			e.childNodes.forEach(cell => {
				if(cell.dataset.date == standardDate) {
					scheduleDate = cell;
					scheduleDate.style.backgroundColor = '#ffb3b3';
				}
			})
		})
	}
	
	document.querySelector('.fc-scrollgrid-sync-table>tbody').addEventListener('dblclick', e => {
		/* 일정등록 선택 화면 뿌리기 */
		if(document.querySelector('.select_menu')) {
			document.querySelector('.select_menu').style.display = 'initial';
		
			document.querySelector('.select_menu').addEventListener('click', e => {
				if(e.target.className == 'top_menu' || e.target.className == 'bottom_menu'){
					document.querySelector('.select_menu').style.display = 'none';
				};
			});
			let doubleClickedDate = prevSelected.dataset.date;
			document.querySelectorAll('.btn_desc').forEach(e => {
				e.href = e.href.split('?')[0];
				e.href += '?date=' + doubleClickedDate;
			})
		}
	});
	
	/*document.querySelector('.fc-button-group').addEventListener('click', e => {
		console.dir(e);
		console.dir(scheduleDate.style.backgroundColor);
		scheduleDate.style.backgroundColor = '#ff6666';
	});*/
	
	
});

})();

let addMedicineNotice = function() {
	let times = document.querySelector('input[type=number]').value;
	let timeInp = document.createElement('label');
	timeInp.innerHTML = "<input type='time' class='time' name='dose_notice' required> ";
	
	let trashIcon = document.createElement('i');
	trashIcon.className = 'fas fa-trash';
	
	trashIcon.addEventListener('click', (e) => {
		document.querySelector('input[type=number]').value -= 1;
		e.target.parentElement.remove();
	})
	
	timeInp.appendChild(trashIcon);
	
	if(times < 24) {
		document.querySelector('input[type=number]').value = times - 1 + 2;
	}
	
	document.querySelector('.input_form').appendChild(timeInp);
	
};

let addVisitNotice = function(e) {
	let today = new Date();
	let tomorrow = new Date(today.getFullYear(), today.getMonth(), today.getDate()+1);
	let yyyy = tomorrow.getFullYear();
	let mm = tomorrow.getMonth()+1;
	let dd = tomorrow.getDate();
	
	if(dd < 10) dd = '0' + dd;
	if(mm < 10) mm = '0' + mm;
	
	let minDate = yyyy + '-' + mm + '-' + dd + 'T00:00';
	
	let dateTimeInp = document.createElement('label');
	dateTimeInp.innerHTML = "<input type='datetime-local' class='dateTime' name='visit_notice_date' min='" + minDate + "' required> ";
	
	let trashIcon = document.createElement('i');
	trashIcon.className = 'fas fa-trash';
	
	trashIcon.addEventListener('click', (e) => {
		e.target.parentElement.remove();
	})
	
	dateTimeInp.appendChild(trashIcon);
	
	document.querySelector('.added-notice').appendChild(dateTimeInp);
	
};

