package com.kh.semi.board.model.dto;

public class FileDTO {
	private int FILE_NO;
	private int BNO;
	private String BBS_TYPE;
	private String ORG_FILE_NAME;
	private String STORED_FILE_NAME;
	private int FILE_SIZE;
	private String REGDATE;

	public int getFILE_NO() {
		return FILE_NO;
	}

	public void setFILE_NO(int fILE_NO) {
		FILE_NO = fILE_NO;
	}

	public int getBNO() {
		return BNO;
	}

	public void setBNO(int bNO) {
		BNO = bNO;
	}

	public String getBBS_TYPE() {
		return BBS_TYPE;
	}

	public void setBBS_TYPE(String bBS_TYPE) {
		BBS_TYPE = bBS_TYPE;
	}

	public String getORG_FILE_NAME() {
		return ORG_FILE_NAME;
	}

	public void setORG_FILE_NAME(String oRG_FILE_NAME) {
		ORG_FILE_NAME = oRG_FILE_NAME;
	}

	public String getSTORED_FILE_NAME() {
		return STORED_FILE_NAME;
	}

	public void setSTORED_FILE_NAME(String sTORED_FILE_NAME) {
		STORED_FILE_NAME = sTORED_FILE_NAME;
	}

	public int getFILE_SIZE() {
		return FILE_SIZE;
	}

	public void setFILE_SIZE(int fILE_SIZE) {
		FILE_SIZE = fILE_SIZE;
	}

	public String getREGDATE() {
		return REGDATE;
	}

	public void setREGDATE(String rEGDATE) {
		REGDATE = rEGDATE;
	}

}
