package br.com.cesarsicas.springstore.web.model;

import br.com.cesarsicas.springstore.data.product_category.ProductCategoryEntity;
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
