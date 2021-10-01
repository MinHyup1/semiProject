package com.kh.semi.pharmacy.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import com.kh.semi.pharmacy.model.dto.Pharmacy;
import com.kh.semi.pharmacy.model.service.PharmacyService;

/**
 * Servlet implementation class PharmecyController
 */
@WebServlet("/pharmacy/*")
public class PharmacyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PharmacyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] uriArr = request.getRequestURI().split("/");
		
		switch (uriArr[uriArr.length-1]) {
		case "pharmacy":
			request.getRequestDispatcher("/pharmacy/pharmacy").forward(request, response);
			break;
		case "update":
			pharmacyupdate(request,response);
			break;
		}
	}

	private void pharmacyupdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
					
			StringBuilder urlBuilder = new StringBuilder(
					"http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyFullDown"); /* URL */
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")+ "=5oSqTcyHae2B8th6Dora7rZr6RgHBA9%2FFLdFH418ubQoO67vohoChDJ0BYGiyBGu8o3cRfxg39ZFGbaBHQor%2Fg%3D%3D");
			urlBuilder.append("&" + URLEncoder.encode("ServiceKey", "UTF-8") + "="+ URLEncoder.encode("-", "UTF-8")); /* 공공데이터포털에서 받은 인증키 */
			urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /* 페이지번호 */
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1500", "UTF-8")); /* 한 페이지 결과 수 */
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

			List<Pharmacy> pharmacyList = null;
			JSONObject jObject = XML.toJSONObject(sb.toString());

			JSONObject jsonResponse = jObject.getJSONObject("response").getJSONObject("body").getJSONObject("items");
			JSONArray jArray = null;
			
			jArray = jsonResponse.getJSONArray("item");
			System.out.println(jArray.toString());

			

			for (int j = 0; j < jArray.length(); j++) {
				Pharmacy phar = null;
				pharmacyList = new ArrayList<Pharmacy>();
				JSONObject obj = jArray.getJSONObject(j);

				String pharName = "";
				String pharLoc = "";
				String pharLat = "";
				String pharLon = "";
				String pharCode = "";

				pharName = obj.getString("dutyName");
				pharLoc = obj.getString("dutyAddr");
				if(obj.has("wgs84Lat")) {
				pharLat = Double.toString(obj.getDouble("wgs84Lat"));
				}
				if(obj.has("wgs84Lon")) {
				pharLon = Double.toString(obj.getDouble("wgs84Lon"));
				}
				pharCode = obj.getString("hpid");

				phar = new Pharmacy();
				phar.setPharName(pharName);
				phar.setPharLoc(pharLoc);
				
				phar.setPharLat(pharLat);
				
				
				phar.setPharLon(pharLon);
				phar.setPharCode(pharCode);

				pharmacyList.add(phar);

				PharmacyService pharmacyService = new PharmacyService();

				try {
					if (pharmacyService.getPharmacyInfo(pharmacyList) > 0) {
						
					}
				} catch (Exception e) {
					
					e.printStackTrace();
				}

			}
			System.out.println("API에서 정보를 가져와  저장했습니다.");
			
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
