<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href='${contextPath}/resources/css/member/joinForm.css' rel='stylesheet'/>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="wrapper">
<form action="/member/join" method="post" id="frm_join" name="checkJoin">
	<table border=1 width="1000" height="500" bordercolor="gray" cellspacing=0>
		<tr class="bTag">
			<td class="bTag" colspan="2" align="center"><b>회원가입</b>
		</tr>
		<tr class="aTag">
			<td class="aTag" colspan="2" align="right">
			<a>*필수입력사항</a></td>
		</tr>
		<tr>
			<td width="200" align="center" class="title">* 아이디</td>
			<td class="inputInfo" width="500">
				<input type="text" size="30" name="userId" id="userId" maxlength="20" placeholder="영문소문자 or 영문소문자+숫자, 5~11 글자"
				 	<c:if test="${empty joinFailed.userId}">
				 		value = "${joinForm.userId}"
				 	</c:if>
				required />
				<input class="check" id="btnIdCheck" type="button" value="중복체크" />
				<span id="idCheck"  class="valid-msg" >
				</span>	
			</td>
		</tr>
		<tr>
			<td width="200" align="center" class="title">* 비밀번호</td>
			<td class="inputInfo" width="500">
				<input type="password" size="30" name="password" id="password" placeholder="특수문자+영어+숫자, 8글자 이상" 
					<c:if test="${empty joinFailed.password}">
						value = "${joinForm.password}"
					</c:if>
				required />
				<span id="pwCheck" class="valid-msg">
				</span>	
			</td>
		</tr>
		<tr>
			<td width="200" align="center" class="title">* 비밀번호 확인</td>
			<td class="inputInfo" width="500">
				<input type="password" size="30" name="passwordCheck" id="passwordCheck" required />
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
			    <input type="text" size="15" name="addressCode" id="addressCode" />
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
				<input type="email" size="30" name="email" id="email" maxlength="20"
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

	
		<ul class="join_box">
			<li class="checkBox check01">
				<ul class="clearfix">
					<li>이용약관, 개인정보 수집 및 이용에 모두 동의합니다.</li>
					<li class="checkAllBtn">
						<input type="checkbox" id="allCheckList" name="chkAll" onclick="allCheck(event)">
					</li>
				</ul>
			</li>
			<li class="checkBox check02">
				<ul class="clearfix">
					<li>이용약관 동의(필수)</li>
					<li class="checkBtn">
						<input type="checkbox" class="checkList" id="agree1" name="chk" onclick="checkList(event)">
					</li>
				</ul> <textarea name="" id="">제 1 조 (목적)
이 약관은 {MEDIBOOK}(이하 "사이트"라 합니다)에서 제공하는 인터넷서비스(이하 "서비스"라 합니다)의 이용 조건 및 절차에 관한 기본적인 사항을 규정함을 목적으로 합니다.
 
제 2 조 (약관의 효력 및 변경)
① 이 약관은 서비스 화면이나 기타의 방법으로 이용고객에게 공지함으로써 효력을 발생합니다.
② 사이트는 이 약관의 내용을 변경할 수 있으며, 변경된 약관은 제1항과 같은 방법으로 공지 또는 통지함으로써 효력을 발생합니다.

제 3 조 (용어의 정의)
이 약관에서 사용하는 용어의 정의는 다음과 같습니다.
① 회원 : 사이트와 서비스 이용계약을 체결하거나 이용자 아이디(ID)를 부여받은 개인 또는 단체를 말합니다.
② 신청자 : 회원가입을 신청하는 개인 또는 단체를 말합니다.
③ 아이디(ID) : 회원의 식별과 서비스 이용을 위하여 회원이 정하고 사이트가 승인하는 문자와 숫자의 조합을 말합니다.
④ 비밀번호 : 회원이 부여 받은 아이디(ID)와 일치된 회원임을 확인하고, 회원 자신의 비밀을 보호하기 위하여 회원이 정한 문자와 숫자의 조합을 말합니다.
⑤ 해지 : 사이트 또는 회원이 서비스 이용계약을 취소하는 것을 말합니다. 

