package com.kh.semi.medicine.model.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;

import com.kh.semi.common.db.JDBCTemplate;
import com.kh.semi.medicine.model.dto.Medicine;

public class MedicineDao {
	
	private JDBCTemplate template = JDBCTemplate.getInstance(); //JDBC템플릿 인스턴스 생성

	public Medicine selectMedicineByName(String medName, Connection conn) {
		// 이름으로 약 정보 검색
		return null;
	}

	public Medicine getMedicineInfo(String medName, Connection conn) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void medicineAPI(String medName) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList?"); /*URL*/
        urlBuilder.append("&" + URLEncoder.encode("serviceKey","UTF-8") + "=" + "5oSqTcyHae2B8th6Dora7rZr6RgHBA9%2FFLdFH418ubQoO67vohoChDJ0BYGiyBGu8o3cRfxg39ZFGbaBHQor%2Fg%3D%3D"); /*공공데이터포털에서 받은 인증키*/
        urlBuilder.append("&" + URLEncoder.encode("itemName","UTF-8") + "=" + URLEncoder.encode(medName, "UTF-8")); /*제품명*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*응답데이터 형식(json)*/
        URL url = new URL(urlBuilder.toString());	        
        System.out.println(url);
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
        System.out.println(sb.toString()); //json으로 들어옴 이걸 맵으로 파싱해서 db에 넣을 예정
	}
}
