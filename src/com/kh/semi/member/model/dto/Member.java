package com.kh.semi.member.model.dto;

import java.sql.Date;

//DTO(DATA TRANSFER OBJECT)
//데이터 전송 객체
//데이터베이스로부터 얻어 온 데이터를 service(비지니스로직)으로 보내기 위해 사용하는 객체
//비지니스 로직을 포함하고 있지 않은, 순수하게 데이터 전송만을 위해 사용되는 객체
//getter / setter, equals, hashCode, toString 메서드만을 가진다.

//*** 참고
//DTO와 비슷한 친구들
//DOMAIN OBJECT, VALUE OBJECT(VO), ENTITY, BEAN

//DTO의 조건(JAVA BEAN 규약)
//1. 모든 필드변수는 private 처리
//2. 모든 필드변수는 getter/setter 메서드를 가져야 한다.
//3. 반드시 기본생성자가 존재할 것(매개변수가 있는 생성자가 있더라도, 기본생성자가 있어야한다.)

//오라클 - 자바 타입 매핑
//CHAR, VARCHAR2 -> String
//DATA -> java.util.Date, java.sql.Date
//Number -> int, double
public class Member {

	/*
	 * USER_ID        VARCHAR2(36 CHAR) 
	 * PASSWORD       VARCHAR2(60 CHAR) 
	 * EMAIL          VARCHAR2(50 CHAR)
	 * GRADE          CHAR(4 CHAR) 
	 * TELL           VARCHAR2(15 CHAR) 
	 * REG_DATE DATE
	 * RENTABLE_DATE DATE
	 * IS_LEAVE       NUMBER
	 */
	
	private String userCode;
	private String id;
	private String password;
	private String name;
	private String nick;
	private String phone;
	private String address;
	private String email;
	private String gender;
	private int kakaoNum;
	private int token;


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

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
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

	public void setAddress(String postCode, String address1, String address2) {
		this.address = postCode + " " + address1 +  " " + address2;
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
	
	public int getKakaoNum() {
		return kakaoNum;
	}

	public void setKakaoNum(int kakaoNum) {
		this.kakaoNum = kakaoNum;
	}
	
	

	public int getToken() {
		return token;
	}

	public void setToken(int token) {
		this.token = token;
	}

	@Override
	public String toString() {
		return "Member [userCode=" + userCode + ", id=" + id + ", password=" + password + ", name=" + name + ", nick="
				+ nick + ", phone=" + phone + ", address=" + address + ", email=" + email + ", gender=" + gender
				+ ", kakaoNum=" + kakaoNum + ", token=" + token + "]";
	}




	

	


}
