package br.com.cesarsicas.springstore.web.controller.merchant;

import br.com.cesarsicas.springstore.domain.model.Product;
import br.com.cesarsicas.springstore.domain.service.ProductCategoryService;
import br.com.cesarsicas.springstore.domain.service.ProductService;
import br.com.cesarsicas.springstore.web.model.ProductDto;
import br.com.cesarsicas.springstore.data.product.ProductEntity;
import br.com.cesarsicas.springstore.data.product.ProductRepository;
import br.com.cesarsicas.springstore.web.model.UpdateProductDto;
import br.com.cesarsicas.springstore.web.model.ProductCategoryDto;
import br.com.cesarsicas.springstore.data.product_category.ProductCategoryRepository;
import br.com.cesarsicas.springstore.data.user.UserEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("merchant/product")
public class MerchantProductsController {

    @Autowired
    ProductService productService;



    @GetMapping
    public ResponseEntity<List<ProductDto>> list(Pageable pageable, @AuthenticationPrincipal UserEntity user) {
        var products = productService.searchByUser(user.getId());
        return ResponseEntity.ok(products.stream().map(ProductDto::new).toList());
    }

    @Transactional
    @PostMapping
    public ResponseEntity save(@RequestBody @Valid ProductDto productDto,
                               UriComponentsBuilder uriBuilder,
                               @AuthenticationPrincipal UserEntity user) {

        Boolean isSuccess = productService.saveProduct(new Product(productDto), user);

        if (isSuccess) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Transactional
    @PutMapping
    public ResponseEntity update(@RequestBody @Valid UpdateProductDto updateProductDto,
                                 @AuthenticationPrincipal UserEntity user) {

        ProductEntity productEntity = productRepository.getReferenceById(updateProductDto.id());

        if (!Objects.equals(productEntity.getUser().getId(), user.getId())) {
            return ResponseEntity.badRequest().build();
        }

        var category = productCategoryRepository.getReferenceById(updateProductDto.category());

        productEntity.atualizar(updateProductDto, category);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/categories")
    public ResponseEntity list() {
        return ResponseEntity.ok(productCategoryRepository.findAll().stream().map(ProductCategoryDto::new));
    }

    @DeleteMapping("/productId")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id,
                                 @AuthenticationPrincipal UserEntity user) {
        ProductEntity productEntity = productRepository.getReferenceById(id);

        if (!Objects.equals(productEntity.getUser().getId(), user.getId())) {
            return ResponseEntity.badRequest().build();
        }

        productRepository.delete(productEntity);
        return ResponseEntity.ok().build();
    }

}
