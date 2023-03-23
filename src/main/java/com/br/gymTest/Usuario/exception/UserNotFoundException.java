package com.br.gymTest.Usuario.exception;

import com.br.gymTest.exceptions.DefaultAbstractException;
import com.br.gymTest.exceptions.LogLevel;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends DefaultAbstractException {
    public UserNotFoundException(String message, HttpStatus httpStatus, LogLevel logLevel) {
        super(message);
    }
}
