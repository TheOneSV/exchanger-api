package com.yotaku.exchanger.exchangerapi.service.impl;

import com.yotaku.exchanger.exchangerapi.domain.User;
import com.yotaku.exchanger.exchangerapi.repository.UserRepository;
import com.yotaku.exchanger.exchangerapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepo;

	@Autowired
	public UserServiceImpl(final UserRepository userRepo) {
		Objects.requireNonNull(userRepo, "userRepo must not be null");
		this.userRepo = userRepo;
	}

	@Override
	@Transactional
	public User findByUsernameIgnoreCase(final String username) {
		Optional<User> user = userRepo.findByUsernameIgnoreCase(username);
				return user.orElseThrow(() -> new UsernameNotFoundException(username));
	}

}
