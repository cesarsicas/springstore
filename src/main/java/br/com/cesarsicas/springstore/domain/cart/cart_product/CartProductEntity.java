package br.com.cesarsicas.springstore.domain.cart.cart_product;

import br.com.cesarsicas.springstore.domain.cart.CartEntity;
import br.com.cesarsicas.springstore.domain.product.Product;
import br.com.cesarsicas.springstore.domain.product.data.product.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Entity
@Table(name = "cart_products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class CartProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private CartEntity cart;

    private int quantity;

    public CartProductEntity(ProductEntity product, CartEntity cart, int quantity ) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
    }
}

