package com.snipe.apmt.constants;

import java.util.HashMap;
import java.util.Map;

public enum Role {

  
	ADMIN("1"), VERIFICATION_MANAGER("2"),SALES_MANAGER("3"), STUDENT("4"), PURCHASER("5"),EMPLOYEE("6");
	
	private static Map<String, Role> codeToRoleMapping;
	private String code;

	private Role(String c) {
		code = c;
	}

	public String getCode() {
		return code;
	}

	public static Role getRole(String roleId) {
		if (codeToRoleMapping == null) {
			initMapping();
		}
		return codeToRoleMapping.get(roleId);
	}

	private static void initMapping() {
		codeToRoleMapping = new HashMap<String, Role>();
		for (Role s : values()) {
			codeToRoleMapping.put(s.code, s);
		}
	}

}
