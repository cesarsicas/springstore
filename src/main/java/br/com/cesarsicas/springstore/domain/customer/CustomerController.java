package br.com.cesarsicas.springstore.domain.customer;

import br.com.cesarsicas.springstore.domain.customer.dto.*;
import br.com.cesarsicas.springstore.domain.product.ProductService;
import br.com.cesarsicas.springstore.domain.product.dto.ProductDto;
import br.com.cesarsicas.springstore.domain.user.data.UserEntity;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
@SecurityRequirement(name = "bearer-key")
public class CustomerController {

    @Autowired
    ProductService productService;

    @Autowired
    CustomerService customerService;

    @PostMapping
    ResponseEntity saveCustomer(@RequestBody CreateCustomerDto customerDto, @AuthenticationPrincipal UserEntity user) {
        customerService.saveCustomer(customerDto, user);
        return ResponseEntity.ok().build();
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


    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> list(Pageable pageable) {
        var products = productService.getProducts(pageable);
        return ResponseEntity.ok(products.stream().map(ProductDto::new).toList());
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<ProductDto>> search(@RequestParam String s) {
        List<ProductDto> searchResult = productService.searchProducts(s).stream().map(ProductDto::new).toList();
        System.out.println(searchResult);
        return ResponseEntity.ok(searchResult);
    }


    @GetMapping("/products/{id}")
    @Transactional
    public ResponseEntity<ProductDto> details(@PathVariable Long id) {
        return ResponseEntity.ok(new ProductDto(productService.getProductById(id)));
    }

}
