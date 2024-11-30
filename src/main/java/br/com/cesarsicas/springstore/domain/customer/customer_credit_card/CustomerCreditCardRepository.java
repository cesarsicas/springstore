package br.com.cesarsicas.springstore.domain.customer.customer_credit_card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository public interface CustomerCreditCardRepository extends JpaRepository<CustomerCreditCardEntity, Long>{
    @Query("SELECT cc FROM CustomerCreditCardEntity cc " +
            "JOIN cc.customer c " +
            "WHERE c.user.id = :userId")
    List<CustomerCreditCardEntity> searchCreditCardsByUser(@Param("userId") Long userId);
}