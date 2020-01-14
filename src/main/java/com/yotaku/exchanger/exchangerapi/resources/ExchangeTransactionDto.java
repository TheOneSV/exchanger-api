package com.yotaku.exchanger.exchangerapi.resources;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.yotaku.exchanger.exchangerapi.domain.Account;
import com.yotaku.exchanger.exchangerapi.domain.ExchangeTransaction;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Currency;
import java.util.List;
import java.util.stream.Collectors;

public class ExchangeTransactionDto extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Long id;

    private final LocalDateTime transactionDateTime;

    private final Currency sourceCurrency;
    private final Currency targetCurrency;

    private final BigDecimal exchangeRate;

    private final BigDecimal sourceAmount;
    private final BigDecimal targetAmount;

    private ExchangeTransactionDto(final ExchangeTransaction exchangeTransaction) {
        this.id = exchangeTransaction.getId();

        this.transactionDateTime = exchangeTransaction.getTransactionDateTime();

        this.sourceCurrency = exchangeTransaction.getSourceCurrency();
        this.targetCurrency = exchangeTransaction.getTargetCurrency();

        this.exchangeRate = exchangeTransaction.getExchangeRate();

        this.sourceAmount = exchangeTransaction.getSourceAmount();
        this.targetAmount = exchangeTransaction.getTargetAmount();
    }

    public static ExchangeTransactionDto from(ExchangeTransaction exchangeTransaction) {
        return new ExchangeTransactionDto(exchangeTransaction);
    }

    public static List<ExchangeTransactionDto> from(Collection<ExchangeTransaction> exchangeTransactions) {
        return exchangeTransactions.stream().map(ExchangeTransactionDto::from).collect(Collectors.toList());
    }

    public Long getTransactionId() {
        return id;
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

}
