
	//전체 체크버튼 클릭 시 전체체크 및 해제
	function allCheck(e) {
    	if(e.target.checked) {
      		document.querySelectorAll(".checkList").forEach(function(v, i) {
        		v.checked = true;
      		});
    	} else {
			document.querySelectorAll(".checkList").forEach(function(v, i) {
        	v.checked = false;
      		});
    	}
  	}
  
	//개별 체크버튼 클릭 시 전체 체크버튼 체크 및 해제
 	function checkList(e) {
    	let checkCount = 0;
			document.querySelectorAll(".checkList").forEach(function(v, i) {
      			if(v.checked === false){
        			checkCount++;
      			}
    		});

    	if(checkCount>0) {
      		document.getElementById("allCheckList").checked = false;
    	} else if(checkCount === 0) {
      		document.getElementById("allCheckList").checked = true;
    	}
  	}   		 
   		
	/* 즉시 실행함수로 넣어주면 전역 안에 포함이 안되기 때문에 외부에서의 접근을 막아줄 수 있다 */
   	(()=>{
	
		//전체체크박스 값을 일단 false로 잡아놓고, 회원가입 클릭 시 맨 아래코드에서 체크여부 확인
		document.getElementById('allCheckList').checked = false;
		
		//아이디 정규식검사 및 중복검사
   		let confirmId = '';
   		//영문소문자 또는 영문소문자+숫자,5-11글자
		let idReg = /^[a-z]{5,11}$|^[a-z0-9]{5,11}$/;

   		document.querySelector("#btnIdCheck").addEventListener('click', e => {
   			
   			/*id 속성값이 지정되어 있으면 id값으로 해당 요소객체 호출 가능*/
   			let id = userId.value;
   			
			/*우리 서버에 요청을 보내서 사용자가 입력한 아이디가 있는지 없는지 확인해야 하니까 우리서버와 통신이 발생해야 함 => fetch 사용*/
   			if(id){ /*userId가 null이 아니라면*/
				if(!idReg.test(userId.value)){
					e.preventDefault();
					alert("아이디는 영문소문자 또는 영문소문자+숫자로 이루어진 \n 5-11글자 입니다.");
					document.querySelector('#idCheck').innerHTML = '아이디는 영문소문자 또는 영문소문자+숫자로 이루어진 5-11글자 입니다.';
				}else{
					fetch('/member/id-check?userId=' + id)
   					.then(response => {
						if(!response.ok)
						throw new Error(`${response.statusText} : ${response.status}`);
						return response.text();
					}).then(text => {
						//console.dir(text);	
   						if(text == 'available'){
							/*alert("사용 가능한 아이디 입니다.");*/
   							document.querySelector('#idCheck').innerHTML = '사용 가능한 아이디 입니다.';
   							confirmId = id;
   						}else if(text == 'disable'){
							/*alert("이미 존재하는 아이디 입니다.");*/
   							document.querySelector('#idCheck').innerHTML = '이미 존재하는 아이디 입니다.';
   						}
   					}).catch(error => {
						document.querySelector('#idCheck').innerHTML = error; //에러가 발생할 경우 에러 찍어주기
					})	
				}
   			}
   		})

   		//닉네임 정규식검사 및 중복검사
   		let confirmNick = '';
   		let nickReg = /[a-zA-Z0-9가-힣]{2,10}/;
   		
		document.querySelector("#btnNickCheck").addEventListener('click', e => {
   			let nickName = nick.value;
   			
   			if(nickName){
				if(!nickReg.test(nick.value)){
					e.preventDefault();
					alert("닉네임은 2글자 이상의 문자열입니다.");
					document.querySelector('#nickCheck').innerHTML = '닉네임은 2글자 이상의 문자열입니다.';
				} else{
					fetch('/member/nickName-check?nick=' + nickName)
   					.then(response => {
						if(!response.ok)
							throw new Error(`${response.statusText} : ${response.status}`);
							return response.text();
					}).then(text => {
   						if(text == 'available'){
   							document.querySelector('#nickCheck').innerHTML = '사용 가능한 닉네임 입니다.';
   							confirmNick = nickName;
   						}else if(text == 'disable'){
   							document.querySelector('#nickCheck').innerHTML = '이미 존재하는 닉네임 입니다.';
   						}
   					}).catch(error => {
						document.querySelector('#nickCheck').innerHTML = error;
					})	
				}
			}
   		})

		//핸드폰번호 정규식검사 및 중복검사
   		let confirmPhone = '';
   		let phoneReg = /^\d{9,11}$/;
   		
		document.querySelector("#btnPhoneCheck").addEventListener('click', e => {
   			let phoneNum = phone.value;
   			
   			if(phoneNum){
   				if(!phoneReg.test(phone.value)){
					e.preventDefault();
					alert("휴대폰 번호는 9~11자리의 숫자입니다.");
   					document.querySelector('#phoneCheck').innerHTML = '휴대폰 번호는 9~11자리의 숫자입니다.';
				} else{
					fetch('/member/phone-check?phone=' + phoneNum)
   					.then(response => {
						if(!response.ok)
							throw new Error(`${response.statusText} : ${response.status}`);
							return response.text();
					}).then(text => {
   						if(text == 'available'){
   							document.querySelector('#phoneCheck').innerHTML = '사용 가능한 핸드폰번호 입니다.';
   							confirmPhone = phoneNum;
   						}else if(text == 'disable'){
   							document.querySelector('#phoneCheck').innerHTML = '이미 가입된 핸드폰번호 입니다.';
   						}
   					}).catch(error => {
						document.querySelector('#phoneCheck').innerHTML = error;
					})
				}
			}		
   		})

		//이메일 중복검사
   		let confirmEmail = '';
   	
   		document.querySelector("#btnEmailCheck").addEventListener('click', e => {
   			let emailAddress = email.value;
   			
   			if(emailAddress){
   				fetch('/member/email-check?email=' + emailAddress)
   				.then(response => {
					if(!response.ok)
						throw new Error(`${response.statusText} : ${response.status}`);
						return response.text();
				}).then(text => {	
   					if(text == 'available'){
   						document.querySelector('#emailCheck').innerHTML = '사용 가능한 이메일 입니다.';
   						confirmEmail = emailAddress;
   					}else if(text == 'disable'){
   						document.querySelector('#emailCheck').innerHTML = '이미 가입된 이메일 입니다.';
   					}
   				}).catch(error => {
					document.querySelector('#emailCheck').innerHTML = error;
				})
   			} 
   		})

   
   		
   		document.querySelector('#frm_join').addEventListener('submit', e => {
   			
   			let pwReg = /(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Zㄱ-힣0-9])(?=.{8,})/;
			let nameReg = /^[가-힣]{2,5}$|^[a-zA-Z]{2,}$/;

   			if(confirmId != userId.value){
   				e.preventDefault(); //submit태그의 동작을 중단시킴
				//alert("아이디 중복 검사를 통과하지 않았습니다.");
   				document.querySelector('#idCheck').innerHTML = '아이디 중복 검사를 통과하지 않았습니다.';
   			}

   			if(!pwReg.test(password.value)){
   				e.preventDefault();
				alert("비밀번호는 숫자, 영문자, 특수문자 조합의 \n 8자리 이상 문자열입니다.");
   				document.querySelector('#pwCheck').innerHTML = '비밀번호는 숫자, 영문자, 특수문자 조합의 8자리 이상 문자열입니다.';
   			}

			if(password.value != passwordCheck.value){
				e.preventDefault();
				alert("비밀번호가 일치하지 않습니다.");
			}

			if(!nameReg.test(name.value)){
				e.preventDefault();
				document.querySelector('#nameCheck').innerHTML = '이름을 올바르게 입력하세요.';
			}
   			
			if(confirmNick != nick.value){
   				e.preventDefault();
				//alert("닉네임 중복 검사를 통과하지 않았습니다.");
   				document.querySelector('#nickCheck').innerHTML = '닉네임 중복 검사를 통과하지 않았습니다.';
   			}

			if(confirmPhone != phone.value){
				e.preventDefault();
				//alert("휴대전화 중복 검사를 통과하지 않았습니다.");
				document.querySelector('#phoneCheck').innerHTML = '휴대폰번호 중복 검사를 통과하지 않았습니다.';
			}
			
			if(confirmEmail != email.value){
				e.preventDefault();
				document.querySelector('#emailCheck').innerHTML = '이메일 중복 검사를 통과하지 않았습니다.';
			}
			
			let check1 = document.getElementById('allCheckList').checked;
			if(check1 === false){
				e.preventDefault();
				alert("이용약관, 개인정보 수집 및 이용에 모두 동의하여 주십시오.");
			}
			
   		})
  

   	})();
	