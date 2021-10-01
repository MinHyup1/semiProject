package com.kh.semi.board.model.dto;

public class BoardDTO {
	private int RN;
	private int BNO;
	private String BBS_TYPE;
	private String TITLE;
	private String CONTENT;
	private String REGDATE;
	private String USER_CODE;
	private String ID;
	private String NAME;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public int getRN() {
		return RN;
	}

	public void setRN(int rN) {
		RN = rN;
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

	public String getTITLE() {
		return TITLE;
	}

	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}

	public String getCONTENT() {
		return CONTENT;
	}

	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}

	public String getREGDATE() {
		return REGDATE;
	}

	public void setREGDATE(String rEGDATE) {
		REGDATE = rEGDATE;
	}

	public String getUSER_CODE() {
		return USER_CODE;
	}

	public void setUSER_CODE(String uSER_CODE) {
		USER_CODE = uSER_CODE;
	}

	@Override
	public String toString() {
		return "BoardDTO [RN=" + RN + ", BNO=" + BNO + ", BBS_TYPE=" + BBS_TYPE + ", TITLE=" + TITLE + ", CONTENT="
				+ CONTENT + ", REGDATE=" + REGDATE + ", USER_CODE=" + USER_CODE + ", ID=" + ID + ", NAME=" + NAME + "]";
	}

	
	
}
