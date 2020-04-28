package com.yotaku.exchanger.exchangerapi.dto;

import com.yotaku.exchanger.exchangerapi.domain.Exchange;
import com.yotaku.exchanger.exchangerapi.domain.ExchangeTransaction;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Currency;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ExchangeTransactionDto extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Long transactionId;

    private final LocalDateTime transactionDateTime;

    private final Currency sourceCurrency;
    private final Currency targetCurrency;

    private final BigDecimal exchangeRate;

    private final BigDecimal sourceAmount;
    private final BigDecimal targetAmount;

    private ExchangeTransactionDto(final ExchangeTransactionDtoBuilder builder) {
        this.transactionId = builder.transactionId;
        this.transactionDateTime = builder.transactionDateTime;
        this.sourceCurrency = builder.sourceCurrency;
        this.targetCurrency = builder.targetCurrency;
        this.exchangeRate = builder.exchangeRate;
        this.sourceAmount = builder.sourceAmount;
        this.targetAmount = builder.targetAmount;
    }

    public static ExchangeTransactionDtoBuilder builder() {
        return new ExchangeTransactionDtoBuilder();
    }

    public Long getTransactionId() {
        return transactionId;
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

    public static class ExchangeTransactionDtoBuilder {
        private Long transactionId;

        private LocalDateTime transactionDateTime;

        private Currency sourceCurrency;
        private Currency targetCurrency;

        private BigDecimal exchangeRate;
        private BigDecimal sourceAmount;
        private BigDecimal targetAmount;

        private ExchangeTransactionDtoBuilder() {}

        public ExchangeTransactionDtoBuilder transactionId(Long transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public ExchangeTransactionDtoBuilder transactionDateTime(LocalDateTime transactionDateTime) {
            this.transactionDateTime = transactionDateTime;
            return this;
        }

        public ExchangeTransactionDtoBuilder sourceCurrency(Currency sourceCurrency) {
            this.sourceCurrency = sourceCurrency;
            return this;
        }
        public ExchangeTransactionDtoBuilder sourceCurrency(String sourceCurrencyCode) {
            this.sourceCurrency = Currency.getInstance(sourceCurrencyCode);
            return this;
        }

        public ExchangeTransactionDtoBuilder targetCurrency(Currency targetCurrency) {
            this.targetCurrency = targetCurrency;
            return this;
        }
        public ExchangeTransactionDtoBuilder targetCurrency(String targetCurrencyCode) {
            this.targetCurrency = Currency.getInstance(targetCurrencyCode);
            return this;
        }

        public ExchangeTransactionDtoBuilder exchangeRate(BigDecimal exchangeRate) {
            this.exchangeRate = exchangeRate;
            return this;
        }

        public ExchangeTransactionDtoBuilder sourceAmount(BigDecimal sourceAmount) {
            this.sourceAmount = sourceAmount;
            return this;
        }

        public ExchangeTransactionDtoBuilder targetAmount(BigDecimal targetAmount) {
            this.targetAmount = targetAmount;
            return this;
        }

        public ExchangeTransactionDto build() {
            return new ExchangeTransactionDto(this);
        }

    }

}
