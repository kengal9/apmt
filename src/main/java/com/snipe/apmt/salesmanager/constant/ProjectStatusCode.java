package com.snipe.apmt.salesmanager.constant;

public enum ProjectStatusCode {
	NEW(0, "New Project"), VERIFIED(1, "Verified Project"), APPROVED(2, "Approved Project");

	private int code;
	private String desc;

	ProjectStatusCode(int code, String desc) {
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
