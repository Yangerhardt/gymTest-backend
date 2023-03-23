package com.br.gymTest.exceptions;

import org.springframework.http.HttpStatus;

public class DefaultAbstractException extends RuntimeException {
    private  LogLevel logLevel = LogLevel.ERROR;
    private  HttpStatus status = HttpStatus.BAD_REQUEST;


    public DefaultAbstractException(String message) {
        super(message);
    }

    public DefaultAbstractException (String message, HttpStatus status, LogLevel logLevel) {
        super(message);
        this.status = status;
        this.logLevel = logLevel;
    }

    public DefaultAbstractException (HttpStatus status, LogLevel logLevel) {
        this.status = status;
        this.logLevel = logLevel;
    }

    public LogLevel getLogLevel() {
        return logLevel;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
