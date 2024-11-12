package br.com.cesarsicas.springstore.data.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository  extends JpaRepository<ProductEntity, Long> {

    Page<ProductEntity> getAllBy(Pageable page);

    List<ProductEntity> findByNameOrDescriptionContainingIgnoreCase(String productName, String description);

    //this works too!
    //List<ProductEntity> findByCategoryId(Long categoryId);

    @Query("SELECT p FROM Product p WHERE p.category.id = :categoryId")
    List<ProductEntity> searchByCategoryId(@Param("categoryId") Long categoryId);

    @Query("SELECT p FROM Product p WHERE p.user.id = :userId")
    List<ProductEntity> searchByUser(@Param("userId") Long userId);


}
