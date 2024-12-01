package br.com.cesarsicas.springstore.domain.cart;

import br.com.cesarsicas.springstore.domain.cart.dto.DeleteCartProductsDto;
import br.com.cesarsicas.springstore.domain.cart.dto.GetCartDto;
import br.com.cesarsicas.springstore.domain.cart.dto.AddUpdateCartProductsDto;
import br.com.cesarsicas.springstore.domain.exceptions.QuantityException;
import br.com.cesarsicas.springstore.domain.user.data.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;



    @GetMapping
    ResponseEntity<GetCartDto> getCart(@AuthenticationPrincipal UserEntity user) {
        var cartEntity = cartService.getCartByUser(user);
        return ResponseEntity.ok(new GetCartDto(cartEntity));
    }

    @PostMapping("/products")
    ResponseEntity addCartProducts(@RequestBody AddUpdateCartProductsDto addCartProducts, @AuthenticationPrincipal UserEntity user) throws QuantityException {
        cartService.addCartProducts(addCartProducts, user);
        return ResponseEntity.ok().build();

    }


    @PutMapping("/products")
    ResponseEntity updateCartProducts(@RequestBody AddUpdateCartProductsDto addUpdateCartProductsDto, @AuthenticationPrincipal UserEntity user) {
        cartService.updateProduct(addUpdateCartProductsDto, user);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping("/products")
    ResponseEntity deleteCartProducts(@RequestBody DeleteCartProductsDto deleteCartProductsDto, @AuthenticationPrincipal UserEntity user) {
        cartService.removeProduct(deleteCartProductsDto, user);
        return ResponseEntity.ok().build();

    }

    @PostMapping("/finish")
    ResponseEntity cartFinish(@RequestBody AddUpdateCartProductsDto addCartProducts, @AuthenticationPrincipal UserEntity user) throws QuantityException {
        cartService.addCartProducts(addCartProducts, user);
        return ResponseEntity.ok().build();

    }


}
