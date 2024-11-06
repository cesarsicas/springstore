package br.com.cesarsicas.springstore.web.controller.customer;

import br.com.cesarsicas.springstore.domain.product.ProductDto;
import br.com.cesarsicas.springstore.domain.product.ProductRepository;
import br.com.cesarsicas.springstore.domain.product_category.ProductCategoryDto;
import br.com.cesarsicas.springstore.domain.product_category.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer/products")
public class CustomerProductsController {

    @Autowired
    ProductRepository repository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;


    @GetMapping
    public ResponseEntity list(Pageable pageable) {
        var products = repository.findAll();
        return ResponseEntity.ok(products.stream().map(ProductDto::new));
    }

    @GetMapping("/search")
    public ResponseEntity search(@RequestParam String s) {
        var searchResult = repository.findByNameOrDescriptionContainingIgnoreCase(s,s);

        System.out.println(searchResult);

        return ResponseEntity.ok(searchResult);
    }


    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity details(@PathVariable Long id) {
        return ResponseEntity.ok(new ProductDto(repository.getReferenceById(id)));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity listByCategory(@PathVariable Long categoryId, Pageable pageable) {
        //var category = productCategoryRepository.getReferenceById(id);
        var result = repository.searchByCategoryId(categoryId);

        return ResponseEntity.ok(result.stream().map(ProductDto::new));
    }

    @GetMapping("/categories")
    public ResponseEntity list() {
        return ResponseEntity.ok(productCategoryRepository.findAll().stream().map(ProductCategoryDto::new));

    }

}
