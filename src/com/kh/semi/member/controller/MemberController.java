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
		case "login" : //세미 사용 코드
			login(request,response);
			break;
		case "logout" ://세미 사용 코드
			logout(request,response);
			break;
		case "loginPage" : //세미 사용 코드
			loginPage(request,response);
			break;
		case "joinPage": //세미 사용 코드
			joinPage(request,response);
			break;
		case "basicJoin": //세미 사용 코드
			basicJoin(request,response);
			break;
		case "join" :   //join-form.jsp에서 가입버튼 누르면 url을 member/join으로 호출하니까 여기로 넘어옴 //세미 사용 코드
			join(request,response);
			break;
		case "joinCancel" : //세미 사용 코드(가입취소)
			joinCancel(request,response);
			break;
		case "id-check" : //joinForm.js에서 id-check로 보내줌 //세미 사용 코드
			checkId(request,response);
			break;
		case "nickName-check" : //joinForm.js에서 nick-check로 보내줌 //세미 사용 코드
			checkNick(request,response);
			break;
		case "phone-check" : //joinForm.js에서 phone-check로 보내줌 //세미 사용 코드
			checkPhone(request,response);
			break;
		case "email-check" : //joinForm.js에서 email-check로 보내줌 //세미 사용 코드
			checkEmail(request,response);
			break;
		case "join-impl" : 
			joinImpl(request,response);
			break;
		case "mypage" : 
			mypage(request,response);
			break;		
		case "findId" : 
			findId(request,response);
			break;
		case "findId-info" : 
			findIdInfo(request,response);
			break;
		case "findPassword" : 
			findPassword(request,response);
			break;
		case "findPassword-info" : 
			findPasswordInfo(request,response);
			break;
		case "memberInfo" : 
			memberInfo(request,response);
			break;
		case "delete" : 
			delete(request,response);
			break;		
		case "changeForm" : 
			changeForm(request,response);
			break;
		case "change":
			change(request,response);
			break;
		default: throw new PageNotFoundException();  //우리가 만든 예외처리 클래스 넣어주기
		
		}
	}
	

	private void change(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {

		Member member = new Member();
		member = (Member) request.getSession().getAttribute("authentication");
		String userId = member.getId();
		String password = request.getParameter("password");
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
		member.setNick(nick);
		member.setPhone(phone);
		member.setAddress(postCode, address1, address2);
		member.setEmail(email);
		member.setGender(gender);
		
		//request.getSession().setAttribute("persistUser", member);
		memberService.UpdateMember(member);
		
		//response.sendRedirect("/member/loginPage");

		request.setAttribute("msg", "회원 수정이 완료되었습니다."); 
		request.setAttribute("url", "/member/loginPage");
		System.out.println("회원수정 완료!");
		request.getRequestDispatcher("/error/result").forward(request, response);
			
	}

	private void changeForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/member/memberInfo").forward(request, response);
		}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String userId =((Member) request.getSession().getAttribute("authentication")).getId();//멤버객체에 authentication를 집어넣는다
		//String userId = member.getId();
		System.out.println("맴버아이디 제발 : "+userId);//출력 후 확인
		memberService.deleteMember(userId);//삭제 진행
		logout(request, response);//로그아웃 사용해 세션 끊고 인덱스로 이동
	}


	private void memberInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/member/memberInfo").forward(request, response);
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

	/* /member/memberInfo */

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
			return;
		} else if(memberId != null && member == null) {
			request.setAttribute("msg", "비밀번호가 일치하지 않습니다."); 
			request.setAttribute("url", "/member/loginPage");
			request.getRequestDispatcher("/error/result").forward(request, response);
			return;
		}
		
		//로그인 성공시 authentication 라는 이름으로 세션에 사용자 정보 담아놓기
		if(member != null) {
			request.getSession().setAttribute("authentication", member);
			System.out.println("로그인 성공!!");
			System.out.println(member.getAddress());
			response.sendRedirect("/index");
		}
	}

	private void loginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/loginPage").forward(request, response);
		
	}

	private void basicJoin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/joinForm").forward(request, response);
		
	}

	private void joinPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/joinPage").forward(request, response);
		
	}

	private void mypage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/mypage").forward(request, response);
		
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
		
		memberService.insertMember(member);
		
		request.setAttribute("msg", "회원가입이 완료되었습니다."); 
		request.setAttribute("url", "/member/loginPage");
		//request.getRequestDispatcher("/index").forward(request, response);
		System.out.println("회원가입 완료!");

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
		
		if(member == null) {
			response.getWriter().print("available");
		}else {
			response.getWriter().print("disable");
		}
	}
	
	private void checkNick(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nick = request.getParameter("nick");
		Member member = memberService.selectMemberByNick(nick);
	
		if(member == null) {
			response.getWriter().print("available");
		}else {
			response.getWriter().print("disable");
		}
	}
	
	private void checkPhone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String phone = request.getParameter("phone");
		Member member = memberService.selectMemberByPhone(phone);
		
		if(member == null) {
			response.getWriter().print("available");
		}else {
			response.getWriter().print("disable");
		}
	}
	
	private void checkEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		Member member = memberService.selectMemberByEmail(email);
		
		if(member == null) {
			response.getWriter().print("available");
		}else {
			response.getWriter().print("disable");
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
