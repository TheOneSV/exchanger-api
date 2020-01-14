package com.yotaku.exchanger.exchangerapi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.yotaku.exchanger.exchangerapi.service.UserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserService userService;

	@Autowired
	public UserDetailsServiceImpl(final UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public UserDetails loadUserByUsername(final String username) {
		return userService.findByUsernameIgnoreCase(username);
	}

}
