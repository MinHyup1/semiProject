package com.kh.semi.medicine.controller;

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
import org.json.JSONException;
import org.json.JSONObject;

import com.kh.semi.common.code.ErrorCode;
import com.kh.semi.common.exception.PageNotFoundException;
import com.kh.semi.medicine.model.dto.Medicine;
import com.kh.semi.medicine.model.service.MedicineService;
import com.kh.semi.common.exception.HandlableException;


@WebServlet("/Medicine/*")
public class MedicineController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private MedicineService medicineService = new MedicineService();
    public MedicineController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String[] uriArr = request.getRequestURI().split("/");
		
		switch (uriArr[uriArr.length-1]) {
		case "medicineMain":
			request.getRequestDispatcher("/medicine/medicine").forward(request, response);
			break;
		case "medicineInfo":
			medicineInfo(request,response);
			break;

		default:throw new PageNotFoundException();
		}
	}

	private void medicineInfo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		// 검색하고 싶은 약품 이름 받아오기
		String medName = request.getParameter("medName");
		List<Medicine> medicineList = null;
		medicineList = medicineService.selectMedicineByName(medName); //DB 에 있는지 확인 

		if(medicineList.isEmpty()) { //DB에 없을경우 API에 접속해서 확인후  DB에 저장
			medicineList = medicineAPI(medName);//DB저장 따로 ,API에서 바로 출력	
		}
					
		if(medicineList != null) {
		request.setAttribute("medicineList", medicineList);
		request.setAttribute("size", medicineList.size());
		}
		
		request.getRequestDispatcher("/medicine/medicine").forward(request, response);		
	}
	
	public List<Medicine> medicineAPI(String medName) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1471000/DrbEasyDrugInfoService/getDrbEasyDrugList?"); /*URL*/
        urlBuilder.append("&" + URLEncoder.encode("serviceKey","UTF-8") + "=" + "5oSqTcyHae2B8th6Dora7rZr6RgHBA9%2FFLdFH418ubQoO67vohoChDJ0BYGiyBGu8o3cRfxg39ZFGbaBHQor%2Fg%3D%3D"); /*공공데이터포털에서 받은 인증키*/
        urlBuilder.append("&" + URLEncoder.encode("itemName","UTF-8") + "=" + URLEncoder.encode(medName, "UTF-8")); /*제품명*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*응답데이터 형식(json)*/
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
        List<Medicine> medicineList = null;
		try {
			JSONObject jObject = new JSONObject(sb.toString());

			JSONObject jsonResponse = jObject.getJSONObject("body");

			JSONArray jArray = null;

			if (!jsonResponse.has("items")) {
				return medicineList;
				//throw new HandlableException(ErrorCode.API_DATA_ERROR);
				
			}

			jArray = jsonResponse.getJSONArray("items");

			Medicine med = null;
			medicineList = new ArrayList<Medicine>();

			
			for (int j = 0; j < jArray.length(); j++) {

				JSONObject obj = jArray.getJSONObject(j);
				
				int medNum = 0;
				String medGetName = " ";
				String medEfc = " ";
				String medMethod = " ";
				String medWarn = " ";
				String medImg = " ";
				
				if(obj.has("itemName")) {
					medGetName = obj.getString("itemName");
				}
				if(obj.has("itemSeq") && !obj.isNull("itemSeq")) {
					medNum = obj.getInt("itemSeq");
				}
				if(obj.has("efcyQesitm") && !obj.isNull("efcyQesitm")) {
					medEfc = obj.getString("efcyQesitm");
				}
				if(obj.has("useMethodQesitm") && !obj.isNull("useMethodQesitm")) {
					medMethod = obj.getString("useMethodQesitm");						
				}
				
				if(obj.has("atpnWarnQesitm") && !obj.isNull("atpnWarnQesitm")) {
					medWarn = obj.getString("atpnWarnQesitm");
				}
				if(obj.has("itemImage") && !obj.isNull("itemImage")) {
					medImg = String.valueOf(obj.get("itemImage"));
				}

				med = new Medicine();
				med.setMedNum(medNum);
				med.setMedName(medGetName);
				med.setMedMethod(medMethod);
				med.setMedEfc(medEfc);
				med.setMedWarn(medWarn);
				med.setMedImg(medImg);
				
				medicineList.add(med);
			}
			if (medicineService.getMedicineInfo(medicineList) > 0) { //DB저장
				System.out.println(medicineList);
				System.out.println("API에서 정보를 가져와  저장했습니다.");
				
			}
			

		} catch (JSONException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return medicineList; //view 뿌리기 위해 리턴
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
