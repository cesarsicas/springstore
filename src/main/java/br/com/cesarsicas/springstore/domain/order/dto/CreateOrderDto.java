package br.com.cesarsicas.springstore.domain.order.dto;

public record CreateOrderDto(
        Long addressId,
        Long creditCardId
) {
}
