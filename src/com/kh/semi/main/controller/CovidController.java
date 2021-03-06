package com.kh.semi.main.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;

import com.kh.semi.common.exception.PageNotFoundException;
import com.kh.semi.main.model.dto.Covid;
import com.kh.semi.main.model.service.CovidService;

/**
 * Servlet implementation class ApiExplorer
 */
@WebServlet("/covid/*")
public class CovidController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CovidService covidService = new CovidService();
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CovidController() {
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
		case "covidChart":
			covidChart(request);
			break;
		case "covid":
			request.getRequestDispatcher("/main/covid").forward(request, response);
		default:
			throw new PageNotFoundException();
		}

	}

	public HttpServletRequest covidChart(HttpServletRequest request) throws ServletException, IOException {
		JSONArray covidJson = new JSONArray();
		covidJson = covidService.covidDecideCnt();
		System.out.println(covidJson);
		
		request.getSession().setAttribute("covidJson", covidJson);
		//request.getRequestDispatcher("/index").forward(request, response);
		return request;
	}

	private void covidInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		LocalTime nowTime = LocalTime.now(); //?????? ?????? ????????????
		LocalTime setTime = LocalTime.of(12, 00);  //api?????? ?????? ???????????? ?????? ?????? 10?????? ???????????? ??????12?????? ?????????????????? ????????????

		boolean res = nowTime.isBefore(setTime); //??????????????? ?????????????????? ???????????? ??????		
		
		LocalDate nowDate = LocalDate.now(); //?????? ?????? ????????????
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		
		if(res == true) { //?????? 12??? ???????????? ?????? ?????? ??????
			nowDate = LocalDate.now().minusDays(1);
		}
		
		String nowDateFormat = nowDate.format(formatter);
		String yesterdayFormat = nowDate.minusDays(30).format(formatter);
	

        StringBuilder urlBuilder = new StringBuilder("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=qt0%2BUr8fKiB4cFa0dxYrRkBZevm3bNeJx6NS9zc0jthKuFEFJan2kVokNKzCHQhrgb%2Bvj9Y7lxmfWreKIzMKSA%3D%3D"); /*Service Key*/
       // urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("-", "UTF-8")); /*??????????????????????????? ?????? ?????????*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*???????????????*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*??? ????????? ?????? ???*/
        urlBuilder.append("&" + URLEncoder.encode("startCreateDt","UTF-8") + "=" + URLEncoder.encode(yesterdayFormat, "UTF-8")); /*????????? ????????? ????????? ??????*/
        urlBuilder.append("&" + URLEncoder.encode("endCreateDt","UTF-8") + "=" + URLEncoder.encode(nowDateFormat, "UTF-8")); /*????????? ????????? ????????? ??????*/
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

		Covid covidInfo = new Covid();
		
		JSONObject jObject = XML.toJSONObject(sb.toString());
		JSONObject jsonResponse = jObject.getJSONObject("response").getJSONObject("body").getJSONObject("items");

		JSONArray jArray = null;

		jArray = jsonResponse.getJSONArray("item");
		System.out.println(jArray.toString());

		int todayCnt = 0;
		int yesterdayCnt = 0;
		int onedaytCnt = 0;
		String date ="";
		
		for (int i = 29, j = 0; i < jArray.length(); i--,j++) {
			if (i == -1) {
				break;
			}

			JSONObject obj = jArray.getJSONObject(i);
			JSONObject obj2 = jArray.getJSONObject(i + 1);

			date = obj2.getString("createDt"); //ex) 2021-09-25 10:49:15.225
			String str = date.substring(0, 10);
	
			String[] arrayStr = str.split("-");
			
			todayCnt = obj.getInt("decideCnt"); // ?????? ????????? ???
			yesterdayCnt = obj2.getInt("decideCnt"); // ??? ??? ????????? ???
			onedaytCnt = todayCnt - yesterdayCnt; //?????? ????????? ???
			
			covidInfo.setNum(j);
			covidInfo.setDays(str);
			covidInfo.setDecideCnt(onedaytCnt);
			System.out.println(covidInfo);
			

			try {
				covidService.updateCovidInfo(covidInfo);
	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		request.setAttribute("url", "/covid/covidChart");
		request.getRequestDispatcher("/error/result").forward(request, response);



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
