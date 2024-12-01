package br.com.cesarsicas.springstore.domain.customer.customer_address;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddressEntity, Long> {
    @Query("SELECT ca FROM CustomerAddressEntity ca " +
            "JOIN ca.customer c " +
            "WHERE c.user.id = :userId")
    List<CustomerAddressEntity> searchAddressesByUserId(@Param("userId") Long userId);

    @Query("SELECT ca FROM CustomerAddressEntity ca " +
            "JOIN ca.customer c " +
            "WHERE c.user.id = :userId AND ca.id = :addressId ")
    CustomerAddressEntity searchAddressesByAddressIdAndUserId(@Param("addressId") Long addressId, @Param("userId") Long userId);

}