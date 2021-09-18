package com.kh.semi.member.model.dto;


import java.sql.Date;


public class Member {
	private String userCode;
	private String id;
	private String password;
	private String name;
	private String phone;
	private String address;
	private String email;
	private String gender;
	
	
	public Member() {
		// TODO Auto-generated constructor stub
	}


	public String getUserCode() {
		return userCode;
	}


	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	@Override
	public String toString() {
		return "Member [userCode=" + userCode + ", id=" + id + ", password=" + password + ", name=" + name + ", phone="
				+ phone + ", address=" + address + ", email=" + email + ", gender=" + gender + "]";
	}

	

}