제 2 장 서비스 이용계약

제 4 조 (이용계약의 성립)
① 이용약관 하단의 동의 버튼을 누르면 이 약관에 동의하는 것으로 간주됩니다.
② 이용계약은 서비스 이용희망자의 이용약관 동의 후 이용 신청에 대하여 사이트가 승낙함으로써 성립합니다.

제 5 조 (이용신청)
① 신청자가 본 서비스를 이용하기 위해서는 사이트 소정의 가입신청 양식에서 요구하는 이용자 정보를 기록하여 제출해야 합니다.
② 가입신청 양식에 기재하는 모든 이용자 정보는 모두 실제 데이터인 것으로 간주됩니다. 실명이나 실제 정보를 입력하지 않은 사용자는 법적인 보호를 받을 수 없으며, 서비스의 제한을 받을 수 있습니다.

제 6 조 (이용신청의 승낙)
① 사이트는 신청자에 대하여 제2항, 제3항의 경우를 예외로 하여 서비스 이용신청을 승낙합니다.
② 사이트는 다음에 해당하는 경우에 그 신청에 대한 승낙 제한사유가 해소될 때까지 승낙을 유보할 수 있습니다.
가. 서비스 관련 설비에 여유가 없는 경우
나. 기술상 지장이 있는 경우
다. 기타 사이트가 필요하다고 인정되는 경우
③ 사이트는 신청자가 다음에 해당하는 경우에는 승낙을 거부할 수 있습니다.
가. 다른 개인(사이트)의 명의를 사용하여 신청한 경우
나. 이용자 정보를 허위로 기재하여 신청한 경우
다. 사회의 안녕질서 또는 미풍양속을 저해할 목적으로 신청한 경우
라. 기타 사이트 소정의 이용신청요건을 충족하지 못하는 경우

제 7 조 (이용자정보의 변경)
회원은 이용 신청시에 기재했던 회원정보가 변경되었을 경우에는, 온라인으로 수정하여야 하며 변경하지 않음으로 인하여 발생되는 모든 문제의 책임은 회원에게 있습니다.

제 3 장 계약 당사자의 의무

제 8 조 (사이트의 의무)
① 사이트는 회원에게 각 호의 서비스를 제공합니다.
가. 신규서비스와 도메인 정보에 대한 뉴스레터 발송
나. 추가 도메인 등록시 개인정보 자동 입력
다. 도메인 등록, 관리를 위한 각종 부가서비스
② 사이트는 서비스 제공과 관련하여 취득한 회원의 개인정보를 회원의 동의없이 타인에게 누설, 공개 또는 배포할 수 없으며, 서비스관련 업무 이외의 상업적 목적으로 사용할 수 없습니다. 단, 다음 각 호의 1에 해당하는 경우는 예외입니다.
가. 전기통신기본법 등 법률의 규정에 의해 국가기관의 요구가 있는 경우
나. 범죄에 대한 수사상의 목적이 있거나 정보통신윤리 위원회의 요청이 있는 경우
다. 기타 관계법령에서 정한 절차에 따른 요청이 있는 경우
③ 사이트는 이 약관에서 정한 바에 따라 지속적, 안정적으로 서비스를 제공할 의무가 있습니다.

제 9 조 (회원의 의무)
① 회원은 서비스 이용 시 다음 각 호의 행위를 하지 않아야 합니다.
가. 다른 회원의 ID를 부정하게 사용하는 행위
나. 서비스에서 얻은 정보를 사이트의 사전승낙 없이 회원의 이용 이외의 목적으로 복제하거나 이를 변경, 출판 및 방송 등에 사용하거나 타인에게 제공하는 행위
다. 사이트의 저작권, 타인의 저작권 등 기타 권리를 침해하는 행위
라. 공공질서 및 미풍양속에 위반되는 내용의 정보, 문장, 도형 등을 타인에게 유포하는 행위
마. 범죄와 결부된다고 객관적으로 판단되는 행위
바. 기타 관계법령에 위배되는 행위
② 회원은 관계법령, 이 약관에서 규정하는 사항, 서비스 이용 안내 및 주의 사항을 준수하여야 합니다.
③ 회원은 내용별로 사이트가 서비스 공지사항에 게시하거나 별도로 공지한 이용 제한 사항을 준수하여야 합니다.

