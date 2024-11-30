package br.com.cesarsicas.springstore.domain.cart.cart_product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartProductRepository extends JpaRepository<CartProductEntity, Long> {
    @Query("DELETE FROM CartProductEntity c WHERE c.cart.id = :cartId AND c.product.id = :productId")
    void deleteByCartIdAndProductId(@Param("cartId") Long cartId, @Param("productId") Long productId);

    @Modifying
    @Query("DELETE FROM CartProductEntity c WHERE c.cart.id = :cartId AND c.product.id IN :productIds")
    void deleteByCartIdAndProductIds(@Param("cartId") Long cartId, @Param("productIds") List<Long> productIds);

    @Modifying
    @Query("DELETE FROM CartProductEntity c WHERE c.cart.id = :cartId")
    void deleteByCartId(@Param("cartId") Long cartId);

}
