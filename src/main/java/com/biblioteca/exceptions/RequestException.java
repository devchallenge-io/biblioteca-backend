package com.biblioteca.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RequestException extends RuntimeException {

    private final String message;
    private final HttpStatus status;

    public RequestException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
