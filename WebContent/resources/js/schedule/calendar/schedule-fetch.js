(()=>{
	fetch('/schedule/get-schedule', {method: 'POST'})
	.then(response => {
		if(response) {
			return response.json();
		}
	}).then(json => {
		sessionStorage.setItem('schedule', JSON.stringify(json));
	})
})();

/*let getSchedule = async () => {
	
	let response = await fetch('/schedule/get-schedule');
	let data;
	if(response) {
		data = await response.json();
	}
	sessionStorage.setItem('schedule', JSON.stringify(data));
	
}

await getSchedule();*/
