package br.com.cesarsicas.springstore.web.controller.admin;

import br.com.cesarsicas.springstore.domain.product.ProductDto;
import br.com.cesarsicas.springstore.domain.product.ProductEntity;
import br.com.cesarsicas.springstore.domain.product.ProductRepository;
import br.com.cesarsicas.springstore.domain.product_category.ProductCategoryDto;
import br.com.cesarsicas.springstore.domain.product_category.ProductCategoryEntity;
import br.com.cesarsicas.springstore.domain.product_category.ProductCategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("admin/products")
public class AdminProductsController {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    ProductRepository productRepository;


    @GetMapping
    public ResponseEntity list(Pageable pageable) {
        var products = productRepository.findAll();
        return ResponseEntity.ok(products.stream().map(ProductDto::new));
    }

    @DeleteMapping("/productId")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        ProductEntity productEntity = productRepository.getReferenceById(id);
        productRepository.delete(productEntity);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/categories")
    public ResponseEntity saveCategory(@RequestBody @Valid ProductCategoryDto productDto, UriComponentsBuilder uriBuilder) {
        System.out.println(productDto);
        productCategoryRepository.save(new ProductCategoryEntity(productDto));
        return ResponseEntity.ok().build();

    }

    @GetMapping("/categories")
    public ResponseEntity listProductCategories() {
        return ResponseEntity.ok(productCategoryRepository.findAll().stream().map(ProductCategoryDto::new));
    }

}
