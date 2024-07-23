package ru.javapro.task4.dto;

import ru.javapro.task4.entity.Product;

import java.util.List;

public record ProductListRespDto(
        List<Product> productList
) {
}
