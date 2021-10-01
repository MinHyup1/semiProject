package com.kh.semi.schedule.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.common.exception.PageNotFoundException;

@WebServlet("/schedule/popup/*")
public class PopupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public PopupController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.addHeader("Content-Type", "text/html; charset=utf-8");
		String[] uriArr = request.getRequestURI().split("/");
		
		switch (uriArr[3]) {
		case "hospital-popup":
			hospitalPopup(request, response);
			break;
		case "pharm-popup":
			pharmPopup(request, response);
			break;
		case "medicine-popup":
			medicinePopup(request, response);
			break;
		default: throw new PageNotFoundException();
		}
		
	}

	

	private void medicinePopup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/schedule/popup/search-medicine").forward(request, response);
	}
	
	private void pharmPopup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/schedule/popup/search-pharm").forward(request, response);
	}

	private void hospitalPopup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/schedule/popup/search-hospital").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
