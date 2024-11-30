package br.com.cesarsicas.springstore.domain.cart.dto;

import br.com.cesarsicas.springstore.domain.cart.CartEntity;

import java.util.List;

public record GetCartDto(
        Long id,
        Long customerId,
        List<CartProductItemDto> products) {
    public GetCartDto(CartEntity cartEntity) {
        this(
                cartEntity.getId(),
                cartEntity.getCustomer().getId(),
                cartEntity.getCartProducts().stream().map(CartProductItemDto::new).toList());

    }
}
