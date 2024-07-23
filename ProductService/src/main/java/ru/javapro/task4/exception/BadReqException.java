package ru.javapro.task4.exception;

import org.springframework.http.HttpStatus;

public class BadReqException extends RuntimeException{
    public BadReqException(String message) {
        super(message);
    }
}
