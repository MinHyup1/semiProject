package com.kh.semi.schedule.model.service;

import java.sql.Connection;

import com.kh.semi.common.db.JDBCTemplate;
import com.kh.semi.schedule.model.dao.ScheduleDao;

public class ScheduleService {

	private ScheduleDao scheduleDao = new ScheduleDao();
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	public String insertScheduleList(String userCode) {
		Connection conn = template.getConnection();
		String scheduleId = "";
		
		try {
			scheduleDao.insertScheduleList(userCode, conn);
			scheduleId = scheduleDao.getCurrentScheduleId(conn);
			
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		return scheduleId;
	}
	
}
