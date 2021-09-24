package com.kh.semi.hospitalInfo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
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
import org.json.JSONObject;

import com.kh.semi.common.exception.PageNotFoundException;
import com.kh.semi.hospitalInfo.model.dto.HospitalInfo;
import com.kh.semi.hospitalInfo.model.service.HospitalService;

/**
 * Servlet implementation class HospitalController
 */
@WebServlet("/HospitalController/*")
public class HospitalController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	HospitalService hospService = new HospitalService();
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HospitalController() {
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
		case "update-info":
			updateInfo(request, response);
			break;
		case "search":
			searchHospital(request, response);
			break;
		

		default:
			throw new PageNotFoundException();
		}
	}

	private void searchHospital(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	private void updateInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B551182/hospInfoService1/getHospBasisList1"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=WbW7KMwW0GkyneJEApEQUjXNL%2BBLWh1iAnVATl%2FUWX5YemHkvr0a6PRm4UNv5mm%2Fsj2vVgHPgFqmTkqkeS5hng%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("-", "UTF-8")); /*서비스키*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("_type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*JSON 파싱*/
        
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
        JSONObject jObject = new JSONObject(sb.toString());
        
        JSONObject jsonResponse = jObject.getJSONObject("response").getJSONObject("body").getJSONObject("items");
        JSONArray jArray = jsonResponse.getJSONArray("item");
        
        HospitalInfo hosp = null;
        List<HospitalInfo> hospList = new ArrayList<HospitalInfo>();
        
        for (int i = 0; i < jArray.length(); i++) {
            JSONObject obj = jArray.getJSONObject(i);
            String hospAddr = obj.getString("addr");
            String clCd = obj.getString("clCd");
            String hospTell = obj.getString("telno");
            String hospUrl = obj.getString("hospUrl");
            String hospName = obj.getString("yadmNm");
            String xPos = Double.toString(obj.getDouble("XPos"));
            String yPos = Double.toString(obj.getDouble("YPos"));
            
            hosp = new HospitalInfo();
        	hosp.setHospTreat(clCd);
        	hosp.setHospTell(hospTell);
        	hosp.setHospName(hospName);
        	hosp.setHospUrl(hospUrl);
        	hosp.setAddress(hospAddr);
        	hosp.setxPos(xPos);
        	hosp.setyPos(yPos);
        	
        	hospList.add(hosp);
            
        }
        	try {
				if(hospService.updateHospInfo(hospList) >= 1) {
					SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
					Date time = new Date();
					String time1 = format.format(time);
					
					System.out.println(time1 + "  병원정보 업데이트 완료.");
				}
			} catch (Exception e) {
				// 업데이트 된 행이 없습니다.
				e.printStackTrace();
			}
        
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
