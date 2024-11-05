package br.com.cesarsicas.springstore.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository  extends JpaRepository<ProductEntity, Long> {

    List<ProductEntity> findByNameOrDescriptionContainingIgnoreCase(String productName, String description);

}
