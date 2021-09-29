package com.kh.semi.medicine.model.dto;

public class Medicine {

	private int medNum;
	private String medName;
	private String medEfc;
	private String medMethod;
	private String medWarn;
	private String medImg;
	
	
	public int getMedNum() {
		return medNum;
	}
	public void setMedNum(int medNum) {
		this.medNum = medNum;
	}
	public String getMedName() {
		return medName;
	}
	public void setMedName(String medName) {
		this.medName = medName;
	}
	public String getMedEfc() {
		return medEfc;
	}
	public void setMedEfc(String medEfc) {
		this.medEfc = medEfc;
	}
	public String getMedMethod() {
		return medMethod;
	}
	public void setMedMethod(String medMethod) {
		this.medMethod = medMethod;
	}
	public String getMedWarn() {
		return medWarn;
	}
	public void setMedWarn(String medWarn) {
		this.medWarn = medWarn;
	}
	public String getMedImg() {
		return medImg;
	}
	public void setMedImg(String medImg) {
		this.medImg = medImg;
	}
	
	@Override
	public String toString() {
		return "Medicine [medNum=" + medNum + ", medName=" + medName + ", medEfc=" + medEfc + ", medMethod=" + medMethod
				+ ", medWarn=" + medWarn + ", medImg=" + medImg + "]";
	}
	
	
}
