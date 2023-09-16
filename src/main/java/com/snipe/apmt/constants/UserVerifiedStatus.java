package com.snipe.apmt.constants;

public enum UserVerifiedStatus {

    VERIFIED(1, "UserVerified"), NOTVERIFIED(2, "NotVerified");
	

	UserVerifiedStatus(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
	private int code;
	private String desc;

	public int getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

}
