package br.com.cesarsicas.springstore.domain.customer;

import br.com.cesarsicas.springstore.domain.user.data.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    CustomerEntity findByUser(UserEntity user);
}