제 4 장 서비스 제공 및 이용

제 10 조 (회원 아이디(ID)와 비밀번호 관리에 대한 회원의 의무)
① 아이디(ID)와 비밀번호에 대한 모든 관리는 회원에게 책임이 있습니다. 회원에게 부여된 아이디(ID)와 비밀번호의 관리소홀, 부정사용에 의하여 발생하는 모든 결과에 대한 전적인 책임은 회원에게 있습니다.
② 자신의 아이디(ID)가 부정하게 사용된 경우 또는 기타 보안 위반에 대하여, 회원은 반드시 사이트에 그 사실을 통보해야 합니다.

제 11 조 (서비스 제한 및 정지)
① 사이트는 전시, 사변, 천재지변 또는 이에 준하는 국가비상사태가 발생하거나 발생할 우려가 있는 경우와 전기통신사업법에 의한 기간통신 사업자가 전기통신서비스를 중지하는 등 기타 불가항력적 사유가 있는 경우에는 서비스의 전부 또는 일부를 제한하거나 정지할 수 있습니다.
② 사이트는 제1항의 규정에 의하여 서비스의 이용을 제한하거나 정지할 때에는 그 사유 및 제한기간 등을 지체없이 회원에게 알려야 합니다.

제5장 계약사항의 변경, 해지

제 12 조 (정보의 변경)
회원이 주소, 비밀번호 등 고객정보를 변경하고자 하는 경우에는 홈페이지의 회원정보 변경 서비스를 이용하여 변경할 수 있습니다.

제 13 조 (계약사항의 해지)
회원은 서비스 이용계약을 해지할 수 있으며, 해지할 경우에는 본인이 직접 서비스를 통하거나 전화 또는 온라인 등으로 사이트에 해지신청을 하여야 합니다. 사이트는 해지신청이 접수된 당일부터 해당 회원의 서비스 이용을 제한합니다. 사이트는 회원이 다음 각 항의 1에 해당하여 이용계약을 해지하고자 할 경우에는 해지조치 7일전까지 그 뜻을 이용고객에게 통지하여 소명할 기회를 주어야 합니다.
① 이용고객이 이용제한 규정을 위반하거나 그 이용제한 기간 내에 제한 사유를 해소하지 않는 경우
② 정보통신윤리위원회가 이용해지를 요구한 경우
③ 이용고객이 정당한 사유 없이 의견진술에 응하지 아니한 경우
④ 타인 명의로 신청을 하였거나 신청서 내용의 허위 기재 또는 허위서류를 첨부하여 이용계약을 체결한 경우
사이트는 상기 규정에 의하여 해지된 이용고객에 대해서는 별도로 정한 기간동안 가입을 제한할 수 있습니다.

제6장 손해배상

제 14 조 (면책조항)
① 사이트는 회원이 서비스 제공으로부터 기대되는 이익을 얻지 못하였거나 서비스 자료에 대한 취사선택 또는 이용으로 발생하는 손해 등에 대해서는 책임이 면제됩니다.
② 사이트는 회원의 귀책사유나 제3자의 고의로 인하여 서비스에 장애가 발생하거나 회원의 데이터가 훼손된 경우에 책임이 면제됩니다.
③ 사이트는 회원이 게시 또는 전송한 자료의 내용에 대해서는 책임이 면제됩니다.
④ 상표권이 있는 도메인의 경우, 이로 인해 발생할 수도 있는 손해나 배상에 대한 책임은 구매한 회원 당사자에게 있으며, 사이트는 이에 대한 일체의 책임을 지지 않습니다.

제 15 조 (관할법원)

