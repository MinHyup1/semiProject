package com.kh.semi.board.model.dto;

public class CommentDTO {
	private int BNO;
	private int COMMENTLEVEL;
	private int COMMENT_ID;
	private int PARENT_COMMENT_ID;
	private String CONTENT;
	private String NAME;
	private String USER_CODE;
	private String DELETE_AT;
	private String REGDATE;

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public int getBNO() {
		return BNO;
	}

	public void setBNO(int bNO) {
		BNO = bNO;
	}

	public int getCOMMENTLEVEL() {
		return COMMENTLEVEL;
	}

	public void setCOMMENTLEVEL(int cOMMENTLEVEL) {
		COMMENTLEVEL = cOMMENTLEVEL;
	}

	public int getCOMMENT_ID() {
		return COMMENT_ID;
	}

	public void setCOMMENT_ID(int cOMMENT_ID) {
		COMMENT_ID = cOMMENT_ID;
	}

	public int getPARENT_COMMENT_ID() {
		return PARENT_COMMENT_ID;
	}

	public void setPARENT_COMMENT_ID(int pARENT_COMMENT_ID) {
		PARENT_COMMENT_ID = pARENT_COMMENT_ID;
	}

	public String getCONTENT() {
		return CONTENT;
	}

	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}

	public String getUSER_CODE() {
		return USER_CODE;
	}

	public void setUSER_CODE(String uSER_CODE) {
		USER_CODE = uSER_CODE;
	}

	public String getDELETE_AT() {
		return DELETE_AT;
	}

	public void setDELETE_AT(String dELETE_AT) {
		DELETE_AT = dELETE_AT;
	}

	public String getREGDATE() {
		return REGDATE;
	}

	public void setREGDATE(String rEGDATE) {
		REGDATE = rEGDATE;
	}

}
