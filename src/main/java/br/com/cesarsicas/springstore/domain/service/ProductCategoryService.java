package br.com.cesarsicas.springstore.domain.service;

import br.com.cesarsicas.springstore.data.product_category.ProductCategoryEntity;
import br.com.cesarsicas.springstore.data.product_category.ProductCategoryRepository;
import br.com.cesarsicas.springstore.domain.model.ProductCategory;
import br.com.cesarsicas.springstore.web.model.ProductCategoryDto;
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
