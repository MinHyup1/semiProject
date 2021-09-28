package com.kh.semi.schedule.model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.kh.semi.common.db.JDBCTemplate;
import com.kh.semi.common.exception.DataAccessException;
import com.kh.semi.schedule.model.dto.Medical;
import com.kh.semi.schedule.model.dto.Prescription;
import com.kh.semi.schedule.model.dto.Schedule;
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

	public void updateHasMedicalRecord(Connection conn, String scheduleId, String status) {
		PreparedStatement pstm = null;
		String query = "update schedule_list set has_medical_record = ? "
							+ "where schedule_id = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, status);
			pstm.setString(2, scheduleId);
			pstm.executeUpdate();
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
	}

	public void updateHasPrescription(Connection conn, String scheduleId, String status) {
		PreparedStatement pstm = null;
		String query = "update schedule_list set has_prescription = ? "
							+ "where schedule_id = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, status);
			pstm.setString(2, scheduleId);
			pstm.executeUpdate();
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
	}

	public void updateHasVisitNotice(Connection conn, String scheduleId, String status) {
		PreparedStatement pstm = null;
		String query = "update schedule_list set has_visit_notice = ? "
							+ "where schedule_id = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, status);
			pstm.setString(2, scheduleId);
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
	
	public List<Schedule> selectScheduleListByUser(Connection conn, String userCode) {
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "select * from schedule_list where user_code = ?";
		List<Schedule> scheduleList = new ArrayList<Schedule>();
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userCode);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				scheduleList.add(parseResultSetToSchedule(rset));
			}
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		return scheduleList;
	}

	public Medical selectMedicalHistoryByScheduleId(Connection conn, String scheduleId) {
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "select * from medical_history where schedule_id = ?";
		Medical medical = null;
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, scheduleId);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				medical = parseResultSetToMedical(rset);
			}
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		return medical;
	}
	
	public Prescription selectPrescriptionListByScheduleId(Connection conn, String scheduleId) {
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "select * from prescription_list where schedule_id = ?";
		Prescription prescription = null;
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, scheduleId);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				prescription = parseResultSetToPrescription(rset);
			}
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		return prescription;
	}
	
	public List<Visit> selectVisitNoticeByScheduleId(Connection conn, String scheduleId) {
		PreparedStatement pstm = null;
		ResultSet rset = null;
		String query = "select * from visit_notice where schedule_id = ?";
		List<Visit> visitList = new ArrayList<Visit>();
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, scheduleId);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				visitList.add(parseResultSetToVisit(rset));
			}
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		return visitList;
	}

	public Medical selectMedicalById(Connection conn, String historyId) {
		PreparedStatement pstm = null;
		String query = "select * from medical_history where history_id = ?";
		ResultSet rset = null;
		Medical medical = null;
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, historyId);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				medical = parseResultSetToMedical(rset);
			}
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		return medical;
	}
	
	public Prescription selectPrescriptionById(Connection conn, String prescriptionId) {
		PreparedStatement pstm = null;
		String query = "select * from prescription_list where prescription_id = ?";
		ResultSet rset = null;
		Prescription prescription = null;
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, prescriptionId);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				prescription = parseResultSetToPrescription(rset);
			}
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		return prescription;
	}

	public Set<String> selectDoseNoticeTimeById(Connection conn, String prescriptionId) {
		PreparedStatement pstm = null;
		String query = "select * from dose_notice_list where prescription_id = ?";
		ResultSet rset = null;
		Set<String> timeSet = new HashSet<String>();
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, prescriptionId);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				Timestamp time = rset.getTimestamp("notice_time");
				int hours = time.getHours();
				int minutes = time.getMinutes();
				
				String hh = hours < 10 ? "0" + hours : String.valueOf(hours);
				String mi = minutes < 10 ? "0" + minutes : String.valueOf(minutes);
				String timeStr = hh + ":" + mi + ":00";
				timeSet.add(timeStr);
			}
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		return timeSet;
	}
	
	public Visit selectVisitByCode(Connection conn, String visitNoticeCode) {
		PreparedStatement pstm = null;
		String query = "select * from visit_notice where visit_notice_code = ?";
		ResultSet rset = null;
		Visit visit = null;
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, visitNoticeCode);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				visit = parseResultSetToVisit(rset);
			}
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		return visit;
	}
	
	public Schedule selectScheduleListById(Connection conn, String scheduleId) {
		PreparedStatement pstm = null;
		ResultSet rset = null;
		Schedule schedule = null;
		String query = "select * from schedule_list where schedule_id = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, scheduleId);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				schedule = parseResultSetToSchedule(rset);
			}
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(rset, pstm);
		}
		return schedule;
	}
	
	public void deleteScheduleListById(Connection conn, String scheduleId) {
		PreparedStatement pstm = null;
		String query = "delete from schedule_list where schedule_id = ?";
		
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
	
	public void deleteMedicalById(Connection conn, String historyId) {
		PreparedStatement pstm = null;
		String query = "delete from medical_history where history_id = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, historyId);
			pstm.executeUpdate();
			
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
	}
	
	public void deletePrescriptionById(Connection conn, String prescriptionId) {
		PreparedStatement pstm = null;
		String query = "delete from prescription_list where prescription_id = ?";
		
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
	
	public void deleteVisitByCode(Connection conn, String visitNoticeCode) {
		PreparedStatement pstm = null;
		String query = "delete from visit_notice where visit_notice_code = ?";
		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, visitNoticeCode);
			pstm.executeUpdate();
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			template.close(pstm);
		}
	}
	
	
	
	
	

	private Schedule parseResultSetToSchedule(ResultSet rset) throws SQLException {
		Schedule schedule = new Schedule();
		schedule.setScheduleId(rset.getString("schedule_id"));
		schedule.setUserCode(rset.getString("user_code"));
		schedule.setHasMedicalRecord(rset.getString("has_medical_record"));
		schedule.setHasPrescription(rset.getString("has_prescription"));
		schedule.setHasVisitNotice(rset.getString("has_visit_notice"));
		schedule.setRegDate(rset.getDate("reg_date"));
		return schedule;
	}

	private Medical parseResultSetToMedical(ResultSet rset) throws SQLException {
		Medical medical = new Medical();
		medical.setHistoryId(rset.getString("history_id"));
		medical.setScheduleId(rset.getString("schedule_id"));
		medical.setScheduleDate(rset.getDate("schedule_date"));
		medical.setScheduleName(rset.getString("schedule_name"));
		medical.setHospCode(rset.getString("hosp_code"));
		medical.setRegDate(rset.getDate("reg_date"));
		return medical;
	}

	private Prescription parseResultSetToPrescription(ResultSet rset) throws SQLException {
		Prescription prescription = new Prescription();
		prescription.setPrescriptionId(rset.getString("prescription_id"));
		prescription.setScheduleId(rset.getString("schedule_id"));
		prescription.setPrescriptionName(rset.getString("prescription_name"));
		prescription.setStartDate(rset.getDate("start_date"));
		prescription.setEndDate(rset.getDate("end_date"));
		prescription.setPharmCode(rset.getString("pharm_code"));
		prescription.setHasMedicine(rset.getString("has_medicine"));
		prescription.setTimesPerDay(rset.getInt("times_per_day"));
		prescription.setHasDoseNotice(rset.getInt("has_dose_notice"));
		prescription.setRegDate(rset.getDate("reg_date"));
		return prescription;
	}

	private Visit parseResultSetToVisit(ResultSet rset) throws SQLException {
		Visit visit = new Visit();
		visit.setVisitNoticeCode(rset.getString("visit_notice_code"));
		visit.setScheduleId(rset.getString("schedule_id"));
		visit.setNoticeName(rset.getString("notice_name"));
		visit.setHospCode(rset.getString("hosp_code"));
		visit.setNoticeDate(rset.getTimestamp("notice_date"));
		visit.setIsNoticed(rset.getString("is_noticed"));
		visit.setRegDate(rset.getDate("reg_date"));
		return visit;
	}

	


	

	
	
	
}
