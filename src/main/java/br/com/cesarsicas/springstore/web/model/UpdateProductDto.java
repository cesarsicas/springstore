package br.com.cesarsicas.springstore.web.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record UpdateProductDto(
        @NotNull
        Long id,

        @NotBlank
        String name,

        String description,

        BigDecimal value,

        long category,

        String imageUrl) {

}