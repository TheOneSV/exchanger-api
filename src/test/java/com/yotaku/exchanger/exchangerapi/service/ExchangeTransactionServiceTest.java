package com.yotaku.exchanger.exchangerapi.service;

import static com.yotaku.exchanger.exchangerapi.provider.ExchangeTransactionProvider.usdToEur;
import static com.yotaku.exchanger.exchangerapi.provider.ExchangeTransactionProvider.transactionsList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.yotaku.exchanger.exchangerapi.errorhandling.CustomRestExceptionHandler;
import com.yotaku.exchanger.exchangerapi.web.rest.controller.ConversionApiController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.yotaku.exchanger.exchangerapi.domain.ExchangeTransaction;
import com.yotaku.exchanger.exchangerapi.repository.ExchangeTransactionRepository;
import com.yotaku.exchanger.exchangerapi.service.impl.ExchangeTransactionServiceImpl;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(SpringExtension.class)
public class ExchangeTransactionServiceTest {

	@Mock
	private ExchangeTransactionRepository exchangeTransactionRepositoryMock;

	private ExchangeTransactionService exchangeTransactionService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		exchangeTransactionService = new ExchangeTransactionServiceImpl(exchangeTransactionRepositoryMock);
	}

	@Test
	public void testSaveSaved() {
		ExchangeTransaction expected = usdToEur();

		doReturn(expected).when(exchangeTransactionRepositoryMock).save(any(ExchangeTransaction.class));

		assertThat(exchangeTransactionService.save(expected)).isSameAs(expected);
	}

	@Test
	public void testFindAllByDate() {
		LocalDate today = LocalDate.now();
		List<ExchangeTransaction> transactionsList = transactionsList();	
		Page<ExchangeTransaction> resultPage = new PageImpl<ExchangeTransaction>(transactionsList);

		doReturn(resultPage).when(exchangeTransactionRepositoryMock).findAllByTransactionDateTimeBetween(
				any(LocalDateTime.class), any(LocalDateTime.class), any(Pageable.class));

		assertThat(exchangeTransactionService.findAllByDate(today, Pageable.unpaged())).isEqualTo(resultPage);
	}

}
