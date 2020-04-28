package com.yotaku.exchanger.exchangerapi.mapper;

import com.yotaku.exchanger.exchangerapi.domain.ExchangeTransaction;
import com.yotaku.exchanger.exchangerapi.dto.ExchangeTransactionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ExchangeTransactionMapper {

    @Mappings({
            @Mapping(target = "transactionId", source = "id")
    })
    ExchangeTransactionDto toExchangeTransactionDto(ExchangeTransaction exchangeTransaction);

}
