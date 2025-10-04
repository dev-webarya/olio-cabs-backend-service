package com.oliocabs.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT) // This status code (409) indicates a conflict with the current state of the resource
public class NoResourceAvailableException extends RuntimeException {
    public NoResourceAvailableException(String message) {
        super(message);
    }
}