package com.kh.semi.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test/*")
public class jsp_test_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public jsp_test_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uriArr = request.getRequestURI().split("/");
		switch (uriArr[uriArr.length-1]) {
		case "join":
			request.getRequestDispatcher("/member/page-joinForm").forward(request, response);
			break;
		case "checkId":
			request.getRequestDispatcher("/member/checkId").forward(request, response);
			break;
		case "checkpassword":
			request.getRequestDispatcher("/member/checkpassword").forward(request, response);
			break;
		case "findId":
			request.getRequestDispatcher("/member/findId").forward(request, response);
			break;
		case "findPassword":
			request.getRequestDispatcher("/member/findPassword").forward(request, response);
			break;
		case "memberinfo":
			request.getRequestDispatcher("/member/page-memberInfo").forward(request, response);
			break;
		case "schedule":
			request.getRequestDispatcher("/schedule/schedule_main").forward(request, response);
			break;
		case "searchHos":
			request.getRequestDispatcher("/menu/searchHospital/searchHospital").forward(request, response);
			break;
		case "searchPharm":
			request.getRequestDispatcher("/menu/searchPharmacy/searchPharmacy").forward(request, response);
			break;
		case "searchMedi":
			request.getRequestDispatcher("/menu/searchMedi/searchMedi").forward(request, response);
			break;
		case "board":
			request.getRequestDispatcher("/menu/board/board").forward(request, response);
			break;
			
		default: response.setStatus(404);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
