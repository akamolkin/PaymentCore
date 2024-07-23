package ru.javapro.task4.dto;

public record ErrorRespDto(
        Integer errorCode,
        String errorMessage
) {
}
