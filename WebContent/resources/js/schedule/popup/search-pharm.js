let searchPharm = () => {
	//입력폼으로 값 전달
	opener.document.querySelector('.pharm').value = '약국';
	opener.document.querySelector('.pharmCode').value = '약국코드';
	window.close();
}