package br.com.cesarsicas.springstore.web.controller.admin;

import br.com.cesarsicas.springstore.domain.product.ProductEntity;
import br.com.cesarsicas.springstore.domain.product_category.ProductCategoryDto;
import br.com.cesarsicas.springstore.domain.product_category.ProductCategoryEntity;
import br.com.cesarsicas.springstore.domain.product_category.ProductCategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("admin/users")
public class AdminUsersController {

    @Autowired
    ProductCategoryRepository productCategoryRepository;


    @GetMapping
    public ResponseEntity listUsers() {
        return ResponseEntity.ok(productCategoryRepository.findAll().stream().map(ProductCategoryDto::new));
    }


    @PostMapping
    public ResponseEntity saveUsers(@RequestBody @Valid ProductCategoryDto productDto, UriComponentsBuilder uriBuilder) {
        System.out.println(productDto);
        productCategoryRepository.save(new ProductCategoryEntity(productDto));
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/userId")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
}
