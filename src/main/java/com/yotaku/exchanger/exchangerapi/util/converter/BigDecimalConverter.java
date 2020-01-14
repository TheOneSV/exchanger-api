package com.yotaku.exchanger.exchangerapi.util.converter;

import javax.money.MonetaryAmount;
import javax.money.NumberValue;
import javax.money.convert.ExchangeRate;
import java.math.BigDecimal;

public class BigDecimalConverter {

    public static BigDecimal from(final ExchangeRate exchangeRate) {
        final NumberValue factor = exchangeRate.getFactor();

        return factor.numberValueExact(BigDecimal.class);
    }

    public static BigDecimal from(final MonetaryAmount monetaryAmount) {
        return monetaryAmount.getNumber().numberValueExact(BigDecimal.class);
    }
}
