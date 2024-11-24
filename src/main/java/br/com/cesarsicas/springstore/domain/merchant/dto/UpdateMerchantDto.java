package br.com.cesarsicas.springstore.domain.merchant.dto;

public record UpdateMerchantDto(
        Long id,
        String responsibleName,
        String companyName,
        String document,
        String address) {


}
