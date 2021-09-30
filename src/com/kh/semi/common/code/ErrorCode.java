package com.kh.semi.common.code;

public enum ErrorCode {
	
	DATABASE_ACCESS_ERROR("데이터베이스와 통신 중 에러가 발생하였습니다."),
	VALIDATOR_FAIL_ERROR("부적절한 양식의 데이터 입니다."),
	MAIL_SEND_FAIL_ERROR("이메일 발송 중 에러가 발생하였습니다"),
	HTTP_CONNECT_ERROR("HTTP 통신 중 에러가 발생하였습니다."),
	AUTENTICATION_FAILED_ERROR("유효하지 않은 인증입니다."),
	UNAUTORIZED_PAGE_ERROR("접근 권한이 없는 페이지입니다."),
	FAILED_FILE_UPLOAD_ERROR("파일업로드에 실패하였습니다."),
	NOT_MEMBER_ERROR("로그인 후 사용해주세요.", "/member/loginPage"),
	FAILED_GET_SCHEDULE("스케줄을 가져올 수 없습니다.\r문의 후 사용해주세요.", "/index"),
	API_DATA_ERROR("해당 정보는 없습니다"),
	REDIRECT("");
	

	public final String MESSAGE;
	public String URL;
	
	private ErrorCode(String msg) {
		this.MESSAGE = msg;
		this.URL = "/index";
	}
	
	private ErrorCode(String msg, String url) {
		this.MESSAGE = msg;
		this.URL = url;
	}

	public ErrorCode setURL(String url) {
		URL = url;
		return this;
	}
	
	
}
