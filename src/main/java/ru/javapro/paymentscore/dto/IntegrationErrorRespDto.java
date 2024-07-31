package ru.javapro.paymentscore.dto;

public record IntegrationErrorRespDto(
        Integer errorCode,
        String errorMessage
) {
}
