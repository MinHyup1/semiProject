/*let searchMed = () => {
	//입력폼으로 값 전달
	let medi = document.createElement('span');
	medi.innerHTML = "<input name='medicine' class='medicine' value='약 이름' readonly><input value='199402278' name='mediCode' id='code'>";
	
	let trash = document.createElement('i');
	trash.className = "fas fa-trash";
	trash.id = "medi-trash";

	medi.appendChild(trash);
	
	opener.document.querySelector('.medi-list').appendChild(medi);	
	
	window.close();
}
*/
document.querySelector('.search').addEventListener('submit', e => {
	let name = document.querySelector('input[type=text]').value;
	if(!name) {
		e.preventDefault();
		alert('검색어를 입력하세요');
	}
})

document.querySelectorAll('.medImg').forEach(el => {
	el.addEventListener('click', e => {
		let tr = e.target.parentElement.parentElement;
		let medName = tr.children[1].children[0].text;
		let medCode = tr.children[1].children[1].textContent;
		
		let medi = document.createElement('span');
		medi.innerHTML = "<input name='medicine' class='medicine' value=" + medName + " readonly><input value=" + medCode + " name='mediCode' id='code'>";
		
		let trash = document.createElement('i');
		trash.className = "fas fa-trash";
		trash.id = "medi-trash";
	
		medi.appendChild(trash);
		opener.document.querySelector('.medi-list').appendChild(medi);
		window.close();
	})
})

document.querySelectorAll('.medName').forEach(el => {
	el.addEventListener('click', e => {
		let tr = e.target.parentElement.parentElement;
		let medName = tr.children[1].children[0].text;
		let medCode = tr.children[1].children[1].textContent;
		
		let medi = document.createElement('span');
		medi.innerHTML = "<input name='medicine' class='medicine' value=" + medName + " readonly><input value=" + medCode + " name='mediCode' id='code'>";
		
		let trash = document.createElement('i');
		trash.className = "fas fa-trash";
		trash.id = "medi-trash";
	
		medi.appendChild(trash);
		opener.document.querySelector('.medi-list').appendChild(medi);
		window.close();
	})
})