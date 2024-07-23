package ru.javapro.paymentscore.dto;

public record PayRespDto(
        long id,
        double oldBalance,
        double newBalance,
        String answer
) {
}
