package com.yotaku.exchanger.exchangerapi.provider;

import com.yotaku.exchanger.exchangerapi.domain.Account;
import static com.yotaku.exchanger.exchangerapi.provider.UserProvider.userADMIN;

public class AccountProvider {
	
	private static final Account ADMIN_ACCOUNT = new Account();
	
	static {
		ADMIN_ACCOUNT.setUser(userADMIN());
	}
	
	public static final Account adminAccount() {
		return ADMIN_ACCOUNT;
	}
}
