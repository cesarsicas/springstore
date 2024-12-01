package br.com.cesarsicas.springstore.domain.cart;

import br.com.cesarsicas.springstore.domain.cart.cart_product.CartProductEntity;
import br.com.cesarsicas.springstore.domain.cart.cart_product.CartProductRepository;
import br.com.cesarsicas.springstore.domain.cart.dto.AddUpdateCartProductsDto;
import br.com.cesarsicas.springstore.domain.cart.dto.DeleteCartProductsDto;
import br.com.cesarsicas.springstore.domain.exceptions.QuantityException;
import br.com.cesarsicas.springstore.domain.product.data.product.ProductRepository;
import br.com.cesarsicas.springstore.domain.user.data.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class CartService {
    @Autowired
    CartRepository repository;


    @Autowired
    ProductRepository productRepository;


    @Autowired
    CartProductRepository cartProductRepository;


    public CartEntity getCartByUser(UserEntity user) {
        return repository.getByUserId(user.getId());
    }


    @Transactional
    public void addCartProducts(AddUpdateCartProductsDto addUpdateCartProductsDto, UserEntity user) throws QuantityException {
        var cart = repository.getByUserId(user.getId());

        for (int i = 0; i < addUpdateCartProductsDto.products().size(); i++) {
            var productToAdd = addUpdateCartProductsDto.products().get(i);
            var product = productRepository.getReferenceById(productToAdd.productId());

            if (productToAdd.quantity() > product.getQuantity()) {
                throw new QuantityException();
            }

            var wasUpdated = updateProductQuantity(cart.getCartProducts(), productToAdd.productId(), productToAdd.quantity());

            if (wasUpdated) {
                continue;
            }

            CartProductEntity cartProductEntity = new CartProductEntity(product, cart, productToAdd.quantity());
            cart.getCartProducts().add(cartProductEntity);
            cartProductRepository.save(cartProductEntity);
        }
    }

    private Boolean updateProductQuantity(List<CartProductEntity> cartProducts, Long productId, int quantity) {
        AtomicInteger counter = new AtomicInteger(0);
        cartProducts.forEach(p -> {
            if (p.getProduct().getId() == productId) {
                p.setQuantity(quantity);
                counter.getAndIncrement();
            }
        });

        return counter.get() > 0;
    }

    @Transactional
    public void updateProduct(AddUpdateCartProductsDto addUpdateCartProductsDto, UserEntity user) {
        var cart = repository.getByUserId(user.getId());

        for (int i = 0; i < addUpdateCartProductsDto.products().size(); i++) {
            var productToAdd = addUpdateCartProductsDto.products().get(i);

            updateProductQuantity(cart.getCartProducts(), productToAdd.productId(), productToAdd.quantity());

        }

    }


    @Transactional
    public void removeProduct(DeleteCartProductsDto removeProducts, UserEntity user) {
        var cart = repository.getByUserId(user.getId());
        cartProductRepository.deleteByCartIdAndProductIds(cart.getId(), removeProducts.products());
    }
}
