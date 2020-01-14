package com.yotaku.exchanger.exchangerapi.provider;

import static com.yotaku.exchanger.exchangerapi.provider.AccountProvider.adminAccount;
import static com.yotaku.exchanger.exchangerapi.provider.Common.CAD_CODE;
import static com.yotaku.exchanger.exchangerapi.provider.Common.EURO_CODE;
import static com.yotaku.exchanger.exchangerapi.provider.Common.USD_CODE;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.List;

import com.yotaku.exchanger.exchangerapi.domain.Account;
import com.yotaku.exchanger.exchangerapi.domain.ExchangeTransaction;

public class ExchangeTransactionProvider {

	private static final ExchangeTransaction USD_TO_EUR = new ExchangeTransaction();
	private static final ExchangeTransaction USD_TO_CAD = new ExchangeTransaction();
	private static final List<ExchangeTransaction> TRANSACTIONS_LIST = List.of(USD_TO_EUR, USD_TO_CAD);

	static {
		setDetails(USD_TO_EUR, adminAccount(), LocalDateTime.now(), Currency.getInstance(USD_CODE),
				Currency.getInstance(EURO_CODE), BigDecimal.valueOf(0.9D), BigDecimal.valueOf(10L),
				BigDecimal.valueOf(9L));
		setDetails(USD_TO_EUR, adminAccount(), LocalDateTime.now(), Currency.getInstance(USD_CODE),
				Currency.getInstance(CAD_CODE), BigDecimal.valueOf(1.3D), BigDecimal.valueOf(10L),
				BigDecimal.valueOf(13L));

	}

	public static final ExchangeTransaction usdToEur() {
		return USD_TO_EUR;
	}
	
	public static final ExchangeTransaction usdToCad() {
		return USD_TO_CAD;
	}
	
	public static final List<ExchangeTransaction> transactionsList() {
		return TRANSACTIONS_LIST;
	}

	public static final ExchangeTransaction setDetails(ExchangeTransaction transaction, Account account,
			LocalDateTime transactionDateTime, Currency sourceCurrency, Currency targetCurrency,
			BigDecimal exchangeRate, BigDecimal sourceAmount, BigDecimal targetAmount) {

		transaction.setAccount(account);
		transaction.setTransactionDateTime(transactionDateTime);
		transaction.setSourceCurrency(sourceCurrency);
		transaction.setTargetCurrency(targetCurrency);
		transaction.setExchangeRate(exchangeRate);
		transaction.setSourceAmount(sourceAmount);
		transaction.setTargetAmount(targetAmount);

		return transaction;
	}

}
