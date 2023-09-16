package com.snipe.apmt.ecommerce.constant;

public enum CartStatusCode {
	
 CART(0,"IN  CART"),ORDER(1,"ORDER PLACED"),PAYMENT(2,"PAYMENT DONE");
	
private int code;
private String desc;

CartStatusCode(int code, String desc) {
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
