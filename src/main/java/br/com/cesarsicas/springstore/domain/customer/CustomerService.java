package br.com.cesarsicas.springstore.domain.customer;

import br.com.cesarsicas.springstore.domain.customer.dto.*;
import br.com.cesarsicas.springstore.domain.customer_address.CustomerAddressEntity;
import br.com.cesarsicas.springstore.domain.customer_address.CustomerAddressRepository;
import br.com.cesarsicas.springstore.domain.user.data.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private CustomerAddressRepository customerAddressRepository;


    void saveCustomer(CreateCustomerDto customerDto, UserEntity user) {
        //todo validate document user and role
        repository.save(new CustomerEntity(customerDto, user));
    }

    @Transactional
    void updateCustomer(UpdateCustomerDto customerDto, UserEntity user) {
        //todo validate document user and role
        var customer = repository.findByUser(user);
        customer.update(customerDto);
    }

    public void saveCustomerAddress(CreateCustomerAddressDto createCustomerAddressDto, UserEntity user) {
        //todo validate user
        CustomerEntity customer = repository.findByUser(user);
        customerAddressRepository.save(new CustomerAddressEntity(createCustomerAddressDto, customer));
    }

    @Transactional
    public void updateCustomerAddress(UpdateCustomerAddressDto updateCustomerAddressDto, UserEntity user) {
        //todo validate user
        var address = customerAddressRepository.getReferenceById(updateCustomerAddressDto.id());
        address.update(updateCustomerAddressDto);

    }

    public List<GetCustomerAddressDto> getCustomerAddresses(UserEntity user) {
        return customerAddressRepository.searchAddressesByUserId(user.getId()).stream().map(GetCustomerAddressDto::new).toList();
    }
}
