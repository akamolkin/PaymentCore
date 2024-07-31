package ru.javapro.paymentscore.dto;

public record ProductDto(
        long id,
        long userId,
        String accountNumber,
        double balance,
        String type
) {
}
