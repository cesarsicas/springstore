package br.com.cesarsicas.springstore.domain.merchant;

import br.com.cesarsicas.springstore.domain.user.data.UserEntity;
import br.com.cesarsicas.springstore.domain.product.Product;
import br.com.cesarsicas.springstore.domain.product_category.ProductCategoryService;
import br.com.cesarsicas.springstore.domain.product.ProductService;
import br.com.cesarsicas.springstore.domain.product_category.ProductCategoryDto;
import br.com.cesarsicas.springstore.domain.product.dto.ProductDto;
import br.com.cesarsicas.springstore.domain.product.dto.UpdateProductDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("merchant/products")
@SecurityRequirement(name = "bearer-key")
public class MerchantProductsController {

    @Autowired
    ProductService productService;

    @Autowired
    ProductCategoryService productCategoryService;



    @GetMapping
    public ResponseEntity<List<ProductDto>> list(Pageable pageable, @AuthenticationPrincipal UserEntity user) {
        var products = productService.searchByUser(user.getId());
        return ResponseEntity.ok(products.stream().map(ProductDto::new).toList());
    }

    @Transactional
    @PostMapping
    public ResponseEntity save(@RequestBody @Valid ProductDto productDto,
                               @AuthenticationPrincipal UserEntity user) {

        try{
            productService.saveProduct(new Product(productDto), user);
            return ResponseEntity.ok().build();
        }
        catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Transactional
    @PutMapping
    public ResponseEntity update(@RequestBody @Valid UpdateProductDto updateProductDto,
                                 @AuthenticationPrincipal UserEntity user) {

        var isSuccess = productService.updateProduct(new Product(updateProductDto), user);

        if (isSuccess) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/categories")
    public ResponseEntity list() {
        return ResponseEntity.ok(productCategoryService.getAllCategories().stream().map(ProductCategoryDto::new));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id,
                                 @AuthenticationPrincipal UserEntity user) {

        var isSuccess = productService.deleteProductMerchant(id, user);

        if (isSuccess) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

}