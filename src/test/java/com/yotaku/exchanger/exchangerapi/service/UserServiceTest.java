package com.yotaku.exchanger.exchangerapi.service;

import static com.yotaku.exchanger.exchangerapi.provider.Common.USERNAME;
import static com.yotaku.exchanger.exchangerapi.provider.UserProvider.userADMIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

import java.util.Optional;

import com.yotaku.exchanger.exchangerapi.service.impl.ExchangeTransactionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.yotaku.exchanger.exchangerapi.domain.User;
import com.yotaku.exchanger.exchangerapi.repository.UserRepository;
import com.yotaku.exchanger.exchangerapi.service.impl.UserServiceImpl;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepositoryMock;

	private UserService userService = new UserServiceImpl(userRepositoryMock);

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		userService = new UserServiceImpl(userRepositoryMock);
	}

	@Test
	public void testFindByUsernameIgnoreCaseFound() {
		mockUserRepositoryFindByUsernameIgnoreCase(Optional.of(userADMIN()));

		assertThat(userService.findByUsernameIgnoreCase(USERNAME)).isSameAs(userADMIN());
	}

	@Test
	public void testFindByUsernameIgnoreCaseNotFound() {
		mockUserRepositoryFindByUsernameIgnoreCase(Optional.empty());

		Exception exception = assertThrows(UsernameNotFoundException.class,
				() -> userService.findByUsernameIgnoreCase(USERNAME));

		assertThat(exception.getMessage()).contains(USERNAME);
	}

	private void mockUserRepositoryFindByUsernameIgnoreCase(Optional<User> result) {
		doReturn(result).when(userRepositoryMock).findByUsernameIgnoreCase(anyString());
	}

}
