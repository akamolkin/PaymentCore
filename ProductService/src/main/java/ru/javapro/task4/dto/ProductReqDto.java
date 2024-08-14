package ru.javapro.task4.dto;

public record ProductReqDto(
        long id,
        long userId,
        String accountNumber,
        double balance,
        String type
) {
}
