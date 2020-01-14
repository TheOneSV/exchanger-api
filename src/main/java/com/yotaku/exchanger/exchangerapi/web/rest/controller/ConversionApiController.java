package com.yotaku.exchanger.exchangerapi.web.rest.controller;

import com.yotaku.exchanger.exchangerapi.domain.Account;
import com.yotaku.exchanger.exchangerapi.domain.ExchangeTransaction;
import com.yotaku.exchanger.exchangerapi.domain.User;
import com.yotaku.exchanger.exchangerapi.resources.ExchangeTransactionDto;
import com.yotaku.exchanger.exchangerapi.service.ExchangeService;
import com.yotaku.exchanger.exchangerapi.service.ExchangeTransactionService;
import com.yotaku.exchanger.exchangerapi.service.UserService;
import com.yotaku.exchanger.exchangerapi.util.validation.ValidCurrencyFormat;
import com.yotaku.exchanger.exchangerapi.web.exceptions.BadRequestException;
import com.yotaku.exchanger.exchangerapi.web.exceptions.ResourceNotFoundException;
import com.yotaku.exchanger.exchangerapi.web.util.LinkUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


@Validated
@RestController
@RequestMapping(value = "/api/conversion")
@Api(value = "Exchange API Controller")
public class ConversionApiController {

    private static final String TRANSACTIONS_PATH = "/v1/transactions";

    private final UserService userService;
    private final ExchangeService exchangeService;
    private final ExchangeTransactionService exchangeTransactionService;

    @Autowired
    public ConversionApiController(final UserService userService,
                                   final ExchangeService exchangeService,
                                   final ExchangeTransactionService exchangeTransactionService) {

        this.userService = userService;
        this.exchangeService = exchangeService;
        this.exchangeTransactionService = exchangeTransactionService;
    }

    @ApiOperation(value = "Еxchange rate")
    @GetMapping(value = "/v1/rate")
    public String rate(
            @ApiParam(value = "Source currency", required = true)
            @RequestParam(name = "from") @ValidCurrencyFormat
            final String from,
            @ApiParam(value = "Target currency", required = true)
            @RequestParam(name = "to") @ValidCurrencyFormat
            final String to) {

        return exchangeService.rate(from, to).toString();
    }

    @ApiOperation(value = "Money exchange")
    @GetMapping(value = "/v1/exchange")
    public ExchangeTransactionDto exchange(
            @ApiParam(value = "Source currency", required = true)
            @RequestParam(name = "from") @ValidCurrencyFormat
            final String from,
            @ApiParam(value = "Target currency", required = true)
            @RequestParam(name = "to") @ValidCurrencyFormat
            final String to,
            @ApiParam(value = "Amount to exchange", required = true)
            @RequestParam(name = "amount") @Positive
            final BigDecimal amount,
            final Principal principal) {

        final User user = userService.findByUsernameIgnoreCase(principal.getName());
        final Account account = user.getAccount();
        final ExchangeTransaction exchangeTransaction = exchangeService.exchangeWithTransaction(from, to, amount, account);

        return ExchangeTransactionDto.from(exchangeTransaction);
    }

    @ApiOperation(value = "Get an exchange transaction")
    @GetMapping(value = TRANSACTIONS_PATH + "/{id}")
    public ExchangeTransactionDto transaction(
            @ApiParam(value = "Transaction id", required = true) @PathVariable("id") final Long id) {

        return exchangeTransactionService.findById(id)
                .map(ExchangeTransactionDto::from)
                .orElseThrow(() -> new BadRequestException("No transaction found"));
    }

    @ApiOperation(value = "Get exchange transactions by date")
    @GetMapping(value = TRANSACTIONS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedResources<ExchangeTransactionDto> transactions(
            @ApiParam(value = "Transaction date. Format: YYYY-MM-DD", required = true)
            @RequestParam(name = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            final LocalDate date,
            final Pageable pageable,
            final UriComponentsBuilder uriBuilder,
            final HttpServletRequest request) {

        final Page<ExchangeTransaction> resultPage = exchangeTransactionService.findAllByDate(date, pageable);

        if (pageable.getPageNumber() > resultPage.getTotalPages()) {
            throw new ResourceNotFoundException();
        }

        final List<ExchangeTransactionDto> transactionDtos = ExchangeTransactionDto.from(resultPage.getContent());

        transactionDtos.forEach(t -> {
            Link selfLink = linkTo(ConversionApiController.class).slash(TRANSACTIONS_PATH).slash(t.getTransactionId()).withSelfRel();
            t.add(selfLink);
        });

        uriBuilder.path(request.getRequestURI());
        uriBuilder.query(request.getQueryString());

        final PagedResources<ExchangeTransactionDto> resources
                = LinkUtil.createPagedModel(
                        transactionDtos, uriBuilder, pageable.getPageNumber(), resultPage.getTotalPages(),
                        pageable.getPageSize(), resultPage.getTotalElements());

        return resources;
    }
}