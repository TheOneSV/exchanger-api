package com.yotaku.exchanger.exchangerapi.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;
import java.util.Objects;

public class Exchange {

    private final LocalDateTime transactionDateTime;

    private final Currency sourceCurrency;
    private final Currency targetCurrency;

    private final BigDecimal exchangeRate;
    private final BigDecimal sourceAmount;
    private final BigDecimal targetAmount;

    private Exchange(final ExchangeResultBuilder builder) {
        this.transactionDateTime = builder.transactionDateTime;
        this.sourceCurrency = builder.sourceCurrency;
        this.targetCurrency = builder.targetCurrency;
        this.exchangeRate = builder.exchangeRate;
        this.sourceAmount = builder.sourceAmount;
        this.targetAmount = builder.targetAmount;
    }

    public static ExchangeResultBuilder builder(final LocalDateTime transactionDateTime) {
        return new ExchangeResultBuilder(transactionDateTime);
    }

    public static ExchangeResultBuilder builder() {
        return new ExchangeResultBuilder();
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public Currency getSourceCurrency() {
        return sourceCurrency;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public BigDecimal getSourceAmount() {
        return sourceAmount;
    }

    public BigDecimal getTargetAmount() {
        return targetAmount;
    }

    public static class ExchangeResultBuilder {
        private LocalDateTime transactionDateTime;

        private Currency sourceCurrency;
        private Currency targetCurrency;

        private BigDecimal exchangeRate;
        private BigDecimal sourceAmount;
        private BigDecimal targetAmount;

        private ExchangeResultBuilder(LocalDateTime transactionDateTime) {
            this.transactionDateTime = transactionDateTime;
        }

        private ExchangeResultBuilder() {}

        public ExchangeResultBuilder sourceCurrency(Currency sourceCurrency) {
            this.sourceCurrency = sourceCurrency;
            return this;
        }
        public ExchangeResultBuilder sourceCurrency(String sourceCurrencyCode) {
            this.sourceCurrency = Currency.getInstance(sourceCurrencyCode);
            return this;
        }

        public ExchangeResultBuilder targetCurrency(Currency targetCurrency) {
            this.targetCurrency = targetCurrency;
            return this;
        }
        public ExchangeResultBuilder targetCurrency(String targetCurrencyCode) {
            this.targetCurrency = Currency.getInstance(targetCurrencyCode);
            return this;
        }

        public ExchangeResultBuilder exchangeRate(BigDecimal exchangeRate) {
            this.exchangeRate = exchangeRate;
            return this;
        }

        public ExchangeResultBuilder sourceAmount(BigDecimal sourceAmount) {
            this.sourceAmount = sourceAmount;
            return this;
        }

        public ExchangeResultBuilder targetAmount(BigDecimal targetAmount) {
            this.targetAmount = targetAmount;
            return this;
        }

        public Exchange build() {
            transactionDateTime = LocalDateTime.now();

            validate();

            return new Exchange(this);
        }

        private void validate() {
            Objects.requireNonNull(transactionDateTime);
            Objects.requireNonNull(sourceCurrency);
            Objects.requireNonNull(targetCurrency);

            validateBigDecimal(exchangeRate);
            validateBigDecimal(sourceAmount);
            validateBigDecimal(targetAmount);
        }

        private void validateBigDecimal(BigDecimal bigDecimal) {
            Objects.requireNonNull(bigDecimal);

            if (bigDecimal.signum() < 1) {
                throw new IllegalArgumentException();
            }
        }
    }

}