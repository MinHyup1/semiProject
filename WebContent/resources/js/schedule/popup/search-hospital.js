/*let searchHosp = () => {
	//입력폼으로 값 전달
	/*opener.document.querySelector('.hospital').value = '병원';
	opener.document.querySelector('.hospCode').value = '300';
	window.close();*/
/*	let name = document.querySelector('input[type=text]').value;
	
	if(!name) {
		alert('검색어를 입력하세요');
		preventD
	}
}*/

document.querySelector('.search').addEventListener('submit', e => {
	let name = document.querySelector('input[type=text]').value;
	if(!name) {
		e.preventDefault();
		alert('검색어를 입력하세요');
	}
})