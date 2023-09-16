package com.snipe.apmt.salesmanager.constant;

public enum SalesArticleStatusCode {
	
	NEW(0, "New Article"), VERIFIED(1, "Verified Article"), APPROVED(2, "Approved Article");

	private int code;
	private String desc;

	SalesArticleStatusCode(int code, String desc) {
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
