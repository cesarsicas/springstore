package br.com.cesarsicas.springstore.domain.customer.dto;

import br.com.cesarsicas.springstore.domain.StringUtils;
import br.com.cesarsicas.springstore.domain.customer.customer_credit_card.CustomerCreditCardEntity;

public record GetCreditCardDto(
        Long id,
        String cardNumber,
        String brand,
        String expDat) {

    public GetCreditCardDto(CustomerCreditCardEntity customerCreditCardEntity) {
        this(customerCreditCardEntity.getId(),
                StringUtils.maskCreditCard(customerCreditCardEntity.getCardNumber()),
                customerCreditCardEntity.getBrand(),
                customerCreditCardEntity.getExpDate());
    }
}
