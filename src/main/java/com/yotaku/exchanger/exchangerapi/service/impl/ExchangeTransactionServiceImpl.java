package com.yotaku.exchanger.exchangerapi.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.yotaku.exchanger.exchangerapi.domain.ExchangeTransaction;
import com.yotaku.exchanger.exchangerapi.repository.ExchangeTransactionRepository;
import com.yotaku.exchanger.exchangerapi.service.ExchangeTransactionService;

@Service
public class ExchangeTransactionServiceImpl implements ExchangeTransactionService {

    private final ExchangeTransactionRepository exchangeTransactionRepository;

    @Autowired
    public ExchangeTransactionServiceImpl(final ExchangeTransactionRepository exchangeTransactionRepository) {
        this.exchangeTransactionRepository = exchangeTransactionRepository;
    }

    @Override
    public Optional<ExchangeTransaction> findById(final Long id) {
        return exchangeTransactionRepository.findById(id);
    }

    @Override
    public ExchangeTransaction save(final ExchangeTransaction exchangeTransaction) {
        return exchangeTransactionRepository.save(exchangeTransaction);
    }

    @Override
    public Page<ExchangeTransaction> findAllByDate(final LocalDate date, final Pageable pageable) {
        final LocalDateTime startDateTime = date.atTime(LocalTime.MIN);
        final LocalDateTime endDateTime = date.atTime(LocalTime.MAX);

        return exchangeTransactionRepository.findAllByTransactionDateTimeBetween(startDateTime, endDateTime, pageable);
    }

}
