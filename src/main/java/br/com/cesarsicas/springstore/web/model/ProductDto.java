package br.com.cesarsicas.springstore.web.model;


import br.com.cesarsicas.springstore.data.product.ProductEntity;
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
        long category) {

    public ProductDto(ProductEntity product) {
        this(product.getName(), product.getDescription(), product.getValue(), product.getCategory().getId());
    }
}