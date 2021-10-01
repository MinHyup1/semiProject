package com.kh.semi.hospitalInfo.model.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.semi.common.code.ErrorCode;
import com.kh.semi.common.db.JDBCTemplate;
import com.kh.semi.common.exception.HandlableException;
import com.kh.semi.hospitalInfo.model.dao.HospitalDao;
import com.kh.semi.hospitalInfo.model.dto.HospitalInfo;
import com.kh.semi.hospitalInfo.model.dto.SearchTreat;

public class HospitalService {

	JDBCTemplate template = JDBCTemplate.getInstance();
	HospitalDao hospDao =  new HospitalDao();

	public int updateHospInfo(List<HospitalInfo> hospList) throws Exception {
		
		Connection conn =  template.getConnection();
		int res = 0;
		try {
			res = hospDao.updateHospInfo(conn,hospList);
			
			conn.commit();
		} catch (Exception e) {
			conn.rollback();
			throw e;
		}finally {
			template.close(conn);
		}
		
		return res;
	}

	public List<HospitalInfo> searchByHospitalName(String keyWord) {
		List<HospitalInfo> hospList = new ArrayList<HospitalInfo>();
		Connection conn = template.getConnection();
		
		try {
			hospList = hospDao.searchByHospitalName(conn,keyWord);
			
		} catch (Exception e) {
			
		}finally {
			template.close(conn);
		}
		
		
		
		return hospList;
	}

	public int bringHospCode(String uniqeCode) {
		Connection conn = template.getConnection();
		int hospCode = 0;
		
		try {
			hospCode = hospDao.bringHospCode(conn,uniqeCode);
			
		} catch (Exception e) {
			
		}finally {
			template.close(conn);
		}
		
		return hospCode;
	}

	public int updateSearchTreat(List<SearchTreat> treatList) throws SQLException {
		
		Connection conn = template.getConnection();
		int res = 0;
		
		try {
			res = hospDao.updateSearchTreat(conn,treatList);
			
		} catch (Exception e) {
			conn.rollback();
			throw e;
			
		}finally {
			template.close(conn);
		}
		
		return res;
	}
	
	//[참고] 륜수 수정(10/01 01:29)
	public HospitalInfo searchByHospitalCode(String hospCode) {
		Connection conn = template.getConnection();
		HospitalInfo hospital = null;
		
		try {
			hospital = hospDao.searchByHospitalCode(conn, hospCode);
		} finally {
			template.close(conn);
		}
		return hospital;
	}
	
}
