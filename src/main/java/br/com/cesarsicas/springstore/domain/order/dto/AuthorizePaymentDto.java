package br.com.cesarsicas.springstore.domain.order.dto;

public record  AuthorizePaymentDto(
        String creditCardNumber,
        String cvv ,
        String brand,
        String expDate,
        String cardOwner) {
}
