package com.kh.semi.pharmacy.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.semi.common.db.JDBCTemplate;

import com.kh.semi.common.exception.DataAccessException;

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


	public List<Pharmacy> pharmacyFindByName(String name, Connection conn) {
		List<Pharmacy> pharmacyList = new ArrayList<Pharmacy>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		
		String query = "SELECT * FROM PHARM WHERE PHARM_NAME like ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, "%"+name+"%");
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Pharmacy pharmacy = new Pharmacy();
				pharmacy = parseRsetToPharmacy(rset);				
				pharmacyList.add(pharmacy);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			template.close(rset, pstm);
		}
		
		return pharmacyList;
	}

	public List<Pharmacy> pharmacyFindByaddress(String address, Connection conn) {
		List<Pharmacy> pharmacyList = new ArrayList<Pharmacy>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
	
		String query = "SELECT * FROM PHARM WHERE PHARM_LOC like ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, "%"+address+"%");
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Pharmacy pharmacy = new Pharmacy();
				pharmacy = parseRsetToPharmacy(rset);				
				pharmacyList.add(pharmacy);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			template.close(rset, pstm);
		}
		
		return pharmacyList;
	}
	
	//[??????] ?????? ?????? 10/01 10:54
	public Pharmacy selectPharmByCode(Connection conn, String pharmCode) {
		PreparedStatement pstm = null;
		String query = "select * from pharm where pharm_code = ?";
		ResultSet rset = null;
		Pharmacy pharmacy = null;
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, pharmCode);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				pharmacy = parseRsetToPharmacy(rset);
			}
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		
		return pharmacy;
	}
	
	//[??????] ?????? ?????? 10/01 10:54
	//DB?????? Pharmacy ???????????? ?????? ???????????????!!
	private Pharmacy parseRsetToPharmacy(ResultSet rset) throws SQLException {
		Pharmacy pharmacy = new Pharmacy();
		pharmacy.setPharName(rset.getString("pharm_name"));
		pharmacy.setPharLoc(rset.getString("pharm_loc"));
		pharmacy.setPharLat(rset.getString("pharm_lat"));
		pharmacy.setPharLon(rset.getString("pharm_lon"));
		pharmacy.setPharCode(rset.getString("pharm_code"));
		return pharmacy;
	}


	public List<Pharmacy> pharmacyFindByCode(String code, Connection conn) {
		List<Pharmacy> pharmacyList = new ArrayList<Pharmacy>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
	
		String query = "SELECT * FROM PHARM WHERE PHARM_CODE = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, code);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Pharmacy pharmacy = new Pharmacy();
				pharmacy = parseRsetToPharmacy(rset);				
				pharmacyList.add(pharmacy);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			template.close(rset, pstm);
		}
		
		return pharmacyList;		
	}
}

