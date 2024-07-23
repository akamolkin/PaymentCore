package ru.javapro.paymentscore.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.javapro.paymentscore.dto.IntegrationErrorRespDto;
import ru.javapro.paymentscore.exception.BadReqException;
import ru.javapro.paymentscore.exception.IntegrationException;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(IntegrationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public IntegrationErrorRespDto handleIntegrationException(IntegrationException exception){
        return new IntegrationErrorRespDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(BadReqException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public IntegrationErrorRespDto handleBadReqException(BadReqException exception){
        return new IntegrationErrorRespDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
}
