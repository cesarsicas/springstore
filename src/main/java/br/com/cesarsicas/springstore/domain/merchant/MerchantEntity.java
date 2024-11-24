package br.com.cesarsicas.springstore.domain.merchant;

import br.com.cesarsicas.springstore.domain.merchant.dto.CreateMerchantDto;
import br.com.cesarsicas.springstore.domain.merchant.dto.UpdateMerchantDto;
import br.com.cesarsicas.springstore.domain.user.data.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "merchants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class MerchantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String responsibleName;

    private String companyName;

    private String document;

    private String address;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    public MerchantEntity(CreateMerchantDto merchantDto, UserEntity user) {
        this.responsibleName = merchantDto.responsibleName();
        this.companyName = merchantDto.companyName();
        this.document = merchantDto.document();
        this.address = merchantDto.address();
        this.user = user;
    }

    public MerchantEntity(UpdateMerchantDto updateCustomerDto, UserEntity user) {
        this.responsibleName = updateCustomerDto.responsibleName();
        this.companyName = updateCustomerDto.companyName();
        this.document = updateCustomerDto.document();
        this.address = updateCustomerDto.address();
        this.user = user;
    }
}
