package com.kh.semi.pharmacy.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.kh.semi.common.db.JDBCTemplate;
import com.kh.semi.pharmacy.model.dto.Pharmacy;

public class PharmacyDao {
	
	private JDBCTemplate template = JDBCTemplate.getInstance(); 
	
	public int getPharmacyInfo(List<Pharmacy> pharmacyList, Connection conn) {
		PreparedStatement pstm = null;
		int res = 0;
		String query = "insert into PHARM(PHARM_NAME, PHARM_LOC, PHARM_LAT, PHARM_LON, PHARM_CODE)"
				+ " values(?,?,?,?,?)";
		try {
			for (int i = 0; i < pharmacyList.size(); i++) {
				Pharmacy pharmacy = pharmacyList.get(i);
				pstm = conn.prepareStatement(query);

				pstm.setString(1, pharmacy.getPharName());
				pstm.setString(2, pharmacy.getPharLoc());
				pstm.setString(3, pharmacy.getPharLat());
				pstm.setString(4, pharmacy.getPharLon());
				pstm.setString(5, pharmacy.getPharCode());
				
				res = pstm.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			template.close(pstm);
		}
		return res;
	}

}
