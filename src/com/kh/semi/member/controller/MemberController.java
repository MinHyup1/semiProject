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
    //브라우저가 요청을 한다 -> 브라우저로 응답이 날아간다 -> 그 안에 있는 서블릿이 하는 일 -> 요청이 들어옴 -> 필터를 타고, 필터에서 인코딩을 하고, 필터에서 리퀘스트래퍼가 돌고,
    //컨트롤러로 넘어가고, 컨트롤러에서 처리가 되고 -> 다오에서 예외 발생(예외가 없으면 뷰로 가서 화면출력) -> throws가 되니까 컨트롤러 바깥으로 넘어가고 -> 서블릿컨테이너가 해당 예외를 캐치해서 
    //우리의 이셉션핸들러를 부름 -> 보여줄 뷰를 결정해줌 -> 화면에 나타남
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uriArr = request.getRequestURI().split("/");
		
		switch (uriArr[uriArr.length-1]) {
		case "login" : //세미 사용 코드
			login(request,response);
			break;
		case "logout" :
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
		default: throw new PageNotFoundException();  //우리가 만든 예외처리 클래스 넣어주기
		
		}
	}
	

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		
		Member member = memberService.memberAuthenticate(userId, password);
		
		if(member == null) {
			response.sendRedirect("/member/loginPage?err=1");
			return;
		}
		
		//로그인 성공시 authentication 라는 이름으로 세션에 사용자 정보 담아놓기
		if(member != null) {
			request.getSession().setAttribute("authentication", member);
			System.out.println("로그인 성공!!");
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
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String gender = request.getParameter("gender");

		// 받아 온 정보들 member에 넣어주기
		Member member = new Member();
		member.setId(userId);
		member.setPassword(password);
		member.setName(name);
		member.setNick(nick);
		member.setPhone(phone);
		member.setAddress(address);
		member.setEmail(email);
		member.setGender(gender);
		
		//request.getSession().setAttribute("persistUser", member);
		memberService.insertMember(member);
		
		response.sendRedirect("/member/loginPage");

		//밑에 주석코드 동작 안함 ㅠㅠ   (Uncaught SyntaxError: Unexpected token '<') 에러
//		request.setAttribute("msg", "회원가입이 완료되었습니다."); 
//		request.setAttribute("url", "/member/loginPage");
//		request.getRequestDispatcher("/error/result").forward(request, response);
		System.out.println("회원가입 완료!");

		
		
		/*
		 * //메일을 보내기 전에 난수를 하나 생성해주기 String persistToken = UUID.randomUUID().toString();
		 * request.getSession().setAttribute("persistUser", member);
		 * request.getSession().setAttribute("persistToken", persistToken);
		 * 
		 * //메일 보낼 때 멤버 정보와 랜덤으로 생성한 난수를 같이 보내주기
		 * memberService.authenticateByEmail(member, persistToken); //회원가입 폼에서 우리가 입력한
		 * 정보를 가져와서 넣어주고 메일 전송하는 메서드를 서비스에서 생성하도록
		 * 
		 * //안내메세지 전송되도록 하고, index로 돌려보내기 
		 * request.setAttribute("msg", "이메일이 발송되었습니다.");
		 * request.setAttribute("url", "/index");
		 * request.getRequestDispatcher("/error/result").forward(request, response);
		 */
		
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
			//우리가 페치랑 통신을 하고있기 때문에 리다이렉트나 포워드를 사용하지 않음
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
		//세션 : 사용자 인증정보가 담기는 공간 X
		//		session scope를 가지는 저장공간 이다.
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
