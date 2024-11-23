package br.com.cesarsicas.springstore.data.customer_address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddressEntity, Long> {
}
