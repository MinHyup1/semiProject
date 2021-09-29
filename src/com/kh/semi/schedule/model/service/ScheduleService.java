package com.kh.semi.schedule.model.service;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.kh.semi.common.db.JDBCTemplate;
import com.kh.semi.schedule.model.dao.ScheduleDao;
import com.kh.semi.schedule.model.dto.Medical;
import com.kh.semi.schedule.model.dto.Prescription;
import com.kh.semi.schedule.model.dto.Schedule;
import com.kh.semi.schedule.model.dto.Visit;

public class ScheduleService {

	private ScheduleDao scheduleDao = new ScheduleDao();
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	public void insertMainSchedule(String userCode, Map<String, Object> dtoMap) {
		Connection conn = template.getConnection();
		
		try {
			String scheduleId = insertScheduleList(conn, userCode);
			
			if(dtoMap.get("medical") != null) {
				Medical medical = (Medical) dtoMap.get("medical");
				insertMedicalHistory(conn, medical, scheduleId);
			}
			
			if(dtoMap.get("prescription") != null) {
				Prescription prescription = (Prescription) dtoMap.get("prescription");
				String prescriptionId = insertPrescription(conn, prescription, scheduleId);
				
				if(dtoMap.get("doseNoticeDateTimes") != null) {
					Timestamp[] doseNoticeDateTimes = (Timestamp[]) dtoMap.get("doseNoticeDateTimes");
					insertDoseNoticeList(conn, doseNoticeDateTimes, prescriptionId);
				}
				
				// 처방 약이 있다면, medicine_record에 insert 하고, prescription_list의 has_medicine Y로 바꾸기
				if(dtoMap.get("medicine") != null) {
					
				}
				
			}
			
			if(dtoMap.get("visitList") != null) {
				List<Visit> visitList = (List<Visit>) dtoMap.get("visitList");
				for (int i = 0; i < visitList.size(); i++) {
					scheduleDao.insertVisitNotice(conn, visitList.get(i));
				}
				scheduleDao.updateHasVisitNotice(conn, scheduleId, "Y");
			}
			
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
		
	}
	
	public void insertDoseNoticeOnly(String userCode, Map<String, Object> dtoMap) {
		Connection conn = template.getConnection();
		
		try {
			String scheduleId = insertScheduleList(conn, userCode);
			
			Prescription prescription = (Prescription) dtoMap.get("prescription");
			String prescriptionId = insertPrescription(conn, prescription, scheduleId);
			
			Timestamp[] doseNoticeDateTimes = (Timestamp[]) dtoMap.get("doseNoticeDateTimes");
			insertDoseNoticeList(conn, doseNoticeDateTimes, prescriptionId);
			
			// 처방 약이 있다면, medicine_record에 insert 하고, prescription_list의 has_medicine Y로 바꾸기
			if(dtoMap.get("medicine") != null) {
				
			}
			
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
	}
	
	public void insertVisitNoticeOnly(String userCode, Visit visit) {
		Connection conn = template.getConnection();
		
		try {
			String scheduleId = insertScheduleList(conn, userCode);
			scheduleDao.insertVisitNotice(conn, visit);
			scheduleDao.updateHasVisitNotice(conn, scheduleId, "Y");
			
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
	}
	
	public Map<String, Object> selectScheduleByUser(String userCode) {
		Connection conn = template.getConnection();
		Map<String, Object> scheduleMap = new HashMap<String, Object>();
		List<Medical> medicalList = new ArrayList<Medical>();
		List<Prescription> prescriptionList = new ArrayList<Prescription>();
		List<Visit> visitList = new ArrayList<Visit>();
		
		try {
			List<Schedule> scheduleList = scheduleDao.selectScheduleListByUser(conn, userCode);
			Schedule schedule = null;
			String scheduleId = "";
			
			for (int i = 0; i < scheduleList.size(); i++) {
				schedule = scheduleList.get(i);
				scheduleId = schedule.getScheduleId();
				
				if(schedule.getHasMedicalRecord().equals("Y")) {
					medicalList.add(scheduleDao.selectMedicalHistoryByScheduleId(conn, scheduleId));
				}
				
				if(schedule.getHasPrescription().equals("Y")) {
					prescriptionList.add(scheduleDao.selectPrescriptionListByScheduleId(conn, scheduleId));
				}
				
				if(schedule.getHasVisitNotice().equals("Y")) {
					List<Visit> visitByScheduleId = scheduleDao.selectVisitNoticeByScheduleId(conn, scheduleId);
					for (int j = 0; j < visitByScheduleId.size(); j++) {
						visitList.add(visitByScheduleId.get(j));
					}
				}
			}
			scheduleMap.put("medicalList", medicalList);
			scheduleMap.put("prescriptionList", prescriptionList);
			scheduleMap.put("visitList", visitList);
		} finally {
			template.close(conn);
		}
		return scheduleMap;
	}
	
	public Medical selectMedicalById(String historyId) {
		Connection conn = template.getConnection();
		Medical medical = null;
		
		try {
			medical = scheduleDao.selectMedicalById(conn, historyId);
		} finally {
			template.close(conn);
		}
		return medical;
	}
	
	public Map<String, Object> selectPrescriptionById(String prescriptionId) {
		Connection conn = template.getConnection();
		Map<String, Object> prescMap = new HashMap<String, Object>();
		
		try {
			Prescription prescription = scheduleDao.selectPrescriptionById(conn, prescriptionId);
			prescMap.put("prescription", prescription);
			
			if(prescription.getHasDoseNotice() != 0) {
				Set<String> doseNoticeTimeSet = scheduleDao.selectDoseNoticeTimeById(conn, prescription.getPrescriptionId());
				prescMap.put("timeSet", doseNoticeTimeSet);
			}
			
			if(prescription.getHasMedicine().equals("Y")) {
				//약 검색 해결되면 진행 prescMap.put("medicine", medicineList);
			}
			
		} finally {
			template.close(conn);
		}
		return prescMap;
	}
	
	public Visit selectVisitByCode(String visitNoticeCode) {
		Connection conn = template.getConnection();
		Visit visit = null;
		
		try {
			visit = scheduleDao.selectVisitByCode(conn, visitNoticeCode);
		} finally {
			template.close(conn);
		}
		return visit;
	}
	
	public void deleteMedical(Medical medical) {
		Connection conn = template.getConnection();
		String scheduleId = medical.getScheduleId();
		
		try {
			//삭제하고
			scheduleDao.deleteMedicalById(conn, medical.getHistoryId());
			//schedule_list 유무 수정하고
			scheduleDao.updateHasMedicalRecord(conn, scheduleId, "N");
			//schedule_list 받아와서
			Schedule schedule = scheduleDao.selectScheduleListById(conn, scheduleId);
			//세가지 유무 상태가 모두 'N'이면 schedule_list 삭제
			if(schedule.getHasMedicalRecord().equals("N") && schedule.getHasPrescription().equals("N") && schedule.getHasVisitNotice().equals("N")) {
				scheduleDao.deleteScheduleListById(conn, scheduleId);
			}
			
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
	}
	
	public void deletePrescription(Prescription prescription) {
		Connection conn = template.getConnection();
		String scheduleId = prescription.getScheduleId();
		
		try {
			//삭제하고
			scheduleDao.deletePrescriptionById(conn, prescription.getPrescriptionId());
			//schedule_list 유무 수정하고
			scheduleDao.updateHasPrescription(conn, scheduleId, "N");
			//schedule_list 받아와서
			Schedule schedule = scheduleDao.selectScheduleListById(conn, scheduleId);
			//세가지 유무 상태가 모두 'N'이면 schedule_list 삭제
			if(schedule.getHasMedicalRecord().equals("N") && schedule.getHasPrescription().equals("N") && schedule.getHasVisitNotice().equals("N")) {
				scheduleDao.deleteScheduleListById(conn, scheduleId);
			}
			
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
	}
	
	public void deleteVisit(Visit visit) {
		Connection conn = template.getConnection();
		String scheduleId = visit.getScheduleId();
		
		try {
			//삭제하고
			scheduleDao.deleteVisitByCode(conn, visit.getVisitNoticeCode());
			//현재 scheduleId에 해당하는 visit_notice 받아와서 size 0이 아니면 return
			if(scheduleDao.selectVisitNoticeByScheduleId(conn, scheduleId).size() != 0) return;
			//schedule_list 유무 수정하고
			scheduleDao.updateHasVisitNotice(conn, scheduleId, "N");
			//schedule_list 받아와서
			Schedule schedule = scheduleDao.selectScheduleListById(conn, scheduleId);
			//세가지 유무 상태가 모두 'N'이면 schedule_list 삭제
			if(schedule.getHasMedicalRecord().equals("N") && schedule.getHasPrescription().equals("N") && schedule.getHasVisitNotice().equals("N")) {
				scheduleDao.deleteScheduleListById(conn, scheduleId);
			}
			
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
	}
	
	public void updateMedical(Medical newMedical) {
		Connection conn = template.getConnection();
		
		try {
			//기존 medical 삭제
			scheduleDao.deleteMedicalById(conn, newMedical.getHistoryId());
			//새로둔 medical 등록
			scheduleDao.insertMedicalHistoryWithOriginId(conn, newMedical);
			
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
	}
	
	public void updatePrescription(Map<String, Object> dtoMap) {
		Connection conn = template.getConnection();
		
		try {
			Prescription prescription = (Prescription) dtoMap.get("prescription");
			String prescriptionId = prescription.getPrescriptionId();
			//기존 리스트 삭제
			scheduleDao.deletePrescriptionById(conn, prescriptionId);
			
			scheduleDao.insertPrescriptionListWithOriginId(conn, prescription);
			
			if(dtoMap.get("doseNoticeDateTimes") != null) {
				Timestamp[] doseNoticeDateTimes = (Timestamp[]) dtoMap.get("doseNoticeDateTimes");
				insertDoseNoticeList(conn, doseNoticeDateTimes, prescriptionId);
			}
			
			// 처방 약이 있다면, medicine_record에 insert 하고, prescription_list의 has_medicine Y로 바꾸기
			if(dtoMap.get("medicine") != null) {
				
			}
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
	}
	
	public void updateVisit(Visit newVisit) {
		Connection conn = template.getConnection();
		
		try {
			//기존 Visit 삭제
			scheduleDao.deleteVisitByCode(conn, newVisit.getVisitNoticeCode());
			//새로둔 Visit 등록
			scheduleDao.insertVisitNoticeWithOriginId(conn, newVisit);
			
			template.commit(conn);
		} catch (Exception e) {
			template.rollback(conn);
			throw e;
		} finally {
			template.close(conn);
		}
	}
	
	private String insertScheduleList(Connection conn, String userCode) {
		scheduleDao.insertScheduleList(conn, userCode);
		return scheduleDao.getCurrentScheduleId(conn);
	}

	private void insertMedicalHistory(Connection conn, Medical medical, String scheduleId) {
		scheduleDao.insertMedicalHistory(conn, medical);
		scheduleDao.updateHasMedicalRecord(conn, scheduleId, "Y");
	}

	private String insertPrescription(Connection conn, Prescription prescription, String scheduleId) {
		scheduleDao.insertPrescriptionList(conn, prescription);
		scheduleDao.updateHasPrescription(conn, scheduleId, "Y");
		return scheduleDao.getCurrentPrescriptionId(conn);
	}
	
	private void insertDoseNoticeList(Connection conn, Timestamp[] doseNoticeDateTimes, String prescriptionId) {
		scheduleDao.insertDoseNotice(conn, doseNoticeDateTimes, prescriptionId);
		scheduleDao.updateHasDoseNotice(conn, prescriptionId);
	}

	

	

	

	

	

	

	

	

	
	
}
