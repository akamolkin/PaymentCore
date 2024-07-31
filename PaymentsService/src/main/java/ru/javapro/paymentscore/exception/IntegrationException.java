package ru.javapro.paymentscore.exception;

public class IntegrationException extends RuntimeException{
    public IntegrationException(String message) {
        super(message);
    }
}
