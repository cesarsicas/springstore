package br.com.cesarsicas.springstore.domain.product;


import br.com.cesarsicas.springstore.domain.product_category.ProductCategoryEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Table(name = "products")
@Entity(name = "Product")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private BigDecimal value;



    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategoryEntity category;

    public ProductEntity(ProductDto productDto, ProductCategoryEntity category) {
        this.name = productDto.name();
        this.description = productDto.description();
        this.value = productDto.value();
        this.category = category;
    }


    @Override
    public String toString() {
        return "ProductEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", value=" + value +
                '}';
    }
}
