package br.com.cesarsicas.springstore.domain.customer;

import br.com.cesarsicas.springstore.domain.customer.customer_credit_card.CustomerCreditCardEntity;
import br.com.cesarsicas.springstore.domain.customer.customer_credit_card.CustomerCreditCardRepository;
import br.com.cesarsicas.springstore.domain.customer.dto.*;
import br.com.cesarsicas.springstore.domain.customer.customer_address.CustomerAddressEntity;
import br.com.cesarsicas.springstore.domain.customer.customer_address.CustomerAddressRepository;
import br.com.cesarsicas.springstore.domain.exceptions.PermissionException;
import br.com.cesarsicas.springstore.domain.user.Role;
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


    @Autowired
    private CustomerCreditCardRepository customerCreditCardRepository;


    void saveCustomer(CreateCustomerDto customerDto, UserEntity user) throws PermissionException {
        if(user.getRole() != Role.CUSTOMER){
            throw new PermissionException();
        }
        repository.save(new CustomerEntity(customerDto, user));
    }

    @Transactional
    void updateCustomer(UpdateCustomerDto customerDto, UserEntity user) {
        var customer = repository.findByUser(user);
        customer.update(customerDto);
    }

    public GetCustomerDto getCustomer(UserEntity user) {
        return new GetCustomerDto(repository.findByUser(user));
    }

    public void saveCustomerAddress(CreateCustomerAddressDto createCustomerAddressDto, UserEntity user) {
        CustomerEntity customer = repository.findByUser(user);
        customerAddressRepository.save(new CustomerAddressEntity(createCustomerAddressDto, customer));
    }

    @Transactional
    public void updateCustomerAddress(UpdateCustomerAddressDto updateCustomerAddressDto, UserEntity user) {
        var address = customerAddressRepository.searchAddressesByAddressIdAndUserId(updateCustomerAddressDto.id(), user.getId());
        address.update(updateCustomerAddressDto);

    }

    public List<GetCustomerAddressDto> getCustomerAddresses(UserEntity user) {
        return customerAddressRepository.searchAddressesByUserId(user.getId()).stream().map(GetCustomerAddressDto::new).toList();
    }

    public void deleteCustomerAddress(Long addressId, UserEntity user) {
        var address = customerAddressRepository.searchAddressesByAddressIdAndUserId(addressId, user.getId());
        customerAddressRepository.delete(address);

    }


    public void saveCreditCard(CreateCustomerCreditCardDto createCustomerCreditCardDto, UserEntity user) {
        //todo validate creditcard data
        CustomerEntity customer = repository.findByUser(user);
        customerCreditCardRepository.save(new CustomerCreditCardEntity(createCustomerCreditCardDto, customer));
    }


    public List<GetCreditCardDto> getCreditCardList(UserEntity user) {
        return customerCreditCardRepository.searchCreditCardsByUser(user.getId()).stream().map(GetCreditCardDto::new).toList();
    }

    public void deleteCreditCard(Long creditCardId, UserEntity user) {
        var address = customerCreditCardRepository.searchCreditCardsByCreditCardIdAndUserId(creditCardId, user.getId());
        customerCreditCardRepository.delete(address);

    }

}
