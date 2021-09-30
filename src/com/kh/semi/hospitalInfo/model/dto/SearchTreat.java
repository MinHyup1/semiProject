package com.kh.semi.hospitalInfo.model.dto;

public class SearchTreat {

	private int treatIdx;
	private int hospCode;
	private String treatCode;
	
	public SearchTreat() {
		// TODO Auto-generated constructor stub
	}

	public int getTreatIdx() {
		return treatIdx;
	}

	public void setTreatIdx(int treatIdx) {
		this.treatIdx = treatIdx;
	}

	public int getHospCode() {
		return hospCode;
	}

	public void setHospCode(int hospCode) {
		this.hospCode = hospCode;
	}

	public String getTreatCode() {
		return treatCode;
	}

	public void setTreatCode(String treatCode) {
		this.treatCode = treatCode;
	}

	@Override
	public String toString() {
		return "SearchTreat [treatIdx=" + treatIdx + ", hospCode=" + hospCode + ", treatCode=" + treatCode + "]";
	}
	
	
	
	
}
