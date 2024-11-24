package br.com.cesarsicas.springstore.domain.customer;

import br.com.cesarsicas.springstore.domain.customer.dto.CreateCustomerDto;
import br.com.cesarsicas.springstore.domain.customer.dto.UpdateCustomerDto;
import br.com.cesarsicas.springstore.domain.user.data.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;


    void saveCustomer(CreateCustomerDto customerDto, UserEntity user) {
        //todo validate document user and role
        repository.save(new CustomerEntity(customerDto, user));
    }

    @Transactional
    void updateCustomer(UpdateCustomerDto customerDto, UserEntity user) {
        //todo validate document user and role

        var customer = repository.findByUser(user);
        customer.setName(customerDto.name());
        customer.setDocument(customerDto.document());

    }

}
