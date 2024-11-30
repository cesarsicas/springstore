package br.com.cesarsicas.springstore.domain.cart;

import br.com.cesarsicas.springstore.domain.cart.cart_product.CartProductEntity;
import br.com.cesarsicas.springstore.domain.customer.CustomerEntity;
import br.com.cesarsicas.springstore.domain.product.data.product.ProductEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "carts")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Getter
@Setter
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerEntity customer;


    @OneToMany(mappedBy = "cart")
    private  List<CartProductEntity> cartProducts;

    @ManyToMany
    @JoinTable(
            name = "cart_products",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<ProductEntity> products;
}
