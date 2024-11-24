package br.com.cesarsicas.springstore.domain.customer.dto;

public record UpdateCustomerDto(
        Long id,
        String name,
        String document,
        Long userId) {
}
