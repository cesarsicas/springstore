package br.com.cesarsicas.springstore.domain.product_category;

import br.com.cesarsicas.springstore.domain.product.data.product_category.ProductCategoryEntity;

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
