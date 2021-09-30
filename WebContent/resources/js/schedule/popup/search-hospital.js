let searchHosp = () => {
	//입력폼으로 값 전달
	opener.document.querySelector('.hospital').value = '병원';
	opener.document.querySelector('.hospCode').value = '300';
	window.close();
}