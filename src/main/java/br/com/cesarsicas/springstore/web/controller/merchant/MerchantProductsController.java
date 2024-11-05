package br.com.cesarsicas.springstore.web.controller.merchant;

import br.com.cesarsicas.springstore.domain.product.ProductDto;
import br.com.cesarsicas.springstore.domain.product_category.ProductCategoryRepository;
import br.com.cesarsicas.springstore.domain.product.ProductEntity;
import br.com.cesarsicas.springstore.domain.product.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("merchant/product")
public class MerchantProductsController {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @GetMapping
    public ResponseEntity list(Pageable pageable) {
        var products = productRepository.findAll(); //todo list only products of this merchant
        return ResponseEntity.ok(products.stream().map(ProductDto::new));
    }

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid ProductDto productDto, UriComponentsBuilder uriBuilder) {
        System.out.println(productDto);

        var category = productCategoryRepository.findById(productDto.category());

        if (category.isPresent()){
            productRepository.save(new ProductEntity(productDto, category.get()));
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.badRequest().build(); //todo check error code
        }
    }

    @PutMapping
    public ResponseEntity update() {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity delete() {
        return ResponseEntity.ok().build();
    }

}
