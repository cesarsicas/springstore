package br.com.cesarsicas.springstore.data.product;


import br.com.cesarsicas.springstore.data.merchant.MerchantEntity;
import br.com.cesarsicas.springstore.data.product_category.ProductCategoryEntity;
import br.com.cesarsicas.springstore.domain.model.Product;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "products")
@Entity(name = "Product")
@Getter
@Setter
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
    private String imageUrl;
    private int quantity;


    @ManyToOne
    @JoinColumn(name = "merchant_id")
    private MerchantEntity merchant;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private ProductCategoryEntity category;

    public ProductEntity(Product productDto, ProductCategoryEntity category, MerchantEntity merchant) {
        this.name = productDto.name();
        this.description = productDto.description();
        this.value = productDto.value();
        this.category = category;
        this.merchant = merchant;
    }


    @Override
    public String toString() {
        return "ProductEntity{" + "id=" + id + ", name='" + name + '\'' + ", description='" + description + '\'' + ", value=" + value + '}';
    }

}
