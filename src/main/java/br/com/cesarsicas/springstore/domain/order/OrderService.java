package br.com.cesarsicas.springstore.domain.order;

import br.com.cesarsicas.springstore.domain.cart.CartRepository;
import br.com.cesarsicas.springstore.domain.cart.cart_product.CartProductRepository;
import br.com.cesarsicas.springstore.domain.customer.CustomerRepository;
import br.com.cesarsicas.springstore.domain.customer.customer_address.CustomerAddressRepository;
import br.com.cesarsicas.springstore.domain.customer.customer_credit_card.CustomerCreditCardRepository;
import br.com.cesarsicas.springstore.domain.exceptions.PaymentAuthorizationDenied;
import br.com.cesarsicas.springstore.domain.order.dto.AuthorizePaymentDto;
import br.com.cesarsicas.springstore.domain.order.dto.CreateOrder;
import br.com.cesarsicas.springstore.domain.order.dto.CreateOrderDto;
import br.com.cesarsicas.springstore.domain.order.dto.CustomerEmail;
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


    @Autowired
    private PaymentAuthorizationApiClient paymentAuthorizationApiClient;

    @Autowired
    private SendRabbitMQMessageService sendRabbitMQMessageService;


    @Transactional
    void createOrder(CreateOrderDto createOrderDto, UserEntity user) throws PaymentAuthorizationDenied {
        var cart = cartRepository.getByUserId(user.getId());

        BigDecimal total = cart.getCartProducts().stream().map(
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

        //todo add cvv and card owner to CreditCard entiy
        var authorizationResult = paymentAuthorizationApiClient.authorizePayment(new AuthorizePaymentDto(
                creditCard.getCardNumber(),
                "123",
                creditCard.getBrand(),
                creditCard.getExpDate(),
                "CardOwner"
        ));


        if (authorizationResult) {
            var order = orderRepository.save(new OrderEntity(
                    createOrder,
                    customer,
                    creditCard,
                    address
            ));

            var orderProducts = cart.getCartProducts().stream().map(cp -> new OrderProductEntity(cp, order)).toList();

            orderProductRepository.saveAll(orderProducts);

            //cartProductRepository.deleteByCartId(cart.getId());

            sendRabbitMQMessageService.sendCustomerEmail(
                    new CustomerEmail("test@test.com.br", "Title", "message"));

        } else {
            throw new PaymentAuthorizationDenied();
        }

        //todo trigger email
        //update inventory

    }


}
