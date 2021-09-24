package com.kh.semi.hospitalInfo.model.dto;

public class HospitalInfo {

	
	private int hospCode; 		//병원 번호 (시퀀스)
	private String hospTreat;	//진료 과목 코드
	private String hospTell;	//전화번호
	private String hospName;	//병원 이름
	private String hospUrl;		//홈페이지 
	private String address;		//주소
	private String xPos;		//X 좌표
	private String yPos;		//Y 좌표
	public int getHospCode() {
		return hospCode;
	}
	public void setHospCode(int hospCode) {
		this.hospCode = hospCode;
	}
	public String getHospTreat() {
		return hospTreat;
	}
	public void setHospTreat(String hospTreat) {
		this.hospTreat = hospTreat;
	}
	public String getHospTell() {
		return hospTell;
	}
	public void setHospTell(String hospTell) {
		this.hospTell = hospTell;
	}
	public String getHospName() {
		return hospName;
	}
	public void setHospName(String hospName) {
		this.hospName = hospName;
	}
	public String getHospUrl() {
		return hospUrl;
	}
	public void setHospUrl(String hospUrl) {
		this.hospUrl = hospUrl;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getxPos() {
		return xPos;
	}
	public void setxPos(String xPos) {
		this.xPos = xPos;
	}
	public String getyPos() {
		return yPos;
	}
	public void setyPos(String yPos) {
		this.yPos = yPos;
	}
	@Override
	public String toString() {
		return "HospitalInfo [hospCode=" + hospCode + ", hospTreat=" + hospTreat + ", hospTell=" + hospTell
				+ ", hospName=" + hospName + ", hospUrl=" + hospUrl + ", address=" + address + ", xPos=" + xPos
				+ ", yPos=" + yPos + "]";
	}
	
	
	
	
}
