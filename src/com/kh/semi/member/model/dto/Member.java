package com.kh.semi.member.model.dto;


import java.sql.Date;

//DTO(DATA TRANSFER OBJECT)
//���������۰�ü
//�����ͺ��̽��κ��� ��� �� �����͸� service(�����Ͻ�����)���� ������ ���� ����ϴ� ��ü
//�����Ͻ� ������ �����ϰ� ���� ����, �����ϰ� ������ ���۸��� ���� ���Ǵ� ��ü
//getter/setter, equals, hashCode, toString �޼��常�� ������.

//*** ����
//DTO�� ����� ģ����
//DOMAIN OBJECT, VALUE OBJECT(VO), ENTITY, BEAN

//DTO�� ����(JAVA BEAN �Ծ�)
//1. ��� �ʵ庯���� PRIVATE ó��
//2. ��� �ʵ庯���� GETTER/SETTER �޼��带 ������ �Ѵ�.
//3. �ݵ�� �⺻�����ڰ� ������ ��(�Ű������� �ִ� �����ڰ� �ִ���, �⺻�����ڰ� �־���Ѵ�.)

//����Ŭ - �ڹ� Ÿ�� ����
//CHAR, VARCHAR2 -> String
//DATE -> java.util.Date, java.sql.Date
//Number -> int, double
public class Member {
	
	private String userId;
	private String password;
	private String grade;
	private String email;
	private String tell;
	private Date regDate;
	private Date rentableDate;
	private int isLeave;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTell() {
		return tell;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getRentableDate() {
		return rentableDate;
	}

	public void setRentableDate(Date rentableDate) {
		this.rentableDate = rentableDate;
	}

	public int getIsLeave() {
		return isLeave;
	}

	public void setIsLeave(int isLeave) {
		this.isLeave = isLeave;
	}

	@Override
	public String toString() {
		return "Member [userId=" + userId + ", password=" + password + ", grade=" + grade + ", email=" + email
				+ ", tell=" + tell + ", regDate=" + regDate + ", rentableDate=" + rentableDate + ", isLeave=" + isLeave
				+ "]";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}








