package br.com.cesarsicas.springstore.domain.service;

import br.com.cesarsicas.springstore.data.product.ProductEntity;
import br.com.cesarsicas.springstore.data.product.ProductRepository;
import br.com.cesarsicas.springstore.data.product_category.ProductCategoryRepository;
import br.com.cesarsicas.springstore.data.user.UserEntity;
import br.com.cesarsicas.springstore.domain.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;


    public List<Product> getProducts() {
        return repository.findAll().stream().map(Product::new).toList();
    }

    public List<Product> searchProducts(String s) {
        return repository.findByNameOrDescriptionContainingIgnoreCase(s, s).stream().map(Product::new).toList();
    }

    public Product getProductById(Long id) {
        return new Product(repository.getReferenceById(id));
    }

    public List<Product> searchByCategoryId(Long categoryId) {
        return repository.searchByCategoryId(categoryId).stream().map(Product::new).toList();
    }

    public void deleteProduct(Long id) {
        ProductEntity productEntity = repository.getReferenceById(id);
        repository.delete(productEntity);

    }

    public List<Product> searchByUser(Long id) {
        return repository.searchByUser(id).stream().map(Product::new).toList();
    }

    public Boolean saveProduct(Product product, UserEntity user) {

        var category = productCategoryRepository.findById(product.category());

        if (category.isPresent()) {
            repository.save(new ProductEntity(product, category.get(), user));
            return true;
        } else {
            return false;
        }

    }
}
