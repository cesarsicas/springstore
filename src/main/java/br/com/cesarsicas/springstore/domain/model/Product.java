package br.com.cesarsicas.springstore.domain.model;

import br.com.cesarsicas.springstore.data.product.ProductEntity;
import br.com.cesarsicas.springstore.web.model.ProductDto;
import br.com.cesarsicas.springstore.web.model.UpdateProductDto;

import java.math.BigDecimal;

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

