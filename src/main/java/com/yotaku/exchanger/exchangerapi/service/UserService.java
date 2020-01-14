package com.yotaku.exchanger.exchangerapi.service;

import com.yotaku.exchanger.exchangerapi.domain.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    User findByUsernameIgnoreCase(String username);

}
