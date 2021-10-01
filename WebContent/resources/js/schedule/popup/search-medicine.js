let searchMed = () => {
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