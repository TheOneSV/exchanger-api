package com.yotaku.exchanger.exchangerapi.service;

import com.yotaku.exchanger.exchangerapi.domain.Account;
import com.yotaku.exchanger.exchangerapi.domain.ExchangeTransaction;
import com.yotaku.exchanger.exchangerapi.domain.Exchange;

import javax.money.NumberValue;
import java.math.BigDecimal;

public interface ExchangeService {

    NumberValue rate(String sourceCurrency, String targetCurrency);

    Exchange exchange(String sourceCurrencyCode, String targetCurrencyCode, BigDecimal amount);

    ExchangeTransaction exchangeWithTransaction(String sourceCurrencyCode, String targetCurrencyCode, BigDecimal amount, Account account);

    ExchangeTransaction exchangeWithTransaction(Exchange exchange, Account account);

}
