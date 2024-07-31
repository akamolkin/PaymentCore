package ru.javapro.task4.dto;

public record PayRespDto(
        long id,
        double oldBalance,
        double newBalance,
        int errorCode,
        String answer
) {
}
