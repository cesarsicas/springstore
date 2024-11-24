package br.com.cesarsicas.springstore.domain.merchant.dto;

public record CreateMerchantDto(
        String responsibleName,
        String companyName,
        String document,
        String address) {


}
