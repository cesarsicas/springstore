package br.com.cesarsicas.springstore.domain.product_category;

import jakarta.validation.constraints.NotBlank;

public record ProductCategoryDto(
        @NotBlank
        String name,

        @NotBlank
        String description
) {

    public ProductCategoryDto(ProductCategory entity) {
        this(entity.name(), entity.description());
    }
}
