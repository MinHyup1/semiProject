package com.kh.semi.medicine.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.semi.common.db.JDBCTemplate;
import com.kh.semi.medicine.model.dto.Medicine;

public class MedicineDao {
	
	private JDBCTemplate template = JDBCTemplate.getInstance(); //JDBC템플릿 인스턴스 생성

	public List<Medicine> selectMedicineByName(String medName, Connection conn) {
		List<Medicine> medicineList = new ArrayList<Medicine>();
		PreparedStatement pstm = null;
		ResultSet rset = null;
		Medicine medicine = null;
		String columns = "MED_NUM, MED_NAME, MED_EFC, MED_METHOD, MED_WARN, MED_IMG";
		String selectName = "%"+medName+"%";
		String query = "select " + columns +" from MEDICINE where MED_NAME like ?";		
		try {
			pstm = conn.prepareStatement(query);
			pstm.setString(1, selectName);
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				
				medicine = new Medicine();
				medicine.setMedNum(rset.getInt(1));
				medicine.setMedName(rset.getString(2));
				medicine.setMedEfc(rset.getString(3));
				medicine.setMedMethod(rset.getString(4));
				medicine.setMedWarn(rset.getString(5));
				medicine.setMedImg(rset.getString(6));
				medicineList.add(medicine);
							
			}			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			template.close(rset, pstm);
		}
		
		return medicineList;
	}

	public int getMedicineInfo(List<Medicine> medicineList, Connection conn) {
		PreparedStatement pstm = null;
		int res = 0;
		String query = "insert into MEDICINE(MED_NUM, MED_NAME, MED_EFC, MED_METHOD, MED_WARN, MED_IMG)"
				+ " values(?,?,?,?,?,?)";
		
		try {
			
			for(int i = 0; i < medicineList.size(); i++) {
				Medicine medicine = medicineList.get(i);
				pstm = conn.prepareStatement(query);
				pstm.setInt(1, medicine.getMedNum());
				pstm.setString(2, medicine.getMedName());
				pstm.setString(3, medicine.getMedEfc());
				pstm.setString(4, medicine.getMedMethod());
				pstm.setString(5, medicine.getMedWarn());
				pstm.setString(6, medicine.getMedImg());
				
				res = pstm.executeUpdate();
			}
			
		} catch (SQLException e) {
		 	e.printStackTrace();
		}finally {
			template.close(pstm);
		}
		
		return res;
	}
	
	
}
