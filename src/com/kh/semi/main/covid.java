package com.kh.semi.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.kh.semi.common.exception.PageNotFoundException;

/**
 * Servlet implementation class ApiExplorer
 */
@WebServlet("/covid/*")
public class covid extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public covid() {
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
		case "covidInfo":
			covidInfo(request, response);
			break;
		default:
			throw new PageNotFoundException();
		}

	}

	private void covidInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LocalTime nowTime = LocalTime.now();
		LocalTime setTime = LocalTime.of(12, 00);  //api에서 당일 데이터가 보통 오전 10시쯤 나옴

		boolean res = nowTime.isBefore(setTime); //현재시간이 세팅시간보다 이전인지 확인		
		
		LocalDate nowDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		
		if(res == true) { //PM 12시 이전이면 날짜 하루 빼줌
			nowDate = LocalDate.now().minusDays(1);
		}
		
		String nowDateFormat = nowDate.format(formatter);
		String yesterdayFormat = nowDate.minusDays(7).format(formatter);
	

        StringBuilder urlBuilder = new StringBuilder("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=qt0%2BUr8fKiB4cFa0dxYrRkBZevm3bNeJx6NS9zc0jthKuFEFJan2kVokNKzCHQhrgb%2Bvj9Y7lxmfWreKIzMKSA%3D%3D"); /*Service Key*/
       // urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("-", "UTF-8")); /*공공데이터포털에서 받은 인증키*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("startCreateDt","UTF-8") + "=" + URLEncoder.encode(yesterdayFormat, "UTF-8")); /*검색할 생성일 범위의 시작*/
        urlBuilder.append("&" + URLEncoder.encode("endCreateDt","UTF-8") + "=" + URLEncoder.encode(nowDateFormat, "UTF-8")); /*검색할 생성일 범위의 종료*/
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
        //System.out.println(sb.toString());
		  
		//List<Integer> covidList = new ArrayList<Integer>(7);
		//List<String> covidDate = new ArrayList<String>(7);
		Map<String, Integer> covidData = new LinkedHashMap<String, Integer>();

		JSONObject jObject = XML.toJSONObject(sb.toString());
		JSONObject jsonResponse = jObject.getJSONObject("response").getJSONObject("body").getJSONObject("items");

		JSONArray jArray = null;

		jArray = jsonResponse.getJSONArray("item");
		System.out.println(jArray.toString());

		int todayCnt = 0;
		int yesterdayCnt = 0;
		int onedaytCnt = 0;
		String date ="";
		
		for (int i = 0; i < jArray.length(); i++) {
			if (i == 7) {
				break;
			}

			JSONObject obj = jArray.getJSONObject(i);
			JSONObject obj2 = jArray.getJSONObject(i + 1);

			date = obj2.getString("createDt"); //날짜   2021-09-25 10:49:15.225
			String[] str = date.split("\\s");
			//System.out.println(str[0]);
			
			todayCnt = obj.getInt("decideCnt"); // 최근확진자 수
			yesterdayCnt = obj2.getInt("decideCnt"); // 전 날 확진자 수
			onedaytCnt = todayCnt - yesterdayCnt;
			
			covidData.put(str[0], onedaytCnt);
			
			//covidList.add(i, onedaytCnt);
			//System.out.println(date);
		}

		//System.out.println(covidList.get(0));
		//System.out.println(covidList.toString());
		System.out.println(covidData);

		request.getRequestDispatcher("/covid").forward(request, response);

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