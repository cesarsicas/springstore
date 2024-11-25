package br.com.cesarsicas.springstore.domain.customer.dto;

public record CreateCustomerAddressDto(
        String street,
        String number,
        String complement,
        String city,
        String state,
        String uf,
        String cep,
        Long customerId) {
}
