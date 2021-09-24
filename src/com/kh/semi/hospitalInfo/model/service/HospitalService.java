package com.kh.semi.hospitalInfo.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.semi.common.code.ErrorCode;
import com.kh.semi.common.db.JDBCTemplate;
import com.kh.semi.common.exception.HandlableException;
import com.kh.semi.hospitalInfo.model.dao.HospitalDao;
import com.kh.semi.hospitalInfo.model.dto.HospitalInfo;

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
	
}
