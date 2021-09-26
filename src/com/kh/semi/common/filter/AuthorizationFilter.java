package com.kh.semi.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.semi.common.code.ErrorCode;
import com.kh.semi.common.exception.HandlableException;
import com.kh.semi.member.model.dto.Member;

public class AuthorizationFilter implements Filter {

    public AuthorizationFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String[] uriArr = httpRequest.getRequestURI().split("/");
		
		if(uriArr.length != 0) {
			switch (uriArr[1]) {
			case "member":
				memberAuthorize(httpRequest, httpResponse, uriArr);
				break;
			case "schedule":
				scheduleAuthorize(httpRequest, httpResponse, uriArr);
				break;
			case "board":
				//boardAuthorize(httpRequest, httpResponse, uriArr);
				break;
			default:
				break;
			}
		}
		chain.doFilter(request, response);
	}

	
	/*
	 * private void boardAuthorize(HttpServletRequest httpRequest,
	 * HttpServletResponse httpResponse, String[] uriArr) {
	 * 
	 * HttpSession session = httpRequest.getSession();
	 * Member member = (Member) session.getAttribute("authentication");
	 * 
	 * switch (uriArr[2]) {
	 * case "board-form":
	 * if (member == null) {
	 * throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE_ERROR);
	 * }
	 * break;
	 * case "upload":
	 * if (member == null) {
	 * throw new HandlableException(ErrorCode.UNAUTHORIZED_PAGE_ERROR);
	 * }
	 * break;
	 * default:
	 * break;
	 * }
	 * }
	 */
	 

	private void scheduleAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {
		
		HttpSession session = httpRequest.getSession();
		Member member = (Member) session.getAttribute("authentication");
		
		if(member == null) {
			throw new HandlableException(ErrorCode.NOT_MEMBER_ERROR);
		}
		
	}

	
	  private void memberAuthorize(HttpServletRequest httpRequest,
	  HttpServletResponse httpResponse, String[] uriArr) {
	  
	  HttpSession session = httpRequest.getSession();
	  
	  switch (uriArr[2]) {
	  case "changeForm":
	  if(session.getAttribute("authentication") == null) {
	  throw new
	  HandlableException(ErrorCode.REDIRECT.setURL("/member/loginPage"));
	  }
	  case "delete":
	  if(session.getAttribute("authentication") == null) {
	  throw new
	  HandlableException(ErrorCode.REDIRECT.setURL("/member/loginPage"));
	  }
	  case "change":
	  if(session.getAttribute("authentication") == null) {
	  throw new
	  HandlableException(ErrorCode.REDIRECT.setURL("/member/loginPage"));
	  }
	  case "id-check":
	  if(session.getAttribute("authentication") == null) {
	  throw new
	  HandlableException(ErrorCode.REDIRECT.setURL("/member/login-form"));
	  }
	  break;
	  case "nickName-check":
	  if(session.getAttribute("authentication") == null) {
	  throw new
	  HandlableException(ErrorCode.REDIRECT.setURL("/member/loginPage"));
	  }
	  case "phone-check":
	  if(session.getAttribute("authentication") == null) {
	  throw new
	  HandlableException(ErrorCode.REDIRECT.setURL("/member/loginPage"));
	  }
	  case "email-check":
	  if(session.getAttribute("authentication") == null) {
	  throw new
	  HandlableException(ErrorCode.REDIRECT.setURL("/member/loginPage"));
	  }
	  case "kakaoChange":
	  if(session.getAttribute("authentication") == null) {
	  throw new
	  HandlableException(ErrorCode.REDIRECT.setURL("/member/loginPage"));
	  }
	  case "kakaoMemberForm":
	  if(session.getAttribute("authentication") == null) {
	  throw new
	  HandlableException(ErrorCode.REDIRECT.setURL("/member/loginPage"));
	  }
	  break;
	  default:
	  break;
	  }
	  
	  }
	 

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
