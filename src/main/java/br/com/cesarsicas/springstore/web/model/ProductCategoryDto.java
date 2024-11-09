package br.com.cesarsicas.springstore.web.model;

import br.com.cesarsicas.springstore.domain.model.ProductCategory;
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
