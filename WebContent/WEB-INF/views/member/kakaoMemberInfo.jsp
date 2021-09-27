<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href='${contextPath}/resources/css/member/kakaoMemberInfo.css' rel='stylesheet'/>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<title>Insert title here</title>
</head>
<body>
<div id="wrapper">
<form action="/member/kakaoChange" method="post" id="frm_memberInfo">
	<table border=1 width="1000" height="500" bordercolor="gray" cellspacing=0>
		<tr class="bTag">
			<td class="bTag" colspan="2" align="center"><b>회원정보수정</b>
		</tr>
		<tr class="aTag">
			<td class="aTag" colspan="2" align="right">
			<a>*필수입력사항</a></td>
		</tr>
		<tr>
			<td width="200" align="center" class="title">* 아이디</td>
			<td width="500">
				<span class="fixedSpan">${authentication.id}</span>
			</td>
		</tr>
		<tr>
			<td width="200" align="center" class="title">* 이름</td>
			<td width="500">
				<input type="text" size="30" name="name" id="name" required />
				<span id="nameCheck" class="valid-msg"></span>
			</td>
		</tr>
		<tr>
			<td width="200" align="center" class="title">* 닉네임</td>
			<td class="inputInfo" width="500">
				<input type="text" size="30" name="nick" id="nick" placeholder="2글자 이상" 
					<c:if test="${empty joinFailed.nick}">
						value = "${joinForm.nick}"
					</c:if>
				required />
				<input class="check" id="btnNickCheck" type="button" value="중복체크" />
				<span id="nickCheck" class="valid-msg">
				</span>	
			</td>
		</tr>
		<tr>
			<td width="200" align="center" class="title">* 휴대전화</td>
			<td width="500">
				<input type="text" size="30" name="phone" id="phone" maxlength="20" placeholder="숫자만 입력, 9~11 자리" 
					<c:if test="${empty joinFailed.phone}">
                		value = "${joinForm.phone}"
                	</c:if>
				required />
				<input class="check" id="btnPhoneCheck" type="button" value="중복체크" />
				<span  id="phoneCheck" class="valid-msg">
				</span>	
			</td>
		</tr>
		<tr>
			<td width="200" rowspan="3" align="center" class="title">주소</td>
			<td width="200" class="code">
			    <input type="text" size="15" name="postCode" id="postCode" readonly="readonly"/>
				<input class="check" type="button" value="우편번호 검색" onclick="postCodeSearch()" />
			</td>
		</tr>
		<tr>
			<td class="inputInfo code ad">
				<input type="text" size="50" name="address1" id="address1" /> 기본주소
			</td>
		</tr>
		<tr>
			<td class="inputInfo ad">
				<input type="text" size="50" name="address2" id="address2" /> 나머지주소(선택입력가능)
			</td>
		</tr>
		<tr>
			<td width="200" align="center" class="title">* 이메일</td>
			<td width="500">
				<input type="email" size="30" name="email" id="email"
					<c:if test="${empty joinFailed.email}">
                		value = "${joinForm.email}"
                	</c:if>
				required />
				<input class="check" id="btnEmailCheck" type="button" value="중복체크" />
				<span  id="emailCheck" class="valid-msg">
				</span>		
			</td>
		</tr>
	</table>
		<div class="footBtwrap">
			<a id="modify"><input type="submit" value="회원정보수정" class="fpmgBt1"></a>
			<a id="cancel"href="/member/changeCancel"><input type="button" value="취소" class="fpmgBt2"></a>
			<a href="/member/delete" onclick="unlinkApp()"id="out"><input type="button" value="회원탈퇴" class="fpmgBt3"></a>
		</div>
	
</form>
</div>

<!--     <script>
        // SDK를 초기화 합니다. 사용할 앱의 JavaScript 키를 설정해 주세요.
        /* Kakao.init('cfb699a7c2c194f945a6a34a6be6daa5'); */
		Kakao.init('8406376e4a3016276da81540af46ba74');
        
        // SDK 초기화 여부를 판단합니다.
        console.log(Kakao.isInitialized());
        
    </script> -->

<!-- <script type="text/javascript">
  function unlinkApp() {
      if (!Kakao.Auth.getAccessToken()) {
        return
      }
      Kakao.Auth.logout(function() {
      })
    }
  Kakao.API.request({
      url: '/v1/user/unlink',
      success: function(res) {
      },
      fail: function(err) {
      },
    })

  </script>
 -->


<script src='${contextPath}/resources/js/member/changeForm.js'></script>
<script src='${contextPath}/resources/js/member/kakaoMemberInfo.js'></script>

</body>
</html>