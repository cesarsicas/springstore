package br.com.cesarsicas.springstore.domain.order;

import br.com.cesarsicas.springstore.domain.order.dto.CreateOrderDto;
import br.com.cesarsicas.springstore.domain.user.data.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    ResponseEntity createOrder(@RequestBody CreateOrderDto createOrderDto, @AuthenticationPrincipal UserEntity user) {
        orderService.createOrder(createOrderDto, user);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    ResponseEntity getOrder() {
        return ResponseEntity.ok().build();
    }

}
