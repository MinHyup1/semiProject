package com.kh.semi.pharmacy.model.dto;

public class Pharmacy {

	private String pharName;// 약국이름
	private String pharLoc;// 약국 위치
	private String pharLat;// 약국 위도
	private String pharLon;// 약국 경도
	private String pharCode;// 약국 ID

	public String getPharName() {
		return pharName;
	}

	public void setPharName(String pharName) {
		this.pharName = pharName;
	}

	public String getPharLoc() {
		return pharLoc;
	}

	public void setPharLoc(String pharLoc) {
		this.pharLoc = pharLoc;
	}

	public String getPharLat() {
		return pharLat;
	}

	public void setPharLat(String pharLat) {
		this.pharLat = pharLat;
	}

	public String getPharLon() {
		return pharLon;
	}

	public void setPharLon(String pharLon) {
		this.pharLon = pharLon;
	}

	public String getPharCode() {
		return pharCode;
	}

	public void setPharCode(String pharCode) {
		this.pharCode = pharCode;
	}

	@Override
	public String toString() {
		return "Pharmacy [pharName=" + pharName + ", pharLoc=" + pharLoc + ", pharLat=" + pharLat + ", pharLon="
				+ pharLon + ", pharCode=" + pharCode + "]";
	}

}
