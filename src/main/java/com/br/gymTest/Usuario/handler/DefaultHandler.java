package com.br.gymTest.Usuario.handler;

import com.br.gymTest.Usuario.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public abstract class DefaultHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public static ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), ex.getCause());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    public static class ErrorResponse {
        private int status = HttpStatus.BAD_REQUEST.value();
        private String message;
        private Throwable cause;

        public ErrorResponse(String message, Throwable cause) {
            this.message = message;
            this.cause = cause;
        }

        public ErrorResponse(int status, String message, Throwable cause) {
            this.status = status;
            this.message = message;
            this.cause = cause;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
