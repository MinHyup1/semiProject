package com.kh.semi.medicine.model.service;

import java.sql.Connection;

import com.kh.semi.common.db.JDBCTemplate;
import com.kh.semi.medicine.model.dao.MedicineDao;
import com.kh.semi.medicine.model.dto.Medicine;

public class MedicineService {

	private MedicineDao medicineDao = new MedicineDao();
	private JDBCTemplate template = JDBCTemplate.getInstance();
	
	//이름으로 검색할 경우 db에서 약 정보를 확인해서 가져옴 (리스트로 받아오려고 하려 생각중)
	
	public Medicine selectMedicineByName(String medName) {
		Connection conn = template.getConnection();
		Medicine medicine = null;
		try {			
			if(medicineDao.selectMedicineByName(medName,conn) == null) {
				//이 중간에 db에 데이터가 없을경우 api로 접속해서 해당 약정보를 db로 가져오기 (매일 업데이트하기엔 100개가 한번 검색이라서 계속 검색하기 힘듬)
				medicine = medicineDao.getMedicineInfo(medName,conn);
			}
			medicine = medicineDao.selectMedicineByName(medName,conn);
		} finally {
			template.close(conn);
		}
		return medicine; //약정보를 전달
	}
	
	
	
	
	
	
}
