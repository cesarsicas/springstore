package br.com.cesarsicas.springstore.domain.customer.dto;

public record CreateCustomerCreditCardDto(
        String cardNumber,
        String brand,
        String expDat) {
}