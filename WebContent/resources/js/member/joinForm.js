
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

   		 
		/*   		
		1. 이미 존재하는 아이디로 사용자가 가입하려는 경우
   		2. 비밀번호는 영어, 숫자, 특수문자 조합의 8자리 이상의 문자열
   		3. 전화번호는 숫자로만 입력
		*/
   		 
   		
	/* 즉시 실행함수로 넣어주면 전역 안에 포함이 안되기 때문에 외부에서의 접근을 막아줄 수 있다 */
   	(()=>{
   		let confirmId = '';
   		
   		document.querySelector("#btnIdCheck").addEventListener('click', e => {
   			
   			/*id 속성값이 지정되어 있으면 id값으로 해당 요소객체 호출 가능*/
   			let id = userId.value;
   			
			/*우리 서버에 요청을 보내서 사용자가 입력한 아이디가 있는지 없는지 확인해야 하니까 우리서버와 통신이 발생해야 함 => fetch 사용*/
   			if(id){ /*userId가 null이 아니라면*/
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
   		})

   	
   		let confirmNick = '';
   		
   		document.querySelector("#btnNickCheck").addEventListener('click', e => {
   			
   			/*nick 속성값이 지정되어 있으면 nick값으로 해당 요소객체 호출 가능*/
   			let nickName = nick.value;
   			
			/*우리 서버에 요청을 보내서 사용자가 입력한 닉네임이 있는지 없는지 확인해야 하니까 우리서버와 통신이 발생해야 함 => fetch 사용*/
   			if(nickName){ /*nick이 null이 아니라면*/
   				fetch('/member/nickName-check?nick=' + nickName)
   				.then(response => {
					if(!response.ok)
						throw new Error(`${response.statusText} : ${response.status}`);
					return response.text();
				}).then(text => {
					console.dir(text);
						
   					if(text == 'available'){
   						document.querySelector('#nickCheck').innerHTML = '사용 가능한 닉네임 입니다.';
   						confirmNick = nickName;
   					}else if(text == 'disable'){
   						document.querySelector('#nickCheck').innerHTML = '이미 존재하는 닉네임 입니다.';
   					}
   				}).catch(error => {
					document.querySelector('#nickCheck').innerHTML = error; //에러가 발생할 경우 에러 찍어주기
				})
   			}
   		})
   				
   
   		
   		document.querySelector('#frm_join').addEventListener('submit', e => {
   			
   			let pwReg = /(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Zㄱ-힣0-9])(?=.{8,})/;
   			let phoneReg = /^\d{9,11}$/;
   			
   			if(confirmId != userId.value){
   				e.preventDefault();
   				document.querySelector('#idCheck').innerHTML = '아이디 중복 검사를 통과하지 않았습니다.';
   			}

   			if(!pwReg.test(password.value)){
   				e.preventDefault();
   				document.querySelector('#pwCheck').innerHTML = '비밀번호는 숫자, 영문자, 특수문자 조합의 8자리 이상 문자열입니다.';
   			}
   			
			if(confirmNick != nick.value){
   				e.preventDefault();
   				document.querySelector('#nickCheck').innerHTML = '닉네임 중복 검사를 통과하지 않았습니다.';
   			}

   			if(!phoneReg.test(phone.value)){
   				e.preventDefault();
   				document.querySelector('#phoneCheck').innerHTML = '휴대폰 번호는 9~11자리의 숫자입니다.';
   			}
   		})
   	








   	})();