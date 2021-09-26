package com.kh.semi.schedule.model.dto;

import java.sql.Date;

public class Prescription {

	private String prescriptionId;
	private String scheduleId;
	private String prescriptionName;
	private Date startDate;
	private Date endDate;
	private String pharmCode;
	private String hasMedicine;
	private int timesPerDay;
	private int hasDoseNotice;
	private Date regDate;
	
	public Prescription() {
		// TODO Auto-generated constructor stub
	}

	public String getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(String prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getPrescriptionName() {
		return prescriptionName;
	}

	public void setPrescriptionName(String prescriptionName) {
		this.prescriptionName = prescriptionName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getPharmCode() {
		return pharmCode;
	}

	public void setPharmCode(String pharmCode) {
		this.pharmCode = pharmCode;
	}

	public String getHasMedicine() {
		return hasMedicine;
	}

	public void setHasMedicine(String hasMedicine) {
		this.hasMedicine = hasMedicine;
	}

	public int getTimesPerDay() {
		return timesPerDay;
	}

	public void setTimesPerDay(int timesPerDay) {
		this.timesPerDay = timesPerDay;
	}

	public int getHasDoseNotice() {
		return hasDoseNotice;
	}

	public void setHasDoseNotice(int hasDoseNotice) {
		this.hasDoseNotice = hasDoseNotice;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "Prescription [prescriptionId=" + prescriptionId + ", scheduleId=" + scheduleId + ", prescriptionName="
				+ prescriptionName + ", startDate=" + startDate + ", endDate=" + endDate + ", pharmCode=" + pharmCode
				+ ", hasMedicine=" + hasMedicine + ", timesPerDay=" + timesPerDay + ", hasDoseNotice=" + hasDoseNotice
				+ ", regDate=" + regDate + "]";
	}
	
}
