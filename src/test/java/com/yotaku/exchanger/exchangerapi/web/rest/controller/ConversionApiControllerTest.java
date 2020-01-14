package com.yotaku.exchanger.exchangerapi.web.rest.controller;

import com.yotaku.exchanger.exchangerapi.errorhandling.CustomRestExceptionHandler;
import com.yotaku.exchanger.exchangerapi.service.ExchangeService;
import com.yotaku.exchanger.exchangerapi.service.ExchangeTransactionService;
import com.yotaku.exchanger.exchangerapi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.money.NumberValue;

import static com.yotaku.exchanger.exchangerapi.provider.Common.*;
import static org.javamoney.moneta.spi.DefaultNumberValue.of;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class ConversionApiControllerTest {

	@MockBean
	private UserService userService;
	@MockBean
	private ExchangeService exchangeService;
	@MockBean
	private ExchangeTransactionService exchangeTransactionService;

	private ConversionApiController conversionApiController;

	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);

		conversionApiController = new ConversionApiController(userService, exchangeService, exchangeTransactionService);

		mockMvc = MockMvcBuilders.standaloneSetup(conversionApiController)
				.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				.setControllerAdvice(new CustomRestExceptionHandler()).build();
	}

	@Test
	public void testRate_200Ok() throws Exception {
		NumberValue expectedRate = of(1.3);

		doReturn(expectedRate).when(exchangeService).rate(anyString(), anyString());

		mockMvc.perform(get(RATE_URL)
				.param("from", USD_CODE).param("to", CAD_CODE)
				.with(httpBasic(USERNAME, PASSWORD))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(expectedRate.toString()));
	}
	
	@Test
	public void testRate_400BadRequest() throws Exception {
		NumberValue expectedRate = of(1.3);

		doReturn(expectedRate).when(exchangeService).rate(anyString(), anyString());

		mockMvc.perform(get(RATE_URL)
				.param("to", CAD_CODE)
				.with(httpBasic(USERNAME, PASSWORD))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andDo(print());
		
		mockMvc.perform(get(RATE_URL)
				.param("from", CAD_CODE)
				.with(httpBasic(USERNAME, PASSWORD))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andDo(print());
		
	}

}
