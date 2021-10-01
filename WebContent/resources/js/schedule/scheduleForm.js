(() => {
	
	let dateReg = /^\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01])$/;
	let timeReg = /^([0-1]\d|2[0-3]):([0-5]\d)/;
	
	if(document.querySelector('.dose_start')) {
		document.querySelector('.dose_start').addEventListener('input', e => {
			let doseStart = dose_start.value;
			let doseEnd = dose_end.value;
			
			if(doseStart && !doseEnd) {
				document.querySelector('.dose_end').value = doseStart;
			}
			
			if(!doseStart && !doseEnd) {
				document.querySelector('.dose_end').required = false;
				document.querySelector('.dose_start').required = false;
			}else {
				document.querySelector('.dose_end').required = true;
				document.querySelector('.dose_start').required = true;
			}
			
		})
	}
	
	if(document.querySelector('.dose_end')) {
		document.querySelector('.dose_end').addEventListener('input', e => {
			if(!dose_start.value && !dose_end.value) {
				document.querySelector('.dose_end').required = false;
				document.querySelector('.dose_start').required = false;
			}else {
				document.querySelector('.dose_end').required = true;
				document.querySelector('.dose_start').required = true;
			}
		})
	}
	
	if(document.querySelector('#dose_start')) {
		document.querySelector('#dose_start').addEventListener('input', e => {
			if(e.target.value) {
				document.querySelector('#dose_end').min = e.target.value;
			}
		})
	}
	
	document.querySelector('#input_form').addEventListener('submit', e => {
		document.querySelectorAll('.valid-msg').forEach(element => {
			element.innerHTML = '';
		})
		
		if(document.querySelector('#schedule_date')) {
			if(!(dateReg.test(schedule_date.value) && validateDate(schedule_date.value))) {
				e.preventDefault();
				document.querySelector('#scheduleDateCheck').innerHTML = '날짜 입력이 잘못되었습니다.';
			}
		}
		
		if(document.querySelector('#dose_start')) {
			if(dose_start.value) {
				if(!(dateReg.test(dose_start.value) && validateDate(dose_start.value))) {
					e.preventDefault();
					document.querySelector('#startDateCheck').innerHTML = '날짜 입력이 잘못되었습니다.';
				}
			}
		}
		
		if(document.querySelector('#dose_end')) {
			if(dose_end.value) {
				if(!(dateReg.test(dose_end.value) && validateDate(dose_end.value))) {
					e.preventDefault();
					document.querySelector('#endDateCheck').innerHTML = '날짜 입력이 잘못되었습니다.';
				}
			}
		}
		
		if(document.querySelectorAll('.dateTime')) {
			document.querySelectorAll('.dateTime').forEach(element => {
				let visitDate = element.value.split('T')[0];
				let visitTime = element.value.split('T')[1];
				
				if(!(dateReg.test(visitDate) && validateDate(visitDate) && timeReg.test(visitTime))) {
					e.preventDefault();
					document.querySelector('#dateTimeCheck').innerHTML = '진료 알림 설정에 잘못된 입력이 있습니다.';
					return;
				}
			})
		}
		
		if(document.querySelectorAll('.time').length != 0) {
			if(!dose_start.value || !dose_end.value) {
				e.preventDefault();
				document.querySelector('#dose_start').required = true;
				document.querySelector('#dose_end').required = true;
			}
			
			document.querySelectorAll('.time').forEach(element => {
				if(!timeReg.test(element.value)) {
					e.preventDefault();
					document.querySelector('#timeCheck').innerHTML = '복용 알림시간 설정에 잘못된 입력이 있습니다.';
					return;
				}
			})
		}
	})
	
	
	let validateDate = (inputDate) => {
		let dateArr = inputDate.split('-');
		let date = new Date(dateArr[0], dateArr[1]-1, dateArr[2]);
		
		if(dateArr[0] != date.getFullYear()) {
			return false;
		}if(dateArr[1] != date.getMonth() +1) {
			return false;
		}if(dateArr[2] != date.getDate()) {
			return false;
		}
		return true;
	}
	
	
})();

window.addEventListener('unload', e => {
	if(docuemnt.querySelectorAll('.medicine')) {
		document.querySelectorAll('#medi-trash').forEach(el => {
			console.dir(el);
		})
	}
})

let createHospitalPopup = () => {
	let positionX = screen.width/2 - (450/2);
	let positionY = screen.height/2 - (700/2);
	let windowFeatures = `width=510px, height=700px, left=${positionX}, top=${positionY}, resizable=no`;
	
	let popup = open("/schedule/popup/hospital-popup", "search", windowFeatures);
	
	return popup;
}

let createMedicinePopup = () => {
	let positionX = screen.width/2 - (450/2);
	let positionY = screen.height/2 - (700/2);
	let windowFeatures = `width=510px, height=700px, left=${positionX}, top=${positionY}, resizable=no`;
	
	let popup = open("/schedule/popup/medicine-popup", "search", windowFeatures);
		popup.addEventListener('beforeunload', e => {
			document.querySelectorAll('#medi-trash').forEach(el => {
				el.addEventListener('click', e => {		
				e.target.parentElement.remove();
			})
		})
	})
	
	return popup;
}

let createPharmPopup = () => {
	let positionX = screen.width/2 - (450/2);
	let positionY = screen.height/2 - (700/2);
	let windowFeatures = `width=510px, height=700px, left=${positionX}, top=${positionY}, resizable=no`;
	
	let popup = open("/schedule/popup/pharm-popup", "search", windowFeatures);
	
	return popup;
}