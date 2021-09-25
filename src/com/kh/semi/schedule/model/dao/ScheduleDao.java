package com.kh.semi.schedule.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.kh.semi.common.db.JDBCTemplate;
import com.kh.semi.common.exception.DataAccessException;
import com.kh.semi.schedule.model.dto.Medical;
import com.kh.semi.schedule.model.dto.Prescription;

public class ScheduleDao {

	private JDBCTemplate template = JDBCTemplate.getInstance();

	public void insertScheduleList(Connection conn, String userCode) {
		PreparedStatement pstm = null;
		String query = "insert into schedule_list(schedule_id, user_code) "
							+ "values(sc_schedule_id.nextval, ?)";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userCode);
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
	}

	public String getCurrentScheduleId(Connection conn) {
		PreparedStatement pstm = null;
		String query = "select sc_schedule_id.currval from dual";
		ResultSet rset = null;
		String scheduleId = "";
		
		try {
			pstm = conn.prepareStatement(query);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				scheduleId = rset.getString("currval");
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		return scheduleId;
	}

	public void insertMedicalHistory(Connection conn, Medical medical) {
		PreparedStatement pstm = null;
		String query = "insert into medical_history(history_id, schedule_id, schedule_date, "
				+ "schedule_name, hosp_code) values(sc_history_id.nextval, ?, ?, ?, ?)";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, medical.getScheduleId());
			pstm.setDate(2, medical.getScheduleDate());
			pstm.setString(3, medical.getScheduleName());
			pstm.setString(4, medical.getHospCode());
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		
		
	}

	public void insertPrescriptionList(Connection conn, Prescription prescrition) {
		
	}

	public void insertDoseNotice(Connection conn, Timestamp[] doseNoticeDateTimes) {
		
	}
	
	
}
