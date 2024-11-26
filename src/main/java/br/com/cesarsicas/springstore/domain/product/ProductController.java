package br.com.cesarsicas.springstore.domain.product;

import br.com.cesarsicas.springstore.domain.product.dto.ProductDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity list(Pageable pageable) {
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
}
