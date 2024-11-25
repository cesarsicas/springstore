package br.com.cesarsicas.springstore.domain.customer.customer_credit_card;

import org.springframework.data.jpa.repository.JpaRepository; import org.springframework.stereotype.Repository; @Repository public interface CustomerCreditCardRepository extends JpaRepository<CustomerCreditCardEntity, Long>{

}