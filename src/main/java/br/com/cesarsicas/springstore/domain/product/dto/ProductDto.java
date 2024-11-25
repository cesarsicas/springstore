package br.com.cesarsicas.springstore.domain.product.dto;


import br.com.cesarsicas.springstore.domain.product.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductDto(
        @NotBlank
        String name,

        @NotBlank
        String description,

        BigDecimal value,

        @NotNull
        long category,

        String imageUrl) {

    public ProductDto(Product product) {
        this(product.name(), product.description(), product.value(), product.category(), product.imageUrl());
    }
}