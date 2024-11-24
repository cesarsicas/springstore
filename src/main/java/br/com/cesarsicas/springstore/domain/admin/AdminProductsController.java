package br.com.cesarsicas.springstore.domain.admin;

import br.com.cesarsicas.springstore.domain.product_category.ProductCategory;
import br.com.cesarsicas.springstore.domain.product_category.ProductCategoryService;
import br.com.cesarsicas.springstore.domain.product.ProductService;
import br.com.cesarsicas.springstore.domain.product_category.ProductCategoryDto;
import br.com.cesarsicas.springstore.domain.product.dto.ProductDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/products")
@SecurityRequirement(name = "bearer-key")
public class AdminProductsController {

    @Autowired
    ProductCategoryService productCategoryService;

    @Autowired
    ProductService productService;


    @GetMapping
    public ResponseEntity<List<ProductDto>> list(Pageable pageable) {
        var products = productService.getProducts(pageable);
        return ResponseEntity.ok(products.stream().map(ProductDto::new).toList());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/categories")
    public ResponseEntity saveCategory(@RequestBody @Valid ProductCategoryDto productDto) {
        productCategoryService.saveCategory(new ProductCategory(productDto));
        return ResponseEntity.ok().build();

    }

    @GetMapping("/categories")
    public ResponseEntity<List<ProductCategoryDto>> listProductCategories() {
        return ResponseEntity.ok(productCategoryService.getAllCategories().stream().map(ProductCategoryDto::new).toList());
    }

}
