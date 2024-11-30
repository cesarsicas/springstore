package br.com.cesarsicas.springstore.domain.cart.dto;

import java.util.List;

public record AddUpdateCartProductsDto(
        List<AddCartProductItem> products) {
}
