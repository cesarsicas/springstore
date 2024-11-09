package br.com.cesarsicas.springstore.domain.model;

import br.com.cesarsicas.springstore.data.product_category.ProductCategoryEntity;
import br.com.cesarsicas.springstore.web.model.ProductCategoryDto;
import jakarta.validation.constraints.NotBlank;

public record ProductCategory(
        String name,
        String description
) {

        public ProductCategory(ProductCategoryEntity entity){
                this(entity.getName(), entity.getDescription());
        }

        public ProductCategory(ProductCategoryDto productDto) {
                this(productDto.name(), productDto.description());
        }
}
