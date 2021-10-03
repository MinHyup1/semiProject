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
import com.kh.semi.main.controller.CovidController;
import com.kh.semi.member.model.dto.Member;

public class AuthorizationFilter implements Filter {
	
	CovidController covidController = new CovidController();
	
	public AuthorizationFilter() {
		// TODO Auto-generated constructor stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String[] uriArr = httpRequest.getRequestURI().split("/");

		if (uriArr.length != 0) {
			switch (uriArr[1]) {
			
			case "member": memberAuthorize(httpRequest, httpResponse, uriArr); break;
			case "index": index(httpRequest, httpResponse, uriArr); break; 
			case "schedule":
				scheduleAuthorize(httpRequest, httpResponse, uriArr);
				break;
			case "board":
				// boardAuthorize(httpRequest, httpResponse, uriArr);
				break;
			default:
				break;
			}
		}
		chain.doFilter(request, response);
	}

	private void index(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) throws ServletException, IOException {
		      HttpSession session = httpRequest.getSession();
		      Member member = (Member) session.getAttribute("authentication");
		      covidController.covidChart(httpRequest, httpResponse);
		      if(member==null) {
		    	 
		         httpRequest.getRequestDispatcher("/index").forward(httpRequest, httpResponse);
		      }else if(member.getPhone()==null||member.getName()==null || member.getEmail()==null||member.getNick()==null) {
		          throw new HandlableException(ErrorCode.REDIRECT.setURL("/member/kakaoMemberForm"));
		      }
		      
	 }
	private void scheduleAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {

		HttpSession session = httpRequest.getSession();
		Member member = (Member) session.getAttribute("authentication");

		switch (uriArr[2]) {
		case "popup":
			if(member == null) {
				throw new HandlableException(ErrorCode.NOT_MEMBER_ERROR.setCLOSE("close"));
			}
			break;
		default:
			if(member == null) {
				throw new HandlableException(ErrorCode.NOT_MEMBER_ERROR);
			}
			break;
		}
	}

	private void memberAuthorize(HttpServletRequest httpRequest, HttpServletResponse httpResponse, String[] uriArr) {

		HttpSession session = httpRequest.getSession();

		switch (uriArr[2]) {
		case "changeForm":
			if (session.getAttribute("authentication") == null) {
				throw new HandlableException(ErrorCode.REDIRECT.setURL("/member/loginPage"));
			}
		case "mypage":
			if (session.getAttribute("authentication") == null) {
				throw new HandlableException(ErrorCode.REDIRECT.setURL("/member/loginPage"));
			}
		case "delete":
			if (session.getAttribute("authentication") == null) {
				throw new HandlableException(ErrorCode.REDIRECT.setURL("/member/loginPage"));
			}
		case "change":
			if (session.getAttribute("authentication") == null) {
				throw new HandlableException(ErrorCode.REDIRECT.setURL("/member/loginPage"));
			}
		case "kakaoChange":
			if (session.getAttribute("authentication") == null) {
				throw new HandlableException(ErrorCode.REDIRECT.setURL("/member/loginPage"));
			}
		case "kakaoMemberForm":
			if (session.getAttribute("authentication") == null) {
				throw new HandlableException(ErrorCode.REDIRECT.setURL("/member/loginPage"));
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
