package br.com.cesarsicas.springstore.data.cart_product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartProductRepository extends JpaRepository<CartProductEntity, Long> {
}