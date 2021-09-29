package com.kh.semi.hospitalInfo.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Templates;

import com.kh.semi.common.code.ErrorCode;
import com.kh.semi.common.db.JDBCTemplate;
import com.kh.semi.common.exception.HandlableException;
import com.kh.semi.hospitalInfo.model.dto.HospitalInfo;

public class HospitalDao {
	
	JDBCTemplate template = JDBCTemplate.getInstance();
	

	public int updateHospInfo(Connection conn, List<HospitalInfo> hospList) {
		PreparedStatement pstm = null;
		int res = 0;
		String query = "insert into HOSPITAL_INFO(HOSP_CODE, HOSP_TREAT, HOSP_TELL, HOSP_NAME, HOSP_URL"
				+ ", HOSP_ADDRESS, HOSP_XPOS, HOSP_YPOS) values(HOSPITAL_CODE.NEXTVAL,?,?,?,?,?,?,?)";
		
		try {
			
			for(int i = 0; i < hospList.size(); i++) {
				HospitalInfo hosp = hospList.get(i);
				pstm = conn.prepareStatement(query);
				pstm.setString(1, hosp.getHospTreat());
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
				hosp.setHospTreat(rset.getString("HOSP_TREAT"));
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



}
