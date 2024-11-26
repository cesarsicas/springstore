package br.com.cesarsicas.springstore.domain.cart;

import br.com.cesarsicas.springstore.domain.customer.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {

    CartEntity findByCustomer(CustomerEntity customer);

}
