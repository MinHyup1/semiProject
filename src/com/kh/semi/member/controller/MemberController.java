package com.kh.semi.member.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.common.code.ErrorCode;
import com.kh.semi.common.exception.DataAccessException;
import com.kh.semi.common.exception.HandlableException;
import com.kh.semi.common.exception.PageNotFoundException;
import com.kh.semi.member.model.dto.Member;
import com.kh.semi.member.model.service.MemberService;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MemberService memberService = new MemberService();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uriArr = request.getRequestURI().split("/");
		
		switch (uriArr[uriArr.length-1]) {
		case "login" : //로그인 처리 코드
			login(request,response);
			break;
		case "kakaoLogin" : //카카오 로그인 처리 코드
			kakaoLogin(request,response);
			break;
		case "logout" ://로그아웃 코드
			logout(request,response);
			break;
		case "loginPage" : //로그인 페이지로 이동
			loginPage(request,response);
			break;
		case "joinPage": //회원가입 페이지로 이동
			joinPage(request,response);
			break;
		case "basicJoin": //일반회원가입 폼으로 이동
			basicJoin(request,response);
			break;
		case "join" :   //회원가입 진행
			join(request,response);
			break;
		case "joinCancel" : //가입 취소
			joinCancel(request,response);
			break;
		case "id-check" : //아이디 중복 체크
			checkId(request,response);
			break;
		case "nickName-check" : //닉네임 중복 체크
			checkNick(request,response);
			break;
		case "phone-check" : //핸드폰번호 중복 체크
			checkPhone(request,response);
			break;
		case "email-check" : //이메일 중복 체크
			checkEmail(request,response);
			break;
		case "join-impl" : 
			joinImpl(request,response);
			break;		
		case "findId" : //아이디 찾기
			findId(request,response);
			break;
		case "findId-info" : //아이디 찾기 정보 제공 페이지
			findIdInfo(request,response);
			break;
		case "findPassword" : //비밀번호 찾기
			findPassword(request,response);
			break;
		case "findPassword-info" : //비밀번호 찾기 정보제공
			findPasswordInfo(request,response);
			break;
		case "kakaoChange" : //카카오톡 회원 정보 수정
			kakaoChange(request,response);
			break;
		case "delete" : //회원탈퇴
			delete(request,response);
			break;
		case "changeForm" : //일반회원 정보 수정 페이지로 이동
			changeForm(request,response);
			break;
		case "change": //회원정보수정 처리
			change(request,response);
			break;
		case "kakaoMemberForm" : //카카오톡 회원정보수정 페이지로 이동
			kakaoMemberForm(request,response);
			break;
		case "newKakaoMember" : //카카오톡 회원정보수정 페이지로 이동
			newKakaoMember(request,response);
			break;
		case "changeCancel" : //회원정보수정 취소
			changeCancel(request,response);
			break;
		case "memberInfo" :
			memberInfo(request,response);
			break;
		case "mypage" :
			mypage(request,response);
			break;
		default: throw new PageNotFoundException();  //우리가 만든 예외처리 클래스 넣어주기
		
		}
	}
	


	private void mypage(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		request.getRequestDispatcher("/member/mypage").forward(request, response);		
	}

	private void newKakaoMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/newKakaoMember").forward(request, response);
		
	}

	private void memberInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/memberInfo").forward(request, response);
		
	}

	private void changeCancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("msg", "회원수정이 취소되었습니다. 메인페이지로 이동합니다.");
		request.setAttribute("url", "/index");
		request.getRequestDispatcher("/error/result").forward(request, response);
		
	}

	private void kakaoMemberForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/kakaoMemberInfo").forward(request, response);
		}

	//카카오톡 로그인 멤버가 회원수정하는 코드
	private void kakaoChange(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member member = new Member();
		member = (Member) request.getSession().getAttribute("authentication");
 
		System.out.println("넘어온 어트리뷰트 확인 : "+ member);
		
		String userId = member.getId(); 
		String name = request.getParameter("name");
		String nick = request.getParameter("nick");
		String phone = request.getParameter("phone");
		String postCode = request.getParameter("postCode");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");

		member.setId(userId);
		member.setName(name);
		member.setNick(nick);
		member.setPhone(phone);
		member.setAddress(postCode, address1, address2);
		member.setEmail(email);
		member.setGender(gender);
		member.setKakaoNum(2);
		
		memberService.UpdateMember(member);
		
		request.setAttribute("msg", "회원 수정이 완료되었습니다."); 
		request.setAttribute("url", "/index");
		request.getRequestDispatcher("/error/result").forward(request, response);
	}

	private void change(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

		Member member = new Member();
		member = (Member) request.getSession().getAttribute("authentication");
		String userId = member.getId();
		String password = request.getParameter("password");
		String name = member.getName();
		String nick = request.getParameter("nick");
		String phone = request.getParameter("phone");
		String postCode = request.getParameter("postCode");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");

		// 받아 온 정보들 member에 넣어주기
		member.setId(userId);
		member.setPassword(password);
		member.setName(name);
		member.setNick(nick);
		member.setPhone(phone);
		member.setAddress(postCode, address1, address2);
		member.setEmail(email);
		member.setGender(gender);
		
		memberService.UpdateMember(member);

		request.setAttribute("msg", "회원 수정이 완료되었습니다."); 
		request.setAttribute("url", "/member/loginPage");
		request.getRequestDispatcher("/error/result").forward(request, response);
			
	}

	private void changeForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/member/memberInfo").forward(request, response);
		}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String userId =((Member) request.getSession().getAttribute("authentication")).getId();//멤버객체에 authentication를 집어넣는다
		memberService.deleteMember(userId);//삭제 진행
		request.getSession().removeAttribute("authentication");
		request.setAttribute("msg", "회원탈퇴가 완료되었습니다."); 
		request.setAttribute("url", "/index");
		request.getRequestDispatcher("/error/result").forward(request, response);
		//logout(request, response);//로그아웃 사용해 세션 끊고 인덱스로 이동
	}

	private void findPassword(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		request.getRequestDispatcher("/member/findPassword").forward(request, response);
	}
	
	private void findPasswordInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String tell = request.getParameter("tell");
		
		Member member = memberService.findUserPassword(userId, userName, email, tell);
		Member memberId = memberService.selectMemberById(userId);
		Member memberEmail = memberService.selectMemberByEmail(email);
		Member memberTell = memberService.selectMemberByPhone(tell);
		
		if(memberId == null) {
			request.setAttribute("msg", "존재하지 않는 아이디 입니다.~~~"); 
			request.setAttribute("url", "/member/findPassword");
			request.getRequestDispatcher("/error/result").forward(request, response);
			return;
		} else if(memberEmail == null) {
			request.setAttribute("msg", "존재하지 않는 이메일 입니다."); 
			request.setAttribute("url", "/member/findPassword");
			request.getRequestDispatcher("/error/result").forward(request, response);
			return;
		} else if(memberTell == null) {
			request.setAttribute("msg", "존재하지 않는 휴대폰 번호 입니다."); 
			request.setAttribute("url", "/member/findPassword");
			request.getRequestDispatcher("/error/result").forward(request, response);
			return;
		} else if(memberId != null && memberEmail != null && memberTell != null && member == null) {
			request.setAttribute("msg", "존재하지 않는 이름 입니다."); 
			request.setAttribute("url", "/member/findPassword");
			request.getRequestDispatcher("/error/result").forward(request, response);
			return;
		} else if(member != null){
			request.getSession().setAttribute("authentication", member);
			request.getRequestDispatcher("/member/checkpassword").forward(request, response);
			return;
		}
	}

	private void findId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/findId").forward(request, response);
		
	}
	
	private void findIdInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String tell = request.getParameter("tell");
		
		Member member = memberService.findUserId(userName, email, tell);
		Member memberEmail = memberService.selectMemberByEmail(email);
		Member memberTell = memberService.selectMemberByPhone(tell);
		
		if(memberEmail == null) {
			request.setAttribute("msg", "존재하지 않는 이메일 입니다."); 
			request.setAttribute("url", "/member/findId");
			request.getRequestDispatcher("/error/result").forward(request, response);
			return;
		} else if(memberTell == null) {
			request.setAttribute("msg", "존재하지 않는 휴대폰 번호 입니다."); 
			request.setAttribute("url", "/member/findId");
			request.getRequestDispatcher("/error/result").forward(request, response);
			return;
		} else if(memberEmail != null && memberTell != null && member == null) {
			request.setAttribute("msg", "존재하지 않는 이름 입니다."); 
			request.setAttribute("url", "/member/findId");
			request.getRequestDispatcher("/error/result").forward(request, response);
			return;
		} else if(member != null) {
			request.getSession().setAttribute("authentication", member);
			request.getRequestDispatcher("/member/checkId").forward(request, response);
			return;
		}
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		Member member = memberService.memberAuthenticate(userId, password);
		Member memberId = memberService.selectMemberById(userId);
		
		if(memberId == null) {
			request.setAttribute("msg", "존재하지 않는 아이디 입니다."); 
			request.setAttribute("url", "/member/loginPage");
			request.getRequestDispatcher("/error/result").forward(request, response);
			//return;
		} else if(memberId != null && member == null) {
			request.setAttribute("msg", "비밀번호가 일치하지 않습니다."); 
			request.setAttribute("url", "/member/loginPage");
			request.getRequestDispatcher("/error/result").forward(request, response);
			//return;
		}
		
		//로그인 성공시 authentication 라는 이름으로 세션에 사용자 정보 담아놓기
		if(member != null) {
			request.getSession().setAttribute("authentication", member);
			System.out.println("로그인 성공!!");
			System.out.println(member.getAddress());
			response.sendRedirect("/index");
		}
	}
	
	//카카오 로그인 구현
	private void kakaoLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String kakaoId = request.getParameter("kakaoId"); // 카카오 회원번호를 아이디로 넣기
		String kakaoNick = request.getParameter("kakaoNick");
		
		Member kakaoUser = memberService.selectMemberById(kakaoId); //아이디 존재하는지 체크

		Member kakaoMember = new Member();
		HttpSession session = request.getSession();
		
			if (kakaoUser == null) { //아이디가 존재하지 않는다면,
			 System.out.println("카카오 로그인 성공");
			
			 kakaoMember.setId(kakaoId);
			 kakaoMember.setNick(kakaoNick);
			 kakaoMember.setKakaoNum(1); //혹시 나중에 쓸 수도 있어서 테이블 하나 추가해서 넣어놓음

			 memberService.insertMember(kakaoMember);
					
			 session.setAttribute("authentication", kakaoMember);
					
			 request.setAttribute("msg", "나머지 정보를 입력하셔야 카카오 연동 회원가입이 완료됩니다. 회원정보 입력창으로 넘어갑니다."); 
			 request.setAttribute("url", "/member/newKakaoMember"); //나머지 정보 입력하도록 이동시킴
			 request.getRequestDispatcher("/error/result").forward(request, response);
			 return;
		} /*
			 * else if(kakaoUser != null && kakaoUser.getKakaoNum() == 1) {
			 * session.setAttribute("authentication", kakaoUser);
			 * 
			 * request.setAttribute("msg",
			 * "나머지 정보를 입력하지 않아 회원가입이 취소되었습니다. 회원가입을 다시 진행하여 주세요.");
			 * request.setAttribute("url", "/member/delete"); //나머지 정보 입력 안된 계정 db 삭제시킴
			 * request.getRequestDispatcher("/error/result").forward(request, response);
			 * return; }
			 */ else {
			kakaoMember = memberService.selectMemberById(kakaoId); //멤버정보 불러와서
			session.setAttribute("authentication", kakaoMember); //attribute에 넣기
			request.setAttribute("msg", "카카오 연동 로그인이 완료되었습니다."); 
			request.setAttribute("url", "/index");
			request.getRequestDispatcher("/error/result").forward(request, response);
		}

	}


	private void loginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/loginPage").forward(request, response);
		
	}

	private void basicJoin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Member member = new Member();
		HttpSession session = request.getSession();
		
		session.setAttribute("authentication", member);
		request.getRequestDispatcher("/member/joinForm").forward(request, response);
		
	}

	private void joinPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/joinPage").forward(request, response);
		
	}
	
	private void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원가입을 눌렀을 때 넘어오는 정보들 다 받아오기
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String nick = request.getParameter("nick");
		String phone = request.getParameter("phone");
		String postCode = request.getParameter("postCode");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");

		// 받아 온 정보들 member에 넣어주기
		Member member = new Member();
		member.setId(userId);
		member.setPassword(password);
		member.setName(name);
		member.setNick(nick);
		member.setPhone(phone);
		member.setAddress(postCode, address1, address2);
		member.setEmail(email);
		member.setGender(gender);
		member.setKakaoNum(6); //일반회원은 kakaoNum 6으로 고정
		
		memberService.insertMember(member);
		
		request.setAttribute("msg", "회원가입이 완료되었습니다."); 
		request.setAttribute("url", "/member/loginPage");
		request.getRequestDispatcher("/error/result").forward(request, response);
		
	}
	
	//가입취소 버튼 클릭 시 작동
	private void joinCancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("msg", "회원가입이 취소되었습니다. 메인페이지로 이동합니다.");
		request.setAttribute("url", "/index");
		request.getRequestDispatcher("/error/result").forward(request, response);
	}

	//회원가입 진행(db로 넘겨줌) join-auth-mail.jsp파일의 151번줄 <a>태그에 주소 넣어줬음 => 버튼 누르면 여기로 요청 날아오도록 하려고
	private void joinImpl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		Member member = (Member)session.getAttribute("persistUser");
		memberService.insertMember(member);
		
		//insert를 성공하면 우리가 발행한 토큰 만료시켜줘야 함(날려버리기)
		session.removeAttribute("persistToken");
		session.removeAttribute("persistUser");
		
		response.sendRedirect("/member/login-form");
		
	}

	private void checkId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		Member member = memberService.selectMemberById(userId);
		
		HttpSession session = request.getSession();
		Member kakaoId = (Member)session.getAttribute("authentication");
		
		if(member == null) {
			response.getWriter().print("available");
		} else if(kakaoId.getId().equals("undefined") && member.getId() == null) {
			response.getWriter().print("available");
		} else {
			response.getWriter().print("disable");
		}
	}
	
	private void checkNick(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nick = request.getParameter("nick");
		Member member = memberService.selectMemberByNick(nick);
		
		HttpSession session = request.getSession();
		Member userNick = (Member)session.getAttribute("authentication");
		int num = userNick.getKakaoNum();
		
		if(member == null) {
			response.getWriter().print("available");
		} else if(num == 6 && nick.equals(userNick.getNick())) { //일반회원이 닉네임 변경 원할 시
			response.getWriter().print("available");
		} else if(num == 2 && nick.equals(userNick.getNick())) { //카카오 회원이 닉네임 변경 원할 시
			response.getWriter().print("available");
		} else {
			response.getWriter().print("disable"); //이미 다른 사람 정보로 존재하는 경우
		}
	}
	
	private void checkPhone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phone = request.getParameter("phone");
		Member member = memberService.selectMemberByPhone(phone);
		
		HttpSession session = request.getSession();
		Member userPhone = (Member)session.getAttribute("authentication");
		int num = userPhone.getKakaoNum();
		
		if(member == null) {
			response.getWriter().print("available");
		} else if(num == 6 && phone.equals(userPhone.getPhone())){ //일반회원이 핸드폰 번호 변경 원할 시
			response.getWriter().print("available");
		} else if(num == 2 && phone.equals(userPhone.getPhone())) { //카카오 회원이 핸드폰 번호 변경 원할 시
			response.getWriter().print("available");
		} else {
			response.getWriter().print("disable"); //이미 다른 사람 정보로 존재하는 경우
		}
	}
	
	private void checkEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		Member member = memberService.selectMemberByEmail(email);
		
		HttpSession session = request.getSession();
		Member userEmail = (Member)session.getAttribute("authentication");
		int num = userEmail.getKakaoNum();
		
		if(member == null) {
			response.getWriter().print("available");
		} else if(num == 6 && email.equals(userEmail.getEmail())) { //일반회원이 이메일 변경 원할 시
			response.getWriter().print("available");
		} else if(num == 2 && email.equals(userEmail.getEmail())) { //카카오 회원이 이메일 변경 원할 시
			response.getWriter().print("available");
		} else {  
			response.getWriter().print("disable");  //이미 다른 사람 정보로 존재하는 경우
		}
		
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute("authentication");  //세션에 저장되어 있는 인증정보만 없애주기
		response.sendRedirect("/index");
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
