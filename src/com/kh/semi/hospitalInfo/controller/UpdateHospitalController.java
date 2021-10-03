package com.kh.semi.hospitalInfo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.kh.semi.common.code.ErrorCode;
import com.kh.semi.common.exception.HandlableException;
import com.kh.semi.common.exception.PageNotFoundException;
import com.kh.semi.hospitalInfo.model.dto.HospitalInfo;
import com.kh.semi.hospitalInfo.model.dto.SearchTreat;
import com.kh.semi.hospitalInfo.model.service.UpdateHospitalService;

/**
 * Servlet implementation class HospitalController
 */
@WebServlet("/UpdateHospitalController/*")
public class UpdateHospitalController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UpdateHospitalService hospService = new UpdateHospitalService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateHospitalController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

		String[] uriArr = request.getRequestURI().split("/");

		switch (uriArr[uriArr.length - 1]) {
		case "update-info":
			updateInfo(request, response);
			break;
		case "searchByHospitalNameInPopup":
			searchByHospitalNameInPopup(request, response);
			break;
		case "update-treatInfo":
			updateTreatInfo(request, response);
			break;
		
			

		default:
			throw new PageNotFoundException();
		}
	}

	private void updateTreatInfo(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		for(int i = 0; i < 89; i++) {
			boolean flg = true;
			int pageNum = 1;
			
			String treatCodeNum = String.valueOf(i);
			
			if(i < 10) {
				treatCodeNum = "0" + treatCodeNum;
			}
		
		while(flg) {
			
			StringBuilder urlBuilder = new StringBuilder(
					"http://apis.data.go.kr/B551182/hospInfoService1/getHospBasisList1"); /* URL */
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
					+ "=WbW7KMwW0GkyneJEApEQUjXNL%2BBLWh1iAnVATl%2FUWX5YemHkvr0a6PRm4UNv5mm%2Fsj2vVgHPgFqmTkqkeS5hng%3D%3D"); /*
																																 */
			urlBuilder.append(
					"&" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + URLEncoder.encode("-", "UTF-8")); /* 서비스키 */
			urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "="
					+ URLEncoder.encode(String.valueOf(pageNum), "UTF-8")); /* 페이지번호 */
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
					+ URLEncoder.encode("800", "UTF-8")); /* 한 페이지 결과 수 */
			urlBuilder.append("&" + URLEncoder.encode("dgsbjtCd","UTF-8") + "=" 
					+ URLEncoder.encode(treatCodeNum, "UTF-8")); /*진료과목코드(활용가이드 참조)*/
			urlBuilder.append(
					"&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /* JSON 파싱 */

			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd;
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			conn.disconnect();

			// 가장 큰 JSONObject를 가져옵니다.
			try {
				JSONObject jObject = new JSONObject(sb.toString());
				int totalCnt = jObject.getJSONObject("response").getJSONObject("body")
				.getInt("totalCount");
				
				if( totalCnt == 0 || Math.floor(totalCnt/800) + 2 <= pageNum ) {//결과가 존재하지 않을시
					System.out.println("결과가 없습니다.");
					flg = false;
					continue;
				}
				
				JSONObject jsonResponse = jObject.getJSONObject("response").getJSONObject("body")
						.getJSONObject("items");
				JSONArray jArray = jsonResponse.getJSONArray("item");

				
				List<SearchTreat> treatList = new ArrayList<SearchTreat>();

				// 존재하지 않으면 멈춤 9/24
				for (int j = 0; j < jArray.length(); j++) {

					JSONObject obj = jArray.getJSONObject(j);
					
					SearchTreat treat = new SearchTreat();
					
					String uniqeCode = "";
					
					if(obj.has("ykiho")) {
						uniqeCode = String.valueOf(obj.get("ykiho"));
					}
					
					int hospCode = hospService.bringHospCode(uniqeCode);
					
					if(hospCode == 0) {//uniqeCode에 맞는 hospCode가 없을경우
						continue;
					}
					
					
					treat.setHospCode(hospCode);
					treat.setTreatCode(treatCodeNum);
					
					treatList.add(treat);

				}
				
				if (hospService.updateSearchTreat(treatList) >= 1) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date time = new Date();
					String time1 = format.format(time);

					System.out.println("[" + time1 + "]" + pageNum +" 페이지   " + treatCodeNum + " 진료과목코드 업데이트 완료.");
				}
				
				pageNum++;

			} catch (JSONException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			

		}
		if(i == 88) {
			System.out.println("==========업데이트 완료==========");
		}
	}
		
	}

	
	
	private void searchByHospitalNameInPopup(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String keyWord = request.getParameter("input");
		
		if(keyWord == null || keyWord == "") {//공백을 입력할경우
			//response.sendRedirect("/test/searchHos");
		}else {
			List<HospitalInfo> hospList = hospService.searchByHospitalName(keyWord);
			request.getSession().setAttribute("hospList", hospList);
			request.getSession().setAttribute("siez", hospList.size());
			
			if(hospList == null) {//검색결과가 없을경우
				request.setAttribute("res", "null");
			}
			
			//request.getRequestDispatcher("/schedule/popup/hospital-popup").forward(request, response);
			response.sendRedirect("/schedule/popup/hospital-popup");
		}
	}

	private void updateInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, JSONException {
		
		boolean flg = true;
		int pageNum = 1;
		
		while(flg) {
			
			StringBuilder urlBuilder = new StringBuilder(
					"http://apis.data.go.kr/B551182/hospInfoService1/getHospBasisList1"); /* URL */
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
					+ "=WbW7KMwW0GkyneJEApEQUjXNL%2BBLWh1iAnVATl%2FUWX5YemHkvr0a6PRm4UNv5mm%2Fsj2vVgHPgFqmTkqkeS5hng%3D%3D"); /*
																																 */
			urlBuilder.append(
					"&" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + URLEncoder.encode("-", "UTF-8")); /* 서비스키 */
			urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "="
					+ URLEncoder.encode(String.valueOf(pageNum), "UTF-8")); /* 페이지번호 */
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "="
					+ URLEncoder.encode("800", "UTF-8")); /* 한 페이지 결과 수 */
			
			urlBuilder.append(
					"&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /* JSON 파싱 */

			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd;
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			conn.disconnect();

			// 가장 큰 JSONObject를 가져옵니다.
			try {
				JSONObject jObject = new JSONObject(sb.toString());
				int totalCnt = jObject.getJSONObject("response").getJSONObject("body")
				.getInt("totalCount");
				
				if( totalCnt == 0 || Math.floor(totalCnt/800) + 2 <= pageNum ) {//결과가 존재하지 않을시
					System.out.println("결과가 없습니다.");
					flg = false;
					continue;
				}
				
				JSONObject jsonResponse = jObject.getJSONObject("response").getJSONObject("body")
						.getJSONObject("items");
				JSONArray jArray = jsonResponse.getJSONArray("item");

				HospitalInfo hosp = null;
				List<HospitalInfo> hospList = new ArrayList<HospitalInfo>();

				// 존재하지 않으면 멈춤 9/24
				for (int j = 0; j < jArray.length(); j++) {

					JSONObject obj = jArray.getJSONObject(j);
					
					String hospAddr = "-";
					String hospTell = "-";
					String hospUrl = "-";
					String hospName = "-";
					String xPos = "-";
					String yPos = "-";
					String uniqeCode = "";
					
					if(obj.has("addr")) {
						hospAddr = obj.getString("addr");
					}
					if(obj.has("telno")) {
						hospTell = obj.getString("telno");
					}
					if(obj.has("hospUrl")) {
						hospUrl = String.valueOf(obj.get("hospUrl"));
					}
					if(obj.has("yadmNm")) {
						hospName = obj.getString("yadmNm");
					}
					if(obj.has("XPos")) {
						xPos = Double.toString(obj.getDouble("XPos"));
					}
					if(obj.has("YPos")) {
						yPos = Double.toString(obj.getDouble("YPos"));
					}
					if(obj.has("ykiho")) {
						uniqeCode = String.valueOf(obj.get("ykiho"));
					}

					
					hosp = new HospitalInfo();
					
					hosp.setHospTell(hospTell);
					hosp.setHospName(hospName);
					hosp.setHospUrl(hospUrl);
					hosp.setAddress(hospAddr);
					hosp.setxPos(xPos);
					hosp.setyPos(yPos);
					hosp.setUniqeCode(uniqeCode);
					
					hospList.add(hosp);
				}
				int updateCount = hospService.updateHospInfo(hospList);
				if (updateCount >= 1) {
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date time = new Date();
					String time1 = format.format(time);

					System.out.println("[" + time1 + "]" + pageNum +" 페이지 병원정보 업데이트 완료." 
					+ updateCount + "개 업데이트 완료");
				}else {
					System.out.println( pageNum + "업데이트할 병원정보가 없습니다.");
				}
				
				pageNum++;

			} catch (JSONException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String time1 = format.format(time);
		String updateDate = "마지막 업데이트 :" + time1  ;
		
		request.setAttribute("update", updateDate);
		
		request.getRequestDispatcher("/hospital/searchHospital").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
