package br.com.cesarsicas.springstore.web.controller.customer;

import br.com.cesarsicas.springstore.domain.service.ProductCategoryService;
import br.com.cesarsicas.springstore.domain.service.ProductService;
import br.com.cesarsicas.springstore.web.model.ProductCategoryDto;
import br.com.cesarsicas.springstore.web.model.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer/products")
public class CustomerProductsController {


    @Autowired
    ProductService productService;

    @Autowired
    ProductCategoryService productCategoryService;


    @GetMapping
    public ResponseEntity<List<ProductDto>> list(Pageable pageable) {
        var products = productService.getProducts();
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

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<ProductDto>> listByCategory(@PathVariable Long categoryId, Pageable pageable) {

        var result = productService.searchByCategoryId(categoryId);

        return ResponseEntity.ok(result.stream().map(ProductDto::new).toList());
    }

    @GetMapping("/categories")
    public ResponseEntity<List<ProductCategoryDto>> list() {
        return ResponseEntity.ok(productCategoryService.getAllCategories().stream().map(ProductCategoryDto::new).toList());

    }

}
