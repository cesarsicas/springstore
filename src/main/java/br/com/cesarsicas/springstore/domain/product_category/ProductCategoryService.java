package br.com.cesarsicas.springstore.domain.product_category;

import br.com.cesarsicas.springstore.domain.product.data.product_category.ProductCategoryEntity;
import br.com.cesarsicas.springstore.domain.product.data.product_category.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    public List<ProductCategory> getAllCategories(){
        return productCategoryRepository.findAll().stream().map(ProductCategory::new).toList();
    }

    public void saveCategory(ProductCategory productCategory) {
        productCategoryRepository.save(new ProductCategoryEntity(productCategory));
    }
}
