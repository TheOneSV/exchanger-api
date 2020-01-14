package com.yotaku.exchanger.exchangerapi.repository;

import com.yotaku.exchanger.exchangerapi.domain.ExchangeTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ExchangeTransactionRepository extends JpaRepository<ExchangeTransaction, Long> {

    Page<ExchangeTransaction> findAllByTransactionDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

}
