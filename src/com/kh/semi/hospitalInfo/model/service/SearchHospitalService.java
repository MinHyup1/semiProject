package com.kh.semi.hospitalInfo.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.semi.common.db.JDBCTemplate;
import com.kh.semi.hospitalInfo.model.dao.SearchHospitalDao;
import com.kh.semi.hospitalInfo.model.dto.HospitalInfo;

public class SearchHospitalService {
	
	JDBCTemplate template = JDBCTemplate.getInstance();
	SearchHospitalDao hospDao =  new SearchHospitalDao();
	
	public List<String> searchByTreatCode(String[] treatCheckBox) {
		Connection conn = template.getConnection();
		List<String> hospCodeList = null;
		
		try {
			hospCodeList = hospDao.searchByTreatCode(conn,treatCheckBox);
			conn.commit();
		} catch (Exception e) {
			
		}finally {
			template.close(conn);
		}
		
		return hospCodeList;
		
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
	
	public List<HospitalInfo> searchByKeywordAndTreatCode(String keyWord, String[] treatCheckBox) {
		Connection conn = template.getConnection();
		List<HospitalInfo> hospInfoList = null;
		
		try {
			hospInfoList = hospDao.searchByKeywordAndTreatCode(conn,keyWord,treatCheckBox);
		} catch (Exception e) {
			
		}finally {
			template.close(conn);
		}
		
		return hospInfoList;
	}


}
