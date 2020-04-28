package com.yotaku.exchanger.exchangerapi.web.error;


import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;

public class ApiError {

    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ApiError(final HttpStatus status, final String message, final Collection<String> errors) {

        Objects.requireNonNull(status, "status must not be null");

        if (StringUtils.isBlank(message)) {
            throw new IllegalArgumentException("message must not be blank");
        }

        if (CollectionUtils.isEmpty(errors)) {
            throw new IllegalArgumentException("errors must not be empty");
        }

        this.status = status;
        this.message = message;
        this.errors = Collections.unmodifiableList(List.copyOf(errors));
    }

    public ApiError(final HttpStatus status, final String message, final String error) {
        this(status, message, Collections.singletonList(error));
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }

}