package br.com.cesarsicas.springstore.domain.cart.dto;

public record AddUpdateCartProductItem(
        Long productId,
        int quantity)
{
}
