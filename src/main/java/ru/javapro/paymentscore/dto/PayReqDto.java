package ru.javapro.paymentscore.dto;

public record PayReqDto(
        long productId,
        double sumPay
) {
}
