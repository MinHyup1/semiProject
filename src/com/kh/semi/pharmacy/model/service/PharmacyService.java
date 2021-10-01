package com.kh.semi.pharmacy.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.semi.common.db.JDBCTemplate;
import com.kh.semi.pharmacy.model.dao.PharmacyDao;
import com.kh.semi.pharmacy.model.dto.Pharmacy;

public class PharmacyService {
	
	private JDBCTemplate template = JDBCTemplate.getInstance();
	private PharmacyDao pharmacyDao = new PharmacyDao();
	
	public int getPharmacyInfo(List<Pharmacy> pharmacyList) throws Exception {
		Connection conn = template.getConnection();
		int res = 0;
		try {			
			res = pharmacyDao.getPharmacyInfo(pharmacyList,conn);
			conn.commit();
		} catch(Exception e) {
			conn.rollback();
			throw e;
		}
		finally {
			template.close(conn);
		}
		return res;
	}
	
	//[참고] 륜수 수정 10/01 10:44
	public String getPharmNameByCode(String pharmCode) {
		Connection conn = template.getConnection();
		Pharmacy pharmacy = null;
		String pharmName = null;
		
		try {
			pharmacy = pharmacyDao.selectPharmByCode(conn, pharmCode);
			pharmName = pharmacy.getPharName();
		} finally {
			template.close(conn);
		}
		return pharmName;
	}
}
