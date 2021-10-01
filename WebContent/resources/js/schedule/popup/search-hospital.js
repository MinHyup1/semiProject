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

document.querySelectorAll('.hospName').forEach(el => {
	el.addEventListener('click', e => {
		let tr = e.target.parentElement.parentElement;
		opener.document.querySelector('.hospital').value = tr.children[0].children[0].text;
		opener.document.querySelector('.hospCode').value = tr.children[0].children[1].textContent;
		window.close();
	})
})

document.querySelectorAll('.hospAddr').forEach(el => {
	el.addEventListener('click', e => {
		console.dir(e.target.parentElement.parentElement);
		let tr = e.target.parentElement.parentElement;
		opener.document.querySelector('.hospital').value = tr.children[0].children[0].text;
		opener.document.querySelector('.hospCode').value = tr.children[0].children[1].textContent;
		window.close();
	})
})