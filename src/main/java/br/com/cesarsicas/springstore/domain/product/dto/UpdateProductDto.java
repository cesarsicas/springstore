package br.com.cesarsicas.springstore.domain.product.dto;


import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record UpdateProductDto(
        @NotNull
        Long id,

        @NotBlank
        String name,

        @NotBlank
        String description,

        BigDecimal value,

        @Min(1)
        long category,

        @NotEmpty
        String imageUrl) {

}