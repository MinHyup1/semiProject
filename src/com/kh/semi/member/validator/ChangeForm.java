package com.kh.semi.member.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.kh.semi.member.model.service.MemberService;

public class ChangeForm {

	//회원가입 폼에서 파라미터로 보내는 애들을 전부 다 가져오고
	private String userId;
	private String password;
	private String nick;
	private String phone;
	private String address;
	private String email;
	private String gender;
	
	
	private HttpServletRequest request;
	private MemberService memberService = new MemberService();
	private Map<String, String> failedAttribute = new HashMap<String, String>(); //어떤 속성에 대해서 통과하지 못했는지 체크하기 위한 맵 생성
	
	public ChangeForm(HttpServletRequest request) {
		this.userId = request.getParameter("userId");
		this.request = request;
		this.password = request.getParameter("password");
		this.nick = request.getParameter("nick");
		this.phone = request.getParameter("phone");
		this.address = request.getParameter("address");
		this.email = request.getParameter("email");
		this.gender = request.getParameter("gender");

	}
	
	public boolean test() {
		
		boolean res = true;
		boolean valid = true;
		
	

		// 비밀번호가 영수특수문자 조합의 8자리 이상 문자열
		valid = Pattern.matches("(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Zㄱ-힣0-9]).{8,}", password); //자바 정규표현식이라 자릿수 부분 살짝 다름
		if (!valid) { //비밀번호 패턴이 통과되지 못했다면,
			failedAttribute.put("password", password); //실패한 값들 넣어주기
			res = false;
		}
		
		
		// 전화번호가 9~11자리의 숫자
		valid = Pattern.matches("^\\d{9,11}$", phone);
		if (!valid) { //전화번호 패턴이 통과되지 못했다면,
			failedAttribute.put("phone", phone); //실패한 값들 넣어주기
			res = false;
		}else if(memberService.selectMemberByPhone(phone) != null) {
			failedAttribute.put("phone", phone); //실패한 값들 넣어주기
			res = false;
		}
		
		
		//실패했다면, 실패한 속성 값들 세션에 넣어주기(세션에 넣어줘야 재요청이 들어왔을 때도 값을 가져와서 사용가능)
		if(!res) {
			request.getSession().setAttribute("joinFailed", failedAttribute);
			request.getSession().setAttribute("joinForm", this); //사용자가 입력한 파라미터 값이 joinForm에 있기 때문에 그 값들도 담아주기(this는 여기 있는 joinForm에 있는 값들을 뜻함..?)
		}else { //잘 통과했을 경우에는 지워주기
			request.getSession().removeAttribute("joinFailed");
			request.getSession().removeAttribute("joinForm");
		}
		
		
		return res;
	}
	
	//changeForm의 getter들만 추가

	public String getId() {
		return userId;
	}
	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}


	public String getNick() {
		return nick;
	}

	public String getAddress() {
		return address;
	}

	public String getGender() {
		return gender;
	}
	
	
	
	
}
