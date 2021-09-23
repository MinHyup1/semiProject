(() => {
	
	let dateReg = /^\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01])$/;
	
	document.querySelector('#dose_start').addEventListener('input', e => {
		let doseStart = dose_start.value;
		let doseEnd = dose_end.value;
		
		if(doseStart && !doseEnd) {
			document.querySelector('#dose_end').value = doseStart;
		}
		
	})
	
	document.querySelector('#input_form').addEventListener('submit', e => {
		
		if(!dateReg.test(schedule_date.value)) {
			e.preventDefault();
			document.querySelector('.standard_date').style.borderColor = 'red';
		}
		
		let doseStart = dose_start.value;
		let doseEnd = dose_end.value;
		
		let startArr = doseStart.split('-');
		let endArr = doseEnd.split('-');
		
		console.dir(doseStart);
		console.dir(doseEnd);
		
		
	})
	
})();