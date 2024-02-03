package com.microservice.productservice.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.Clock;
import java.time.OffsetDateTime;

@Data
public class ApiError {

    private HttpStatus status;
    private final String message;
    private String debugMessage;
    private final OffsetDateTime timeStamp;

    public ApiError(String message) {
        this.message = message;
        this.timeStamp = OffsetDateTime.now(Clock.systemUTC());
    }

    public ApiError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.timeStamp = OffsetDateTime.now(Clock.systemUTC());
    }

    public ApiError(String message, Throwable throwable) {
        this.message = message;
        this.debugMessage = throwable.getMessage();
        this.timeStamp = OffsetDateTime.now(Clock.systemUTC());
    }

    public ApiError(HttpStatus status, String message, Throwable throwable) {
        this.status = status;
        this.message = message;
        this.debugMessage = throwable.getMessage();
        this.timeStamp = OffsetDateTime.now(Clock.systemUTC());
    }
}
