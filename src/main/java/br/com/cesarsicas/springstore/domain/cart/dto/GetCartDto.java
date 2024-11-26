package br.com.cesarsicas.springstore.domain.cart.dto;

import br.com.cesarsicas.springstore.domain.cart.CartEntity;
import br.com.cesarsicas.springstore.domain.product.dto.ProductDto;

import java.util.List;

public record GetCartDto(
        Long id,
        Long customerId,
        List<ProductDto> products) {
    public GetCartDto(CartEntity cartEntity) {
        this(
                cartEntity.getId(),
                cartEntity.getCustomer().getId(),
                cartEntity.getProducts().stream().map(ProductDto::new).toList());

    }
}
