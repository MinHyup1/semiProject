package com.kh.semi.hospitalInfo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.semi.common.exception.PageNotFoundException;
import com.kh.semi.hospitalInfo.model.dto.HospitalInfo;
import com.kh.semi.hospitalInfo.model.service.SearchHospitalService;

/**
 * Servlet implementation class HospitalSearchController
 */
@WebServlet("/searchHospital/*")
public class HospitalSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SearchHospitalService hospService = new SearchHospitalService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HospitalSearchController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] uriArr = request.getRequestURI().split("/");

		switch (uriArr[uriArr.length - 1]) {

		case "search-hospital":
			searchByHospitalName(request, response);
			break;

		case "search-hospital-main":
			request.getRequestDispatcher("/hospital/searchHospital").forward(request, response);
			break;
		default:
			throw new PageNotFoundException();
		}
	}

	private void searchByHospitalName(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String keyWord = request.getParameter("input"); // 병원검색어
		String hospCodeFromSchedule = request.getParameter("hospCode");

		if (request.getParameter("hospCode") == null) {
			hospCodeFromSchedule = "";
		}

		String[] treatCheckBox = null;
		List<String> hospCodeList = null;
		request.removeAttribute("msg");
		if (request.getParameterValues("treatCheckBox") == null) {
			treatCheckBox = new String[0];

		} else {
			treatCheckBox = request.getParameterValues("treatCheckBox");
			hospCodeList = hospService.searchByTreatCode(treatCheckBox);
		}

		if (keyWord == "" && treatCheckBox.length == 0 && hospCodeFromSchedule == "") {// 키워드와 진료코드 모두 공백일경우

			request.setAttribute("msg", "검색어를 입력해주세요.");

		} else if (keyWord == "" && treatCheckBox.length > 0 && hospCodeFromSchedule == "") {// 진료코드만 입력할경우

			request.setAttribute("msg", "검색어를 입력해주세요.");

		} else if (keyWord != "" && treatCheckBox.length == 0 && hospCodeFromSchedule == "") {// 키워드만 입력할경우
			List<HospitalInfo> hospList = hospService.searchByHospitalName(keyWord);

			request.setAttribute("hospList", hospList);
			request.setAttribute("siez", hospList.size());
			request.setAttribute("input", keyWord);

			if (hospList.size() == 0) {// 검색결과가 없을경우
				request.setAttribute("msg", "검색된 결과가 없습니다.");
			}

		} else if (keyWord != "" && hospCodeFromSchedule != "") {// 진료코드와 진료코드가 륜수씨스케줄에서 넘어올때

			List<HospitalInfo> hospList = hospService.searchByHospitalName(keyWord);

			for (int i = 0; i < hospList.size(); i++) {
				if (!hospCodeFromSchedule.equals(String.valueOf(hospList.get(i).getHospCode()))) {
					hospList.remove(i);
				}
			}
			request.setAttribute("hospList", hospList);
			request.setAttribute("siez", hospList.size());
			request.setAttribute("input", keyWord);

		} else if (keyWord != "" && treatCheckBox.length != 0 && hospCodeFromSchedule == "") {// 키워드랑 코드값넘어올때
			List<HospitalInfo> hospList = hospService.searchByKeywordAndTreatCode(keyWord, treatCheckBox);

			request.setAttribute("hospList", hospList);
			request.setAttribute("siez", hospList.size());
			request.setAttribute("input", keyWord);

			if (hospList.size() == 0) {// 검색결과가 없을경우
				request.setAttribute("msg", "검색된 결과가 없습니다.");
			}

		}

		request.getRequestDispatcher("/hospital/searchHospital").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
