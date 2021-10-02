package com.kh.semi.medicine.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.kh.semi.common.db.JDBCTemplate;
import com.kh.semi.medicine.model.dao.MedicineDao;
import com.kh.semi.medicine.model.dto.Medicine;

public class MedicineService {

	private MedicineDao medicineDao = new MedicineDao();
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	//이름으로 검색할 경우 db에서 약 정보를 확인해서 가져옴 (리스트로 받아오려고 하려 생각중)
	
	public List<Medicine> selectMedicineByName(String medName) {
		Connection conn = template.getConnection();		
		
		List<Medicine> medicineList = new ArrayList<Medicine>();
		try {			
			medicineList = medicineDao.selectMedicineByName(medName,conn);
		} finally {
			template.close(conn);
		}
		return medicineList; //약정보를 전달
	}

	public int getMedicineInfo(List<Medicine> medicineList) throws Exception {
		Connection conn = template.getConnection();
		int res = 0;
		try {			
			res = medicineDao.getMedicineInfo(medicineList,conn);
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
	
	//[참고] 륜수 수정 10/01 2:46
	public List<String> selectMedNameByNum(List<Integer> medNumList) {
		Connection conn = template.getConnection();
		List<String> medicineList = new ArrayList<String>();
		
		try {
			for (Integer num : medNumList) {
				Medicine medicine = medicineDao.selectMedicineByNum(conn, num);
				medicineList.add(medicine.getMedName());
			}
		} finally {
			template.close(conn);
		}
		return medicineList;
	}
	
	//[참고] 륜수 수정 10/02 21:28
	public String selectMedNameByNum(int medNum) {
		Connection conn = template.getConnection();
		Medicine medicine = null;
		String medName = null;
		
		try {
				medicine = medicineDao.selectMedicineByNum(conn, medNum);
				medName = medicine.getMedName();
		} finally {
			template.close(conn);
		}
		return medName;
	}
	
	
}
