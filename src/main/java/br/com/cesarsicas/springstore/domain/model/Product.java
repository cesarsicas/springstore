package br.com.cesarsicas.springstore.domain.model;

import br.com.cesarsicas.springstore.data.product.ProductEntity;
import br.com.cesarsicas.springstore.web.model.ProductDto;

import java.math.BigDecimal;

public record Product(
        String name,
        String description,
        BigDecimal value,
        long category) {

    public Product(ProductEntity product) {
        this(product.getName(), product.getDescription(), product.getValue(), product.getCategory().getId());
    }

    public Product(ProductDto productDto) {
        this(productDto.name(), productDto.description(), productDto.value(), productDto.category());
    }
}

