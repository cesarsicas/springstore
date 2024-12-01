package br.com.cesarsicas.springstore.domain.order;

import br.com.cesarsicas.springstore.domain.cart.CartRepository;
import br.com.cesarsicas.springstore.domain.cart.cart_product.CartProductRepository;
import br.com.cesarsicas.springstore.domain.customer.CustomerRepository;
import br.com.cesarsicas.springstore.domain.customer.customer_address.CustomerAddressEntity;
import br.com.cesarsicas.springstore.domain.customer.customer_address.CustomerAddressRepository;
import br.com.cesarsicas.springstore.domain.customer.customer_credit_card.CustomerCreditCardEntity;
import br.com.cesarsicas.springstore.domain.customer.customer_credit_card.CustomerCreditCardRepository;
import br.com.cesarsicas.springstore.domain.order.dto.CreateOrder;
import br.com.cesarsicas.springstore.domain.order.dto.CreateOrderDto;
import br.com.cesarsicas.springstore.domain.order_product.OrderProductEntity;
import br.com.cesarsicas.springstore.domain.order_product.OrderProductRepository;
import br.com.cesarsicas.springstore.domain.user.data.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerAddressRepository customerAddressRepository;

    @Autowired
    private CustomerCreditCardRepository customerCreditCardRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private CartProductRepository cartProductRepository;




    @Transactional
    void createOrder(CreateOrderDto createOrderDto, UserEntity user){
        var cart = cartRepository.getByUserId(user.getId());

        BigDecimal total = cart.getCartProducts().stream() .map(
                p -> p.getProduct().getValue())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var createOrder = new CreateOrder(
                createOrderDto.addressId(),
                createOrderDto.creditCardId(),
                total,
                LocalDateTime.now(ZoneId.of("GMT-03:00"))
        );

        var customer = customerRepository.findByUser(user);

        //validate owners
        var address = customerAddressRepository.getReferenceById(createOrder.addressId());
        var creditCard = customerCreditCardRepository.getReferenceById(createOrder.creditCardId());



       var order = orderRepository.save(new OrderEntity(
                createOrder,
                customer,
                creditCard,
                address
        ));

        var orderProducts = cart.getCartProducts().stream().map(cp-> new OrderProductEntity(cp, order)).toList();

        orderProductRepository.saveAll(orderProducts);

        cartProductRepository.deleteByCartId(cart.getId());

        //todo trigger email
        //update inventory
        //check if product has the quantity

    }
}
