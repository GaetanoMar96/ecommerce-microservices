package com.microservice.inventoryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles MongoDbException. Created to encapsulate errors while performing mongo db operations.
     *
     * @param ex the MongoDbException
     * @return the ApiError object
     */
    @ExceptionHandler(MongoDbException.class)
    public ResponseEntity<Object> handleBadRequest(MongoDbException ex) {
        ApiError apiError = new ApiError("Error while performing mongo operations", ex);
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
