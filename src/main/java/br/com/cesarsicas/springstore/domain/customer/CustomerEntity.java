package br.com.cesarsicas.springstore.domain.customer;

import br.com.cesarsicas.springstore.domain.customer.dto.CreateCustomerDto;
import br.com.cesarsicas.springstore.domain.customer.dto.UpdateCustomerDto;
import br.com.cesarsicas.springstore.domain.user.data.UserEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String document;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    public CustomerEntity(CreateCustomerDto customerDto, UserEntity user) {
        this.name = customerDto.name();
        this.document = customerDto.document();
        this.user = user;
    }

    public void update(UpdateCustomerDto customerDto) {
        this.name = customerDto.name();
        this.document = customerDto.document();
    }
}
