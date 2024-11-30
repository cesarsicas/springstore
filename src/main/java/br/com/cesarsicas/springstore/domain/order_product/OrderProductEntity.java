package br.com.cesarsicas.springstore.domain.order_product;

import br.com.cesarsicas.springstore.domain.cart.cart_product.CartProductEntity;
import br.com.cesarsicas.springstore.domain.order.OrderEntity;
import br.com.cesarsicas.springstore.domain.product.data.product.ProductEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@Table(name = "order_products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class OrderProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal productValue;

    private int productQuantity;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private OrderEntity order;

    public OrderProductEntity(CartProductEntity cartProductEntity, OrderEntity order) {
        this.product = cartProductEntity.getProduct();
        this.productQuantity = cartProductEntity.getQuantity();
        this.productValue = cartProductEntity.getProduct().getValue();
        this.order = order;
    }
}
