package br.com.cesarsicas.springstore.web.controller.admin;

import br.com.cesarsicas.springstore.domain.product_category.ProductCategoryDto;
import br.com.cesarsicas.springstore.domain.product_category.ProductCategoryEntity;
import br.com.cesarsicas.springstore.domain.product_category.ProductCategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("admin/product_categories")
public class ProductCategoriesController {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid ProductCategoryDto productDto, UriComponentsBuilder uriBuilder) {
        System.out.println(productDto);
        productCategoryRepository.save(new ProductCategoryEntity(productDto));
        return ResponseEntity.ok().build();

    }

        @GetMapping
        public ResponseEntity list() {
            return ResponseEntity.ok(productCategoryRepository.findAll().stream().map(ProductCategoryDto::new));

        }
}
