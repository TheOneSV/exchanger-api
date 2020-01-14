package com.yotaku.exchanger.exchangerapi.service.impl;

import com.yotaku.exchanger.exchangerapi.domain.Account;
import com.yotaku.exchanger.exchangerapi.domain.ExchangeTransaction;
import com.yotaku.exchanger.exchangerapi.domain.Exchange;
import com.yotaku.exchanger.exchangerapi.service.ExchangeService;
import com.yotaku.exchanger.exchangerapi.service.ExchangeTransactionService;
import com.yotaku.exchanger.exchangerapi.util.converter.BigDecimalConverter;
import org.javamoney.moneta.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.money.MonetaryAmount;
import javax.money.NumberValue;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.ExchangeRate;
import javax.money.convert.ExchangeRateProvider;
import java.math.BigDecimal;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    private final ExchangeTransactionService exchangeTransactionService;
    private final ExchangeRateProvider exchangeRateProvider;

    @Autowired
    public ExchangeServiceImpl(
            final ExchangeTransactionService exchangeTransactionService,
            final ExchangeRateProvider exchangeRateProvider) {

        this.exchangeTransactionService = exchangeTransactionService;
        this.exchangeRateProvider = exchangeRateProvider;
    }

    @Override
    public NumberValue rate(final String sourceCurrency, final String targetCurrency) {

        final ExchangeRate exchangeRate = exchangeRateProvider.getExchangeRate(sourceCurrency, targetCurrency);
        return exchangeRate.getFactor();
    }

    @Override
    public Exchange exchange(final String sourceCurrencyCode, final String targetCurrencyCode, final BigDecimal amount) {

        final MonetaryAmount sourceAmount = Money.of(amount, sourceCurrencyCode);
        final CurrencyConversion targetConversion = exchangeRateProvider.getCurrencyConversion(targetCurrencyCode);

        final MonetaryAmount targetMonetaryAmount = sourceAmount.with(targetConversion);
        final BigDecimal targetAmount = BigDecimalConverter.from(targetMonetaryAmount);

        final ExchangeRate rate = targetConversion.getExchangeRate(sourceAmount);
        final BigDecimal exchangeRate = BigDecimalConverter.from(rate);

        return Exchange.builder()
                .sourceCurrency(sourceCurrencyCode)
                .targetCurrency(targetCurrencyCode)
                .exchangeRate(exchangeRate)
                .sourceAmount(amount)
                .targetAmount(targetAmount)
                .build();
    }

    @Override
    public ExchangeTransaction exchangeWithTransaction(
            final String sourceCurrencyCode,
            final String targetCurrencyCode,
            final BigDecimal amount,
            final Account account) {

        Exchange exchange = exchange(sourceCurrencyCode, targetCurrencyCode, amount);

        return exchangeWithTransaction(exchange, account);
    }

    @Override
    public ExchangeTransaction exchangeWithTransaction(final Exchange exchange, final Account account) {

        final ExchangeTransaction exchangeTransaction = new ExchangeTransaction();

        exchangeTransaction.setTransactionDateTime(exchange.getTransactionDateTime());
        exchangeTransaction.setSourceCurrency(exchange.getSourceCurrency());
        exchangeTransaction.setTargetCurrency(exchange.getTargetCurrency());
        exchangeTransaction.setExchangeRate(exchange.getExchangeRate());
        exchangeTransaction.setSourceAmount(exchange.getSourceAmount());
        exchangeTransaction.setTargetAmount(exchange.getTargetAmount());

        exchangeTransaction.setAccount(account);

        exchangeTransactionService.save(exchangeTransaction);

        return exchangeTransaction;
    }

}
