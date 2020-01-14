package com.yotaku.exchanger.exchangerapi.provider;

import com.yotaku.exchanger.exchangerapi.domain.Role;
import com.yotaku.exchanger.exchangerapi.util.converter.UserRole;

public class RoleProvider {
	
	private static final Role ADMIN = new Role();
	
	static {
		ADMIN.setName(UserRole.ADMIN);
	}
	
	public static final Role roleAdmin() {
		return ADMIN;
	}
}
