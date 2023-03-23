package com.br.gymTest.exceptions;

import org.springframework.http.HttpStatus;

public class BasicException extends DefaultAbstractException{
    public BasicException(String message) {
        super(message);
    }

    public BasicException(String message, HttpStatus status, LogLevel logLevel) {
        super(message, status, logLevel);
    }

    public BasicException(HttpStatus status, LogLevel logLevel) {
        super(status, logLevel);
    }
}
