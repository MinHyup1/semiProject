let getNotice = async () => {
	let response = await fetch('/schedule/get-schedule');
	let datas = await response.json();
	return datas;
};

(async () => {
	scheduleArray = await getNotice();
	let noticeArray = scheduleArray.filter(el => {
		if(el.kind == 'medical') {
			return false;
		}
		return true;
	})
	
	let startInThisWeek = noticeArray.filter(el => {
		let todayDateTime = new Date();
		let today = new Date(todayDateTime.getFullYear(), todayDateTime.getMonth(), todayDateTime.getDate());
		let date = getDate(el.start);
		let days = (date - today)/24/60/60/1000;
		if(days > 0 && days < 8) {
			return true;
		}
		return false;
	})
	
	let noticeNow = noticeArray.filter(el => {
		let todayDateTime = new Date();
		let today = new Date(todayDateTime.getFullYear(), todayDateTime.getMonth(), todayDateTime.getDate());
		if(el.kind == 'prescription') {
			let start = getDate(el.start);
			let startDays = (start-today)/24/60/60/1000;
			let end = getDate(el.end);
			let endDays = (today-end)/24/60/60/1000;
			console.dir(startDays);
			console.dir(endDays);
			if(startDays <= 0 && endDays >= 0) {
				return true;
			}
		}
		return false;
	})
	
	if(noticeNow.length == 0 && startInThisWeek.length == 0) {
		document.querySelector('.badge').innerHTML = '';
		return;
	}else{
		document.querySelector('.badge').innerHTML = (noticeNow.length + startInThisWeek.length);
	}
	
	startInThisWeek.sort((a, b)=>{
		let aDate = new Date(a.start.substring(0, 10));
		let bDate = new Date(b.start.substring(0, 10));
		return aDate - bDate;
	})
	console.dir(startInThisWeek);
	noticeNow.sort((a, b)=>{
		let aDate = new Date(a.start.substring(0, 10));
		let bDate = new Date(b.start.substring(0, 10));
		return aDate - bDate;
	})
	console.dir(noticeNow);
	
	document.querySelector('.notifications').innerHTML = '';
	
	noticeNow.forEach(e=>{
		document.querySelector('.notifications').innerHTML += 
		"<li><a href='/schedule/schedule-main' class='notification-item'><span class='dot bg-danger'></span>" + e.title + "</a>"
	})
	
	startInThisWeek.forEach(e=>{
		if(e.kind == 'visit') {
			document.querySelector('.notifications').innerHTML += 
			"<li><a href='/schedule/schedule-main' class='notification-item'><span class='dot bg-success'></span>" + e.title + "</a>"
		}else{
			document.querySelector('.notifications').innerHTML += 
			"<li><a href='/schedule/schedule-main' class='notification-item'><span class='dot bg-warning'></span>" + e.title + "</a>"
		}
	})
	
})();

let getDate = (dateStr) => {
	dateArr = dateStr.split('-');
	let date = new Date(dateArr[0], dateArr[1]-1, dateArr[2]);
	return date;
}