서비스와 관련하여 사이트와 회원간에 분쟁이 발생할 경우 사이트의 본사 소재지를 관할하는 법원을 관할법원으로 합니다.
       				</textarea>
			</li>
			<li class="checkBox check03">
				<ul class="clearfix">
					<li>개인정보 수집 및 이용에 대한 안내(필수)</li>
					<li class="checkBtn">
						<input type="checkbox" class="checkList" id="agree2" name="chk" onclick="checkList(event)">
					</li>
				</ul> <textarea name="" id="">
1. 개인정보의 처리 목적

{MEDIBOOK}은 다음의 목적을 위하여 개인정보를 처리하고 있으며, 다음의 목적 이외의 용도로는 이용하지 않습니다.
– 고객 가입의사 확인, 고객에 대한 서비스 제공에 따른 본인 식별.인증, 회원자격 유지.관리 등

2. 개인정보의 처리 및 보유 기간
① ‘MEDIBOOK’은 정보주체로부터 개인정보를 수집할 때 동의 받은 개인정보 보유․이용기간 또는 법령에 따른 개인정보 보유․이용기간 내에서 개인정보를 처리․보유합니다.

3. 정보주체와 법정대리인의 권리·의무 및 그 행사방법 이용자는 개인정보주체로써 다음과 같은 권리를 행사할 수 있습니다.
① 정보주체는 ‘MEDIBOOK’ 에 대해 언제든지 다음 각 호의 개인정보 보호 관련 권리를 행사할 수 있습니다.
1. 개인정보 열람요구
2. 오류 등이 있을 경우 정정 요구
3. 삭제요구
4. 처리정지 요구

4. 처리하는 개인정보의 항목 작성
① ‘MEDIBOOK’은 다음의 개인정보 항목을 처리하고 있습니다.

<‘MEDIBOOK’에서 수집하는 개인정보 항목>
‘MEDIBOOK’ 회원 가입 및 고객 문의 시, 제공 동의를 해주시는 개인정보 수집 항목입니다.

■ 회원 가입 시(회원)
– 필수항목 : 이름, 이메일, 전화번호, 닉네임
– 선택항목 : 주소
– 수집목적 : MEDIBOOK 회원관리
– 보유기간 : 회원 탈퇴 또는 동의철회 시 지체없이 파기

② ‘MEDIBOOK’은 만 14세 미만 아동의 개인정보를 보호하기 위하여 회원가입은 만14세 이상만 가능하도록 함으로써 아동의 개인정보를 수집하지 않습니다.

5. 개인정보의 파기

‘MEDIBOOK’은 원칙적으로 개인정보 처리목적이 달성된 경우에는 지체없이 해당 개인정보를 파기합니다. 파기의 절차, 기한 및 방법은 다음과 같습니다.’

-파기절차
이용자가 입력한 정보는 목적 달성 후 별도의 DB에 옮겨져(종이의 경우 별도의 서류) 내부 방침 및 기타 관련 법령에 따라 일정기간 저장된 후 혹은 즉시 파기됩니다. 이 때, DB로 옮겨진 개인정보는 법률에 의한 경우가 아니고서는 다른 목적으로 이용되지 않습니다.

-파기기한
이용자의 개인정보는 개인정보의 보유기간이 경과된 경우에는 보유기간의 종료일로부터 5일 이내에, 개인정보의 처리 목적 달성, 해당 서비스의 폐지, 사업의 종료 등 그 개인정보가 불필요하게 되었을 때에는 개인정보의 처리가 불필요한 것으로 인정되는 날로부터 5일 이내에 그 개인정보를 파기합니다.

				
       </textarea>
			</li>

		</ul>
		
		<div class="footBtwrap">
			<a><input type="submit" value="회원가입" class="fpmgBt1" /></a>
			<a href="/member/joinCancel"><input type="button" value="가입취소" class="fpmgBt2" /></a>
		</div>
</form>
</div>

<script src='${contextPath}/resources/js/member/joinForm.js'></script>

</body>
</html>