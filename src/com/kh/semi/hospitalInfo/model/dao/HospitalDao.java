package com.kh.semi.hospitalInfo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.transform.Templates;

import com.kh.semi.common.code.ErrorCode;
import com.kh.semi.common.db.JDBCTemplate;
import com.kh.semi.common.exception.DataAccessException;
import com.kh.semi.common.exception.HandlableException;
import com.kh.semi.hospitalInfo.model.dto.HospitalInfo;
import com.kh.semi.hospitalInfo.model.dto.SearchTreat;

public class HospitalDao {
	
	JDBCTemplate template = JDBCTemplate.getInstance();
	

	public int updateHospInfo(Connection conn, List<HospitalInfo> hospList) {
		PreparedStatement pstm = null;
		int res = 0;
		String query = "insert into HOSPITAL_INFO(HOSP_CODE, UNIQUE_CODE, HOSP_TELL, HOSP_NAME, HOSP_URL"
				+ ", HOSP_ADDRESS, HOSP_XPOS, HOSP_YPOS) values(HOSPITAL_CODE.NEXTVAL,?,?,?,?,?,?,?)";
		
		try {
			
			for(int i = 0; i < hospList.size(); i++) {
				HospitalInfo hosp = hospList.get(i);
				pstm = conn.prepareStatement(query);
				pstm.setString(1, hosp.getUniqeCode());
				pstm.setString(2, hosp.getHospTell());
				pstm.setString(3, hosp.getHospName());
				pstm.setString(4, hosp.getHospUrl());
				pstm.setString(5, hosp.getAddress());
				pstm.setString(6, hosp.getxPos());
				pstm.setString(7, hosp.getyPos());
				res = pstm.executeUpdate();
			}
			
		} catch (SQLException e) {
		 	e.printStackTrace();
		}finally {
			template.close(pstm);
		}
		
		return res;
	}


	public List<HospitalInfo> searchByHospitalName(Connection conn, String keyWord) {
		
		List<HospitalInfo> hospList = new ArrayList<HospitalInfo>();
		HospitalInfo hosp = null;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "select * from HOSPITAL_INFO where HOSP_NAME like '%' || ? || '%'";
				//select * from member where id like '%' || ? || '%'"
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1,keyWord);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				hosp = new HospitalInfo();
				hosp.setHospCode(rset.getInt("HOSP_CODE"));
				hosp.setHospTell(rset.getString("HOSP_TELL"));
				hosp.setHospName(rset.getString("HOSP_NAME"));
				hosp.setHospUrl(rset.getString("HOSP_URL"));
				hosp.setAddress(rset.getString("HOSP_ADDRESS"));
				hosp.setxPos(rset.getString("HOSP_XPOS"));
				hosp.setyPos(rset.getString("HOSP_YPOS"));
				
				hospList.add(hosp);
				
			}

			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			template.close(rset, pstm);
		}
		
		
		return hospList;
	}


	public int bringHospCode(Connection conn, String uniqeCode) throws SQLException {
		int hospCode = 0;
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String query = "select hosp_code from hospital_info where unique_code = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1,uniqeCode);
			
			rset = pstm.executeQuery();
			
			if(rset.next()) {
				hospCode = rset.getInt("HOSP_CODE");
			}
		} catch (SQLException e) {
			throw e;
		}finally {
			template.close(rset, pstm);
		}
		return hospCode;
	}


	public int updateSearchTreat(Connection conn, List<SearchTreat> treatList) {
		int res = 0;
		PreparedStatement pstm = null;
		
		String query = "insert into SEARCH_TREAT(TREAT_INDEX, HOSP_CODE, TREAT_CODE)"
				+ "values(TREAT_INDEX.NEXTVAL, ? , ?)";
		
		try {
			
			for (int i = 0; i < treatList.size(); i++) {
				
				pstm = conn.prepareStatement(query);
				pstm.setInt(1, treatList.get(i).getHospCode());
				pstm.setString(2, treatList.get(i).getTreatCode());
				res = pstm.executeUpdate();
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			template.close(pstm);
		}
		
		
		return res;
	}
	
	//[참고] 륜수 수정(10/01 01:32)
	public HospitalInfo searchByHospitalCode(Connection conn, String hospCode) {
		PreparedStatement pstm = null;
		ResultSet rset = null;
		HospitalInfo hospital = null;
		String query = "select * from hospital_info where hosp_code = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, hospCode);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				hospital = new HospitalInfo();
				hospital.setHospCode(rset.getInt("HOSP_CODE"));
				hospital.setHospTell(rset.getString("HOSP_TELL"));
				hospital.setHospName(rset.getString("HOSP_NAME"));
				hospital.setHospUrl(rset.getString("HOSP_URL"));
				hospital.setAddress(rset.getString("HOSP_ADDRESS"));
				hospital.setxPos(rset.getString("HOSP_XPOS"));
				hospital.setyPos(rset.getString("HOSP_YPOS"));
			}
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		return hospital;
	}

	public List<String> searchByTreatCode(Connection conn, String[] treatCheckBox) {
		List<String> hospCodeList = new ArrayList<String>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String questionMark = "("; 
		for (int i = 0; i < treatCheckBox.length; i++) { //string배열에맞게 물음표갯수를 맞춰준다.
			if(i == treatCheckBox.length-1) {
				questionMark += "? )";
			}else {
				questionMark += "?,";
			}
			
		}
		String query = "select distinct  HOSP_CODE from search_treat where TREAT_CODE IN " + questionMark;
		
		try {
			pstm = conn.prepareStatement(query);
			for (int i = 0; i < treatCheckBox.length; i++) {
				pstm.setString(i + 1 , treatCheckBox[i]);
			}
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				hospCodeList.add(rset.getString("HOSP_CODE"));
			}
									
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			template.close(rset, pstm);
		}
		
		
		
		
		
		
		
		return hospCodeList;
	}


	public List<HospitalInfo> searchByHospitalCode(Connection conn, List<String> hospCodeList) {
		List<HospitalInfo> hospInfoList = new ArrayList<HospitalInfo>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		HospitalInfo hosp = null;
		String query = "select * from hospital_info where hosp_code = ?"; 
		for (int i = 0; i < hospCodeList.size(); i++) {
			try {

				pstm = conn.prepareStatement(query);
				pstm.setString(1, hospCodeList.get(i));

				rset = pstm.executeQuery();

				if (rset.next()) {
					hosp = new HospitalInfo();
					hosp.setHospCode(rset.getInt("HOSP_CODE"));
					hosp.setHospTell(rset.getString("HOSP_TELL"));
					hosp.setHospName(rset.getString("HOSP_NAME"));
					hosp.setHospUrl(rset.getString("HOSP_URL"));
					hosp.setAddress(rset.getString("HOSP_ADDRESS"));
					hosp.setxPos(rset.getString("HOSP_XPOS"));
					hosp.setyPos(rset.getString("HOSP_YPOS"));

					hospInfoList.add(hosp);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				template.close(rset, pstm);
			}
		}
		
		
		
		
		return hospInfoList;

		
	}


	public List<HospitalInfo> searchByKeywordAndTreatCode(Connection conn, String keyWord, String[] treatCheckBox) {
		List<HospitalInfo> hospInfoList = new ArrayList<HospitalInfo>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		HospitalInfo hosp = null;
		String questionMark = "("; 
		for (int i = 0; i < treatCheckBox.length; i++) { //string배열에맞게 물음표갯수를 맞춰준다.
			if(i == treatCheckBox.length-1) {
				questionMark += "? )";
			}else {
				questionMark += "?,";
			}
			
		}
		String query = "select * " + 
				"from hospital_info " + 
				"join search_treat using(hosp_code)" + 
				"where hosp_name like '%' || ? || '%'"
				+ "and treat_code in " + questionMark; 
		try {
			pstm = conn.prepareStatement(query);
			for(int i = 0; i < treatCheckBox.length; i++) {
				pstm.setString(1, keyWord);
				pstm.setString(i + 2, treatCheckBox[i]);
			}
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				hosp = new HospitalInfo();
				hosp.setHospCode(rset.getInt("HOSP_CODE"));
				hosp.setHospTell(rset.getString("HOSP_TELL"));
				hosp.setHospName(rset.getString("HOSP_NAME"));
				hosp.setHospUrl(rset.getString("HOSP_URL"));
				hosp.setAddress(rset.getString("HOSP_ADDRESS"));
				hosp.setxPos(rset.getString("HOSP_XPOS"));
				hosp.setyPos(rset.getString("HOSP_YPOS"));

				hospInfoList.add(hosp);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			template.close(rset, pstm);
		}
		
		
		
		return hospInfoList;
	}



}
