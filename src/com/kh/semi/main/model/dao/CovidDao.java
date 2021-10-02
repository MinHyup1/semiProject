package com.kh.semi.main.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.kh.semi.common.db.JDBCTemplate;
import com.kh.semi.hospitalInfo.model.dto.HospitalInfo;
import com.kh.semi.main.model.dto.Covid;

public class CovidDao {
	
	JDBCTemplate template = JDBCTemplate.getInstance();

	public int updateCovidInfo(Connection conn, Covid covidInfo) {
		PreparedStatement pstm = null;
		int res = 0;
		
		String query = "update covid_info set days = ?, decideCnt = ? where num = ?";
		
		try {
			
			pstm = conn.prepareStatement(query);
			pstm.setString(1, covidInfo.getDays());
			pstm.setInt(2, covidInfo.getDecideCnt());
			pstm.setInt(3, covidInfo.getNum());
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
		 	e.printStackTrace();
		}finally {
			template.close(pstm);
		}
		
		return res;
	}

}
