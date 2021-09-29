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
		Medicine medicine = null;
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
	
	
	
	
	
	
}
