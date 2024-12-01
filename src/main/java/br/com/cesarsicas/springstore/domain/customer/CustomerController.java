package br.com.cesarsicas.springstore.domain.customer;

import br.com.cesarsicas.springstore.domain.customer.dto.*;
import br.com.cesarsicas.springstore.domain.exceptions.PermissionException;
import br.com.cesarsicas.springstore.domain.user.data.UserEntity;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
@SecurityRequirement(name = "bearer-key")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    ResponseEntity saveCustomer(@RequestBody CreateCustomerDto customerDto, @AuthenticationPrincipal UserEntity user) throws PermissionException {
        customerService.saveCustomer(customerDto, user);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    ResponseEntity<GetCustomerDto> getCustomer(@AuthenticationPrincipal UserEntity user) {
        return ResponseEntity.ok(customerService.getCustomer(user));
    }

    @PutMapping
    ResponseEntity updateCustomer(@RequestBody UpdateCustomerDto updateCustomerDto, @AuthenticationPrincipal UserEntity user) {
        customerService.updateCustomer(updateCustomerDto, user);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/address")
    ResponseEntity saveCustomerAddress(@RequestBody CreateCustomerAddressDto createCustomerAddressDto, @AuthenticationPrincipal UserEntity user) {
        customerService.saveCustomerAddress(createCustomerAddressDto, user);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/address")
    ResponseEntity updateCustomerAddress(@RequestBody UpdateCustomerAddressDto updateCustomerAddressDto, @AuthenticationPrincipal UserEntity user) {
        customerService.updateCustomerAddress(updateCustomerAddressDto, user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/address")
    ResponseEntity<List<GetCustomerAddressDto>> getCustomerAddress(@RequestBody UpdateCustomerAddressDto updateCustomerAddressDto, @AuthenticationPrincipal UserEntity user) {
        return ResponseEntity.ok(customerService.getCustomerAddresses(user));
    }

    @DeleteMapping("/address/{addressId}")
    ResponseEntity deleteCustomerAddress(@PathVariable Long addressId, @AuthenticationPrincipal UserEntity user) {
        customerService.deleteCustomerAddress(addressId, user);
        return ResponseEntity.ok().build();
    }



    @PostMapping("/credit-card")
    ResponseEntity saveCustomerCreditCard(@RequestBody CreateCustomerCreditCardDto createCustomerCreditCardDto, @AuthenticationPrincipal UserEntity user) {
        customerService.saveCreditCard(createCustomerCreditCardDto, user);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/credit-card")
    ResponseEntity<List<GetCreditCardDto>> getCustomerCreditCardList(@AuthenticationPrincipal UserEntity user) {
        return ResponseEntity.ok(customerService.getCreditCardList(user));
    }

    @DeleteMapping("/credit-card/{creditCardId}")
    ResponseEntity deleteCustomerCreditCard(@PathVariable Long creditCardId, @AuthenticationPrincipal UserEntity user) {
        customerService.deleteCreditCard(creditCardId, user);
        return ResponseEntity.ok().build();
    }

}
