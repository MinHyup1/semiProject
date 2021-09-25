package com.kh.semi.schedule.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.kh.semi.common.db.JDBCTemplate;
import com.kh.semi.common.exception.DataAccessException;
import com.kh.semi.schedule.model.dto.Medical;
import com.kh.semi.schedule.model.dto.Prescription;
import com.kh.semi.schedule.model.dto.Visit;

import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;

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

			while (rset.next()) {
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
				+ "schedule_name, hosp_code) values(sc_history_id.nextval, sc_schedule_id.currval, ?, ?, ?)";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setDate(1, medical.getScheduleDate());
			pstm.setString(2, medical.getScheduleName());
			pstm.setString(3, medical.getHospCode());
			pstm.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
	}
	
	public String getCurrentPrescriptionId(Connection conn) {
		PreparedStatement pstm = null;
		String query = "select sc_prescription_id.currval from dual";
		ResultSet rset = null;
		String prescriptionId = "";

		try {
			pstm = conn.prepareStatement(query);
			rset = pstm.executeQuery();

			while (rset.next()) {
				prescriptionId = rset.getString("currval");
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		return prescriptionId;
	}

	public void insertPrescriptionList(Connection conn, Prescription prescription) {
		PreparedStatement pstm = null;
		String query = "insert into prescription_list(prescription_id, schedule_id, prescription_name, "
					+ "start_date, end_date, pharm_code, times_per_day) "
					+ "values(sc_prescription_id.nextval, sc_schedule_id.currval, ?, ?, ?, ?, ?)";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, prescription.getPrescriptionName());
			pstm.setDate(2, prescription.getStartDate());
			pstm.setDate(3, prescription.getEndDate());
			pstm.setString(4, prescription.getPharmCode());
			pstm.setInt(5, prescription.getTimesPerDay());
			pstm.executeUpdate();
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
	}

	public void insertDoseNotice(Connection conn, Timestamp[] doseNoticeDateTimes) {
		CallableStatement cstm = null;
		String query = "{call insert_dose_notice(?,?)}";
		
		try {
			cstm = conn.prepareCall(query);
			
			ArrayDescriptor descriptor = ArrayDescriptor.createDescriptor("NOTICE_TIME_ARR", conn);
			ARRAY noticeTimeArr = new ARRAY(descriptor, conn, doseNoticeDateTimes);
			
			cstm.setArray(1, noticeTimeArr);
			cstm.setInt(2, doseNoticeDateTimes.length);
			cstm.executeUpdate();
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(cstm);
		}
	}

	public void updateHasMedicalRecord(Connection conn, String scheduleId) {
		PreparedStatement pstm = null;
		String query = "update schedule_list set has_medical_record = 'Y' "
							+ "where schedule_id = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, scheduleId);
			pstm.executeUpdate();
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
	}

	public void updateHasPrescription(Connection conn, String scheduleId) {
		PreparedStatement pstm = null;
		String query = "update schedule_list set has_prescription = 'Y' "
							+ "where schedule_id = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, scheduleId);
			pstm.executeUpdate();
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
	}

	public void updateHasDoseNotice(Connection conn, String prescriptionId) {
		PreparedStatement pstm = null;
		String query = "update prescription_list set has_dose_notice = 1 "
							+ "where prescription_id = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, prescriptionId);
			pstm.executeUpdate();
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
	}

	public void insertVisitNotice(Connection conn, Visit visit) {
		PreparedStatement pstm = null;
		String qurey = "insert into visit_notice(visit_notice_code, schedule_id, "
				+ "notice_name, hosp_code, notice_date) "
				+ "values(sc_visit_notice_code.nextval, sc_schedule_id.currval, ?, ?, ?)";
		
		try {
			pstm = conn.prepareStatement(qurey);
			pstm.setString(1, visit.getNoticeName());
			pstm.setString(2, visit.getHospCode());
			pstm.setTimestamp(3, visit.getNoticeDate());
			pstm.executeUpdate();
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
		
	}

	public void updateHasVisitNotice(Connection conn, String scheduleId) {
		PreparedStatement pstm = null;
		String query = "update schedule_list set has_visit_notice = 'Y' "
							+ "where schedule_id = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, scheduleId);
			pstm.executeUpdate();
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
	}

	
	
	
}
