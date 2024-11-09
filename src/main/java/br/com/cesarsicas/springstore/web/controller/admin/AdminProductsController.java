package br.com.cesarsicas.springstore.web.controller.admin;

import br.com.cesarsicas.springstore.domain.model.ProductCategory;
import br.com.cesarsicas.springstore.domain.service.ProductCategoryService;
import br.com.cesarsicas.springstore.domain.service.ProductService;
import br.com.cesarsicas.springstore.web.model.ProductCategoryDto;
import br.com.cesarsicas.springstore.web.model.ProductDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("admin/products")
public class AdminProductsController {

    @Autowired
    ProductCategoryService productCategoryService;

    @Autowired
    ProductService productService;


    @GetMapping
    public ResponseEntity<List<ProductDto>> list(Pageable pageable) {
        var products = productService.getProducts();
        return ResponseEntity.ok(products.stream().map(ProductDto::new).toList());
    }

    @DeleteMapping("/productId")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/categories")
    public ResponseEntity saveCategory(@RequestBody @Valid ProductCategoryDto productDto, UriComponentsBuilder uriBuilder) {
        productCategoryService.saveCategory(new ProductCategory(productDto));
        return ResponseEntity.ok().build();

    }

    @GetMapping("/categories")
    public ResponseEntity<List<ProductCategoryDto>> listProductCategories() {
        return ResponseEntity.ok(productCategoryService.getAllCategories().stream().map(ProductCategoryDto::new).toList());
    }

}
