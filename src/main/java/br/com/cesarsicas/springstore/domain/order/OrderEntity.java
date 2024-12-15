package br.com.cesarsicas.springstore.domain.order;

import br.com.cesarsicas.springstore.domain.customer.CustomerEntity;
import br.com.cesarsicas.springstore.domain.customer.customer_address.CustomerAddressEntity;
import br.com.cesarsicas.springstore.domain.customer.customer_credit_card.CustomerCreditCardEntity;
import br.com.cesarsicas.springstore.domain.order.dto.CreateOrder;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@ToString
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal totalAmount;

    private LocalDateTime datetime;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "customer_address_id", referencedColumnName = "id")
    private CustomerAddressEntity address;


    @ManyToOne
    @JoinColumn(name = "customer_credit_card_id", referencedColumnName = "id")
    private CustomerCreditCardEntity creditCard;


    public OrderEntity(CreateOrder createOrder,
                       CustomerEntity customer,
                       CustomerCreditCardEntity customerCreditCardEntity,
                       CustomerAddressEntity customerAddressEntity) {
        this.totalAmount = createOrder.totalAmount();
        this.datetime = createOrder.datetime();
        this.customer = customer;
        this.address = customerAddressEntity;
        this.creditCard = customerCreditCardEntity;
    }
}
