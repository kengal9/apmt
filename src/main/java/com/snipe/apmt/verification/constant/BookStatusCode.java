package com.snipe.apmt.verification.constant;

public enum BookStatusCode {
	
	NEW(0, "New Book"), VERIFIED(1, "Verified Book"), APPROVED(2, "Approved Book"),
	REJECTED(3, "Rejected Book");

	private int code;
	private String desc;

	BookStatusCode(int code, String desc) {
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
