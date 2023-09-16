package com.snipe.apmt.salesmanager.constant;

public enum BookStatusCode {
	
 NEW(0,"New Book"),VERIFIED(1,"VERIFIED BOOK"),APPROVED(2,"APPROVED BOOK");
	
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
