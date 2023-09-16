package com.snipe.apmt.verification.constant;

public enum ArticleStatusCode {
	
	NEW(0, "New Article"), VERIFIED(1, "Verified Article"), APPROVED(2, "Approved Article"),
	REJECTED(3, "Rejected Article");

	private int code;
	private String desc;

	ArticleStatusCode(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	

}
