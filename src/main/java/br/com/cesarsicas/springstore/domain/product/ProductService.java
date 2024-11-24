package br.com.cesarsicas.springstore.domain.product;

import br.com.cesarsicas.springstore.domain.merchant.MerchantRepository;
import br.com.cesarsicas.springstore.domain.product.data.product.ProductEntity;
import br.com.cesarsicas.springstore.domain.product.data.product.ProductRepository;
import br.com.cesarsicas.springstore.domain.product.data.product_category.ProductCategoryRepository;
import br.com.cesarsicas.springstore.domain.user.data.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;


    @Autowired
    MerchantRepository merchantRepository;


    public List<Product> getProducts(Pageable pageable) {
        return repository.getAllBy(pageable).stream().map(Product::new).toList();
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

    public void saveProduct(Product product, UserEntity user) throws Exception {

        var category = productCategoryRepository.findById(product.category());
        var merchant = merchantRepository.findByUser(user);


        if (category.isEmpty() || merchant==null) {
            throw new Exception("It wasn't possible to save the product. Verify the category or merchant");
        }

        repository.save(new ProductEntity(product, category.get(), merchant));

    }

    public Boolean updateProduct(Product product, UserEntity user) {

        ProductEntity productEntity = repository.getReferenceById(product.id());

        if (!Objects.equals(productEntity.getMerchant().getId(), user.getId())) {
            return false;
        }

        var category = productCategoryRepository.getReferenceById(product.category());

        productEntity.setCategory(category);

        if (product.description()!=null){
            productEntity.setDescription(product.description());
        }

        if (product.name()!=null){
            productEntity.setName(product.name());
        }

        if (product.value() !=null){
            productEntity.setValue(product.value());
        }

        return  true;

    }

    public Boolean deleteProductMerchant(Long id, UserEntity user) {

        ProductEntity productEntity = repository.getReferenceById(id);

        if (!Objects.equals(productEntity.getMerchant().getId(), user.getId())) {
            return false;
        }

       repository.delete(productEntity);

        return  true;
    }
}
