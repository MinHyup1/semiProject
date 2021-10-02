package com.kh.semi.main.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.semi.common.db.JDBCTemplate;
import com.kh.semi.main.model.dao.CovidDao;
import com.kh.semi.main.model.dto.Covid;

public class CovidService {

	JDBCTemplate template = JDBCTemplate.getInstance();
	CovidDao covidDao = new CovidDao();
	
	public int updateCovidInfo(Covid covidInfo) throws Exception {
		
		Connection conn =  template.getConnection();
		int res = 0;
		try {
			res = covidDao.updateCovidInfo(conn,covidInfo);
			
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
