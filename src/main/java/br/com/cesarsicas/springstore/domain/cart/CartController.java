package br.com.cesarsicas.springstore.domain.cart;

import br.com.cesarsicas.springstore.domain.cart.dto.GetCartDto;
import br.com.cesarsicas.springstore.domain.user.data.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping
    ResponseEntity getCart(@AuthenticationPrincipal UserEntity user) {
        var cartEntity = cartService.getCartByUser(user);
        return ResponseEntity.ok(  new GetCartDto(cartEntity));

    }


}
