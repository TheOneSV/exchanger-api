package com.yotaku.exchanger.exchangerapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.money.convert.ExchangeRateProvider;
import javax.money.convert.MonetaryConversions;

@Configuration
public class ServiceConfig {

    private static final String EXCHANGE_RATE_PROVIDER = "ECB";

    @Bean
    public ExchangeRateProvider exchangeRateProvider() {
        return MonetaryConversions.getExchangeRateProvider(EXCHANGE_RATE_PROVIDER);
    }
}