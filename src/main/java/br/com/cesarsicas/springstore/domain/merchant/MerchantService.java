package br.com.cesarsicas.springstore.domain.merchant;

import br.com.cesarsicas.springstore.domain.merchant.dto.CreateMerchantDto;
import br.com.cesarsicas.springstore.domain.merchant.dto.UpdateMerchantDto;
import br.com.cesarsicas.springstore.domain.user.data.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MerchantService {

    @Autowired
    MerchantRepository repository;

    public void saveMerchant(CreateMerchantDto merchantDto, UserEntity user) {
        //todo validate user and document
        repository.save(new MerchantEntity(merchantDto, user));
    }

    @Transactional
    public void updateMerchant(UpdateMerchantDto updateCustomerDto, UserEntity user) {
        //todo validate user and document

        var merchant = repository.findByUser(user);

        merchant.setAddress(updateCustomerDto.address());
        merchant.setDocument(updateCustomerDto.document());
        merchant.setResponsibleName(updateCustomerDto.responsibleName());
        merchant.setCompanyName(updateCustomerDto.companyName());
    }
}
