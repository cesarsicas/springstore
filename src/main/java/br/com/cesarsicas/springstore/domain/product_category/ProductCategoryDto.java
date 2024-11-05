package br.com.cesarsicas.springstore.domain.product_category;

import jakarta.validation.constraints.NotBlank;

public record  ProductCategoryDto(
        @NotBlank
        String name,

        @NotBlank
        String description
) {

        public ProductCategoryDto(ProductCategoryEntity entity){
                this(entity.getName(), entity.getDescription());
        }
}
