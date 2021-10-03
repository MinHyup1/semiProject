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
			request.getRequestDispatcher("/hospital/searchHospital").forward(request, response);
			break;
		case "searchPharm":
			request.getRequestDispatcher("/pharmacy/pharmacy").forward(request, response);
			break;
		case "searchMedi":
			request.getRequestDispatcher("/medicine/medicine").forward(request, response);
			break;
		case "board":
			request.getRequestDispatcher("/board/board").forward(request, response);
			break;
		case "write_board":
			request.getRequestDispatcher("/board/write_board").forward(request, response);
			break;
		case "view_board":
			request.getRequestDispatcher("/board/view_board").forward(request, response);
			break;
		case "add_board_comment":
			//request.getRequestDispatcher("/board/view_board").forward(request, response);
			break;
		case "delete_board_comment":
			//request.getRequestDispatcher("/board/board").forward(request, response);
			break;
		case "delete_board":
			//request.getRequestDispatcher("/board/board").forward(request, response);
			break;
		case "update_board":
			//request.getRequestDispatcher("/board/board").forward(request, response);
			break;
		case "loginPage":
			request.getRequestDispatcher("/member/loginPage").forward(request, response);
			break;
		case "joinPage":
			request.getRequestDispatcher("/member/joinPage").forward(request, response);
			break;
			
		default: response.setStatus(404);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
