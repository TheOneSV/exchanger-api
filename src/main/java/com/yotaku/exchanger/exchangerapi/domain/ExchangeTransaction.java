package com.yotaku.exchanger.exchangerapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Currency;

@Entity
@Table(name = ExchangeTransaction.TABLE_NAME)
public class ExchangeTransaction {

    public static final String TABLE_NAME = "exchange_transactions";

    public static class Properties {
        public static final String ID = "id";
        public static final String ACCOUNT = "account";
        public static final String TRANSACTION_DATE_TIME = "transactionDateTime";
        public static final String SOURCE_CURRENCY = "sourceCurrency";
        public static final String TARGET_CURRENCY = "targetCurrency";
        public static final String EXCHANGE_RATE = "exchangeRate";
        public static final String SOURCE_AMOUNT = "sourceAmount";
        public static final String TARAGET_AMOUNT = "targetAmount";
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Positive
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    @Valid
    @JsonBackReference
    private Account account;

    @Column(name = "transaction_date_time", nullable = false)
    @NotNull
    private LocalDateTime transactionDateTime;

    @Column(name = "source_currency", nullable = false)
    @NotNull
    private Currency sourceCurrency;

    @Column(name = "target_currency", nullable = false)
    @NotNull
    private Currency targetCurrency;

    @Column(name = "exchange_rate", nullable = false, precision = 20, scale = 4)
    @NotNull
    private BigDecimal exchangeRate;

    @Column(name = "source_amount", nullable = false, precision = 20, scale = 4)
    @NotNull
    private BigDecimal sourceAmount;

    @Column(name = "target_amount", nullable = false, precision = 20, scale = 4)
    @NotNull
    private BigDecimal targetAmount;

    public Long getId() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(LocalDateTime transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    public Currency getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(Currency sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(Currency targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public BigDecimal getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(BigDecimal sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    public BigDecimal getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(BigDecimal targetAmount) {
        this.targetAmount = targetAmount;
    }

}
