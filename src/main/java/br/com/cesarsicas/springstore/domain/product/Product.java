package br.com.cesarsicas.springstore.domain.product;

import br.com.cesarsicas.springstore.domain.product.data.product.ProductEntity;
import br.com.cesarsicas.springstore.domain.product.dto.ProductDto;
import br.com.cesarsicas.springstore.domain.product.dto.UpdateProductDto;

import java.math.BigDecimal;

@Deprecated(since = "use dto now")
public record Product(
        long id,
        String name,
        String description,
        BigDecimal value,
        long category,
        String imageUrl) {

    public Product(ProductEntity product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getValue(), product.getCategory().getId(), product.getImageUrl());
    }

    public Product(ProductDto productDto) {
        this(0, productDto.name(), productDto.description(), productDto.value(), productDto.category(), productDto.imageUrl());
    }

    public Product(UpdateProductDto productDto) {
        this(productDto.id(), productDto.name(), productDto.description(), productDto.value(), productDto.category(), productDto.imageUrl());
    }

}

