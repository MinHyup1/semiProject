package com.kh.semi.main.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

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

	public JSONArray covidDecideCnt(Connection conn) {
		
		PreparedStatement pstm = null;
		ResultSet rset = null;
		JSONObject jsonO = null;
		JSONArray covidJson = new JSONArray();
		String query = "select * from covid_info order by num";
		
		try {
			pstm = conn.prepareStatement(query);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				jsonO = new JSONObject();
				jsonO.put("days", rset.getString("days"));
				jsonO.put("decideCnt", rset.getInt("decidecnt"));
				covidJson.put(jsonO);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			template.close(rset, pstm);
		}
		
		return covidJson;
	}

}
