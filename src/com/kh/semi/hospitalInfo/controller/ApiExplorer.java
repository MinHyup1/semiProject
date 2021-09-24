package com.kh.semi.hospitalInfo.controller;

/* Java 샘플 코드 */


import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class ApiExplorer {
    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B551182/hospInfoService1/getHospBasisList1"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=WbW7KMwW0GkyneJEApEQUjXNL%2BBLWh1iAnVATl%2FUWX5YemHkvr0a6PRm4UNv5mm%2Fsj2vVgHPgFqmTkqkeS5hng%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode("-", "UTF-8")); /*서비스키*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("sidoCd","UTF-8") + "=" + URLEncoder.encode("110000", "UTF-8")); /*시도코드*/
        urlBuilder.append("&" + URLEncoder.encode("sgguCd","UTF-8") + "=" + URLEncoder.encode("110019", "UTF-8")); /*시군구코드*/
        urlBuilder.append("&" + URLEncoder.encode("emdongNm","UTF-8") + "=" + URLEncoder.encode("신내동", "UTF-8")); /*읍면동명*/
        urlBuilder.append("&" + URLEncoder.encode("yadmNm","UTF-8") + "=" + URLEncoder.encode("서울의료원", "UTF-8")); /*병원명(UTF-8 인코딩 필요)*/
        urlBuilder.append("&" + URLEncoder.encode("zipCd","UTF-8") + "=" + URLEncoder.encode("2010", "UTF-8")); /*분류코드(활용가이드 참조)*/
        urlBuilder.append("&" + URLEncoder.encode("clCd","UTF-8") + "=" + URLEncoder.encode("11", "UTF-8")); /*종별코드(활용가이드 참조)*/
        urlBuilder.append("&" + URLEncoder.encode("dgsbjtCd","UTF-8") + "=" + URLEncoder.encode("01", "UTF-8")); /*진료과목코드(활용가이드 참조)*/
        urlBuilder.append("&" + URLEncoder.encode("xPos","UTF-8") + "=" + URLEncoder.encode("127.09854004628151", "UTF-8")); /*x좌표(소수점 15)*/
        urlBuilder.append("&" + URLEncoder.encode("yPos","UTF-8") + "=" + URLEncoder.encode("37.6132113197367", "UTF-8")); /*y좌표(소수점 15)*/
        urlBuilder.append("&" + URLEncoder.encode("radius","UTF-8") + "=" + URLEncoder.encode("3000", "UTF-8")); /*단위 : 미터(m)*/
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
        System.out.println(sb.toString());
    }
}
