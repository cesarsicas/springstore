package br.com.cesarsicas.springstore.domain.customer_address;

import br.com.cesarsicas.springstore.domain.customer.CustomerEntity;
import br.com.cesarsicas.springstore.domain.customer.dto.CreateCustomerAddressDto;
import br.com.cesarsicas.springstore.domain.customer.dto.UpdateCustomerAddressDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "customer_addresses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class CustomerAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private String number;

    private String complement;

    private String city;

    private String state;

    private String uf;

    private String cep;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerEntity customer;

    public CustomerAddressEntity(CreateCustomerAddressDto createCustomerAddressDto, CustomerEntity customer) {
        this.street = createCustomerAddressDto.street();
        this.number = createCustomerAddressDto.number();
        this.complement = createCustomerAddressDto.complement();
        this.city = createCustomerAddressDto.city();
        this.state = createCustomerAddressDto.state();
        this.uf = createCustomerAddressDto.uf();
        this.cep = createCustomerAddressDto.cep();
        this.customer = customer;
    }

    public void update(UpdateCustomerAddressDto updateCustomerAddressDto) {
        this.street = updateCustomerAddressDto.street();
        this.number = updateCustomerAddressDto.number();
        this.complement = updateCustomerAddressDto.complement();
        this.city = updateCustomerAddressDto.city();
        this.state = updateCustomerAddressDto.state();
        this.uf = updateCustomerAddressDto.uf();
        this.cep = updateCustomerAddressDto.cep();
    }
}
