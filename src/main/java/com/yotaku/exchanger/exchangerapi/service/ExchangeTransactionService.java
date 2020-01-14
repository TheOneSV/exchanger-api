package com.yotaku.exchanger.exchangerapi.service;

import com.yotaku.exchanger.exchangerapi.domain.ExchangeTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Optional;

public interface ExchangeTransactionService {

    Optional<ExchangeTransaction> findById(Long id);

    ExchangeTransaction save(ExchangeTransaction exchangeTransaction);

    Page<ExchangeTransaction> findAllByDate(LocalDate date, Pageable pageable);

}
