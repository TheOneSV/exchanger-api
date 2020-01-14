package com.yotaku.exchanger.exchangerapi.provider;

import static com.yotaku.exchanger.exchangerapi.provider.Common.*;
import static com.yotaku.exchanger.exchangerapi.provider.RoleProvider.roleAdmin;
import static com.yotaku.exchanger.exchangerapi.provider.AccountProvider.adminAccount;

import java.util.List;

import com.yotaku.exchanger.exchangerapi.domain.User;

public class UserProvider {
	
	private static final User USER_ADMIN = new User();
	
	static {
		USER_ADMIN.setName(NAME);
		USER_ADMIN.setUsername(USERNAME);
		USER_ADMIN.setPassword(PASSWORD);
		USER_ADMIN.setRoles(List.of(roleAdmin()));
		USER_ADMIN.setAccount(adminAccount());
	}
	
	public static final User userADMIN() {
		return USER_ADMIN;
	}
}
