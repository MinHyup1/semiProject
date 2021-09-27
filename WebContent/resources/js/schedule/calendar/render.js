let getSchedule = async () => {
	let response = await fetch('/schedule/get-schedule');
	let datas = await response.json();
	return datas;
};

let scheduleArray;

(async () => {
	
	scheduleArray =  await getSchedule();
	var prevSelected = undefined;
	var standardDate = new Date(); /* 일정 날짜로 선택된 날짜 */
	var scheduleDate; /* standard 날짜의 셀 */
    var localeSelectorEl = document.getElementById('locale-selector');
    var calendarEl = document.getElementById('calendar');
	let originColor = undefined;
	
	if(!document.querySelector('.select_menu')) {
		standardDate = document.querySelector('.standard_date').value;
	}
	
    var calendar = new FullCalendar.Calendar(calendarEl, {
		
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
		prevSelected = info.dayEl
		info.dayEl.style.backgroundColor = '#c9d7e8';
	  },
	  eventMouseEnter: function(info) {
		originColor = info.el.style.backgroundColor;
		info.el.style.backgroundColor = 'grey';
	  },
	  eventMouseLeave: function(info) {
		info.el.style.backgroundColor = originColor;
	  },
	  eventClick: function(info) {
		scheduleClick(info.event);
	  },
	  initialEvents: scheduleArray,
      events: []
    });
    calendar.render();

	rendEventToTable(scheduleArray);
	
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
		if(e.target.className == 'fc-event-title fc-sticky') return;
		if(e.target.className == 'fc-event-title-container') return;
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
})();

let scheduleClick = async (event) => {
	if(!document.querySelector('.select_menu')) return; //메인화면이 아니면 바로 return;
	
	let kind = event.extendedProps.kind;
	if(kind == 'medical') {
		rendMedicalToTable(event);
	}else if(kind == 'prescription') {
		rendPrescriptionToTable(event);
	}else {
		rendVisitToTable(event);
	}
}


let rendMedicalToTable = async (event) => {
	let historyId = event.id;
	
	let response = await fetch('/schedule/get-medical?historyId=' + historyId);
	let datas = await response.json();
	document.querySelector('.schedule_table>tbody').innerHTML = '';
	
	let date = document.createElement('td');
	date.innerHTML = '<label>진료 날짜 : <input type="date" value=' + datas.schedule_date + ' readonly></label>';
	
	let hospital = document.createElement('td');
	hospital.innerHTML = '<label>진료 병원 : <input type="text" value=' + datas.hospital + ' readonly></label>';
	
	document.querySelector('.schedule_table>tbody').append(date);
	document.querySelector('.schedule_table>tbody').append(hospital);
	document.querySelector('.action_icons').style.visibility = 'visible';
	document.querySelector('.edit').href += '/medical';
}

let rendPrescriptionToTable = async (event) => {
	let prescriptionId = event.id;
	
	let response = await fetch('/schedule/get-prescription?prescriptionId=' + prescriptionId);
	let datas = await response.json();
	console.dir(datas);
	let tbody = document.querySelector('.schedule_table>tbody')
	tbody.innerHTML = '';
	
	let start = document.createElement('td');
	start.innerHTML = '<label>복용 시작일 : <input type="date" value=' + datas.start + ' readonly></label>';
	
	let end = document.createElement('td');
	end.innerHTML = '<label>복용 시작일 : <input type="date" value=' + datas.end + ' readonly></label>';
	
	let pharm = document.createElement('td');
	pharm.innerHTML = '<label>처방 약국 : <input type="text" readonly></label>';
	
	if(datas.pharm) {
		pharm.innerHTML = '<label>처방 약국 : <input type="text" value=' + datas.pharm + ' readonly></label>';
	}
	
	tbody.append(start);
	tbody.append(end);
	tbody.append(pharm);
	
	if(datas.medicine) {}//추후 추가
	
	let times = document.createElement('td');
	times.innerHTML = "<label>1일 복용 횟수 : <input type='number' style='width:40px' value=" + datas.timesPerDay + " readonly> 회</label>";
	tbody.append(times);
	
	if(datas.doseTime) {
		datas.doseTime.forEach(time => {
			let timeTd = document.createElement('td');
			timeTd.innerHTML = '<label><input type="time" value=' + time + ' readonly></label>';
			tbody.append(timeTd);
		})
	}
	document.querySelector('.action_icons').style.visibility = 'visible';
	document.querySelector('.edit').href += '/prescription';
}

let rendVisitToTable = (event) => {
	
}

var rendScheduleTable = function () {
	document.querySelector('.action_icons').style.visibility = 'hidden';
	rendEventToTable(scheduleArray);
}

let rendEventToTable = function (scheduleArray) {
	if(!document.querySelector('.select_menu')) return; //메인화면이 아니면 바로 return;
	
	currentSchedule = getSortedCurrentEvents(scheduleArray);
	document.querySelector('.schedule_table>tbody').innerHTML = '';
	
	if(currentSchedule.length == 0) {
		let th = document.createElement('th');
		th.innerHTML = '등록된 일정이 없습니다.';
		document.querySelector('.schedule_table>tbody').appendChild(th);
		return;
	}
	
	currentSchedule.forEach(element => {
		let td = document.createElement('td');
		td.innerHTML = "<div style='background-color: " + element.backgroundColor + "'></div>";
		let text = document.createTextNode(element.start.substring(8, 10) + '일 ' + element.title);
		
		td.appendChild(text);
		document.querySelector('.schedule_table>tbody').appendChild(td);
		
	})
}

let getSortedCurrentEvents = function(scheduleArray) {
	let current = document.querySelector('.schedule_table>thead>tr>th').innerHTML;
	let curYear = current.substring(0, 4);
	let curMonth = current.substring(6, 8);
	
	let currentSchedule = scheduleArray.filter((element) => {
		let year = element.start.substring(0, 4);
		let month = element.start.substring(5, 7);
		if(year == curYear && month == curMonth) {
			return true;
		}
		return false;
	});
	
	currentSchedule.sort((a, b)=>{
		let aDate = new Date(a.start.substring(0, 10));
		let bDate = new Date(b.start.substring(0, 10));
		return aDate - bDate 
	})
	return currentSchedule;
}

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

