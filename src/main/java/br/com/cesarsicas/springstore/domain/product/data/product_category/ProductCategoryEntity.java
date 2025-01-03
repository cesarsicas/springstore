package br.com.cesarsicas.springstore.domain.product.data.product_category;

import br.com.cesarsicas.springstore.domain.product.data.product.ProductEntity;
import br.com.cesarsicas.springstore.domain.product_category.ProductCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "product_categories")
@Entity(name = "ProductCategories")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProductCategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String imageUrl;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProductEntity> products;

    public ProductCategoryEntity(ProductCategory productDto) {
        this.name = productDto.name();
        this.description = productDto.description();
    }
}