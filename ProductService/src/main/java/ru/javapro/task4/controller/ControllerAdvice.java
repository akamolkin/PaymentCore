package ru.javapro.task4.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.javapro.task4.dto.ErrorRespDto;
import ru.javapro.task4.exception.BadReqException;
import ru.javapro.task4.exception.NotFoundException;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(BadReqException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorRespDto handleBadReqException(BadReqException exception){
        return new ErrorRespDto(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorRespDto handleNotFoundException(NotFoundException exception){
        return new ErrorRespDto(HttpStatus.NOT_FOUND.value(), exception.getMessage());
    }
}
