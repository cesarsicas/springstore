package br.com.cesarsicas.springstore.domain.customer.dto;

import br.com.cesarsicas.springstore.domain.customer.customer_address.CustomerAddressEntity;

public record GetCustomerAddressDto(
        Long id,
        String street,
        String number,
        String complement,
        String city,
        String state,
        String uf,
        String cep,
        Long customerId) {

    public GetCustomerAddressDto(CustomerAddressEntity entity) {
        this(entity.getId(),
                entity.getStreet(),
                entity.getNumber(),
                entity.getComplement(),
                entity.getCity(),
                entity.getState(),
                entity.getUf(),
                entity.getCep(),
                entity.getCustomer().getId());
    }
}
