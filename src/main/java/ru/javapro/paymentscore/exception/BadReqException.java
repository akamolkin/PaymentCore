package ru.javapro.paymentscore.exception;

public class BadReqException extends RuntimeException{
    public BadReqException(String message) {
        super(message);
    }
}
