package br.com.cesarsicas.springstore.domain.cart.dto;

import br.com.cesarsicas.springstore.domain.cart.cart_product.CartProductEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CartProductItemDto(
        @NotBlank
        String name,

        @NotBlank
        String description,

        BigDecimal value,

        @NotNull
        long category,

        String imageUrl,

        int quantity) {

    public CartProductItemDto(CartProductEntity cartProductEntity) {
        this(
                cartProductEntity.getProduct().getName(),
                cartProductEntity.getProduct().getDescription(),
                cartProductEntity.getProduct().getValue(),
                cartProductEntity.getProduct().getCategory().getId(),
                cartProductEntity.getProduct().getImageUrl(),
                cartProductEntity.getQuantity());
    }

}
