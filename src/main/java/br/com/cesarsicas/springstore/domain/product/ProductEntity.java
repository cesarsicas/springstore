package br.com.cesarsicas.springstore.domain.product;


import br.com.cesarsicas.springstore.domain.product_category.ProductCategoryEntity;
import br.com.cesarsicas.springstore.domain.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

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
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategoryEntity category;

    public ProductEntity(ProductDto productDto, ProductCategoryEntity category, UserEntity user) {
        this.name = productDto.name();
        this.description = productDto.description();
        this.value = productDto.value();
        this.category = category;
        this.user = user;
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

    public void atualizar(UpdateProductDto updateProductDto, ProductCategoryEntity category) {
        this.category = category;

        if (updateProductDto.description()!=null){
            this.description = updateProductDto.description();
        }

        if (updateProductDto.name()!=null){
            this.description = updateProductDto.name();
        }

        if (updateProductDto.value() !=null){
            this.value = updateProductDto.value();
        }

    }
}
