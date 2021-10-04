package com.kh.semi.common.code;

public enum Config {
	//DOMAIN("http://pclass.ga")
	DOMAIN("http://localhost:9090"),
	COMPANY_EMAIL("medibook90@gmail.com"),
	STMP_AUTHENTICATION_ID("medibook90@gmail.com"),
	STMP_AUTHENTICATION_PASSWORD("Qwer1234qwer"),
	//UPLOAD_PATH("C:\\CODE\\upload\\")
	UPLOAD_PATH("C:\\CODE\\upload\\");
	
	public final String DESC;
	
	private Config(String desc) {
		this.DESC = desc;
	
	}
}
