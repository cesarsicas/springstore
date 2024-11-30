package br.com.cesarsicas.springstore.domain.cart;

import br.com.cesarsicas.springstore.domain.customer.CustomerEntity;
import br.com.cesarsicas.springstore.domain.user.data.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Long> {

    CartEntity findByCustomer(CustomerEntity customer);

    @Query("SELECT cart FROM CartEntity cart " +
            "JOIN cart.customer customer " +
            "WHERE customer.user.id = :userId")
    CartEntity getByUserId(@Param("userId") Long userId);


}
