package br.com.cesarsicas.springstore.domain.customer;

import br.com.cesarsicas.springstore.domain.product_category.ProductCategoryService;
import br.com.cesarsicas.springstore.domain.product.ProductService;
import br.com.cesarsicas.springstore.domain.product_category.ProductCategoryDto;
import br.com.cesarsicas.springstore.domain.product.dto.ProductDto;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer/products")
@SecurityRequirement(name = "bearer-key")
public class CustomerProductsController {


    @Autowired
    ProductService productService;

    @Autowired
    ProductCategoryService productCategoryService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> list(Pageable pageable) {
        var products = productService.getProducts(pageable);
        return ResponseEntity.ok(products.stream().map(ProductDto::new).toList());
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> search(@RequestParam String s) {
        List<ProductDto> searchResult = productService.searchProducts(s).stream().map(ProductDto::new).toList();
        System.out.println(searchResult);
        return ResponseEntity.ok(searchResult);
    }


    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<ProductDto> details(@PathVariable Long id) {
        return ResponseEntity.ok(new ProductDto(productService.getProductById(id)));
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<List<ProductDto>> listByCategory(@PathVariable Long id) {

        var result = productService.searchByCategoryId(id);

        return ResponseEntity.ok(result.stream().map(ProductDto::new).toList());
    }

    @GetMapping("/categories")
    public ResponseEntity<List<ProductCategoryDto>> list() {
        return ResponseEntity.ok(productCategoryService.getAllCategories().stream().map(ProductCategoryDto::new).toList());

    }

}
