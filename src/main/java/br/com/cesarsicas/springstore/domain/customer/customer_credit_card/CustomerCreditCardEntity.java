package br.com.cesarsicas.springstore.domain.customer.customer_credit_card;

import br.com.cesarsicas.springstore.domain.customer.CustomerEntity;
import br.com.cesarsicas.springstore.domain.customer.dto.CreateCustomerCreditCardDto;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "customer_credit_cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class CustomerCreditCardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cardNumber;

    private String brand;

    private String expDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerEntity customer;

    public CustomerCreditCardEntity(CreateCustomerCreditCardDto createCustomerCreditCardDto, CustomerEntity customer) {
        this.cardNumber = createCustomerCreditCardDto.cardNumber();
        this.brand = createCustomerCreditCardDto.brand();
        this.expDate = createCustomerCreditCardDto.expDat();
        this.customer = customer;
    }
}
