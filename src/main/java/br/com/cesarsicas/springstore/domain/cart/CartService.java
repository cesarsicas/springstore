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

            if (productToAdd.quantity() > product.getQuantity()){
                throw new QuantityException();
            }

            CartProductEntity cartProductEntity = new CartProductEntity(product, cart, productToAdd.quantity());
            cart.getCartProducts().add(cartProductEntity);
            cartProductRepository.save(cartProductEntity);
        }
    }

    @Transactional
    public void updateProduct(AddUpdateCartProductsDto addUpdateCartProductsDto, UserEntity user) {
        var cart = repository.getByUserId(user.getId());

        for (int i = 0; i < addUpdateCartProductsDto.products().size(); i++) {
            var productToAdd = addUpdateCartProductsDto.products().get(i);

            cart.getCartProducts().forEach(p -> {
                if (p.getProduct().getId() == productToAdd.productId()) {
                    p.setQuantity(productToAdd.quantity());
                }
            });

        }

    }


    @Transactional
    public void removeProduct(DeleteCartProductsDto removeProducts, UserEntity user) {
        var cart = repository.getByUserId(user.getId());
        cartProductRepository.deleteByCartIdAndProductIds(cart.getId(), removeProducts.products());
    }
}
