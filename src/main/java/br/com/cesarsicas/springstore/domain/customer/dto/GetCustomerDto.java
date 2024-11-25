package br.com.cesarsicas.springstore.domain.customer.dto;

import br.com.cesarsicas.springstore.domain.customer.CustomerEntity;

public record GetCustomerDto(
        Long id,
        String name,
        String document,
        Long userId) {
    public GetCustomerDto(CustomerEntity customer) {
        this(
                customer.getId(),
                customer.getName(),
                customer.getDocument(),
                customer.getUser().getId()
        );
    }
}
