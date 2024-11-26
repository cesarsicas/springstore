package br.com.cesarsicas.springstore.domain.cart;

import br.com.cesarsicas.springstore.domain.customer.CustomerRepository;
import br.com.cesarsicas.springstore.domain.user.data.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    CartRepository repository;

    @Autowired
    CustomerRepository customerRepository;

    public CartEntity getCartByUser(UserEntity user){
        var customer = customerRepository.findByUser(user);
        return repository.findByCustomer(customer);
    }


}
