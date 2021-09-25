<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="fullscreen-bg">
<head>
<link href='${contextPath}/resources/css/member/joinPage.css' rel='stylesheet'/>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div id="wrapper">
		<div class="vertical-align-wrap">
			<div class="vertical-align-middle">
				<div class="auth-box ">
					<div class="left">
						<div class="content">
							<div class="header">
								<div class="logo text-center"><p>MEDIBOOK</p></div>
								<p class="joinInfo">회원가입</p>
								<p class="lead">카카오톡으로 간편하게 회원가입 하세요.</p>
							</div>
							<form class="form-auth-small">
								<div class="form-group">
									<a><button class="btn btn-lg kakao" onclick="loginWithKakao()">카카오톡 간편 회원가입</button></a>
								</div>
								<a id="or">또는</a>
								<a><button formaction="/member/basicJoin" type="submit" class="btn btn-primary btn-lg btn-block">일반 회원가입</button></a>
							</form>
						</div>
					</div>

					<div class="clearfix"></div>
				</div>
			</div>
		</div>
	</div>
	
	 <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
    <script>
        // SDK를 초기화 합니다. 사용할 앱의 JavaScript 키를 설정해 주세요.
        /* Kakao.init('cfb699a7c2c194f945a6a34a6be6daa5'); */
		Kakao.init('8406376e4a3016276da81540af46ba74');
        
        // SDK 초기화 여부를 판단합니다.
        console.log(Kakao.isInitialized());
        
    </script>
    
<script type="text/javascript">
  
  //const CLIENT_ID = "24bac85b97d9ea780242e6c5a1b32da3";
  //const REDIRECT_URI =  "http://localhost:9090/member/loginPage";
  //const KAKAO_AUTH_URL = `https://kauth.kakao.com/oauth/authorize?client_id=${CLIENT_ID}&redirect_uri=${REDIRECT_URI}&response_type=code`;
  
 
  function loginWithKakao(){
	  Kakao.Auth.login({
	        success: function(authObj) {
	          //로그인 성공시, API 호출
	          Kakao.API.request({
	            url: '/v2/user/me', //사용자 정보를 읽어들이는 고정된 url
	            success: function(res) {
	            	alert(JSON.stringify(res)); //<---- kakao.api.request 에서 불러온 결과값 json형태로 출력
	                alert(JSON.stringify(authObj));  //<----Kakao.Auth.createLoginButton에서 불러온 결과값 json형태로 출력
	                console.log(res.id);
	                console.log(res.kaccount_email);
	                console.log(res.properties['nickname']);
	            	console.log(res);
	            	console.log(authObj.access_token);
	              
					alert('로그인성공');
	              	//location.href="/index";
	              	
	        	}
	          })
	        },
	        fail: function(err) {
	          alert(JSON.stringify(err));
	        }
	  });
  }
  
  
</script>
	
</body>
</html>