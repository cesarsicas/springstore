package br.com.cesarsicas.springstore.domain.cart.dto;

public record AddCartProductItem(
        Long productId,
        int quantity)
{
}
