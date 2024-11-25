package br.com.cesarsicas.springstore.data.utils;

import br.com.cesarsicas.springstore.domain.merchant.MerchantEntity;
import br.com.cesarsicas.springstore.domain.product.data.product.ProductEntity;
import br.com.cesarsicas.springstore.domain.product.data.product_category.ProductCategoryEntity;
import br.com.cesarsicas.springstore.domain.product.Product;
import br.com.cesarsicas.springstore.domain.product_category.ProductCategory;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.math.BigDecimal;

public class RepositoryUtils {

    public static ProductCategoryEntity insertMockProductCategories(TestEntityManager em) {
        ProductCategoryEntity category1 = new ProductCategoryEntity(new ProductCategory("Category ", "Category "));
        em.persist(category1);

        return category1;


    }

    public static ProductEntity insertMockProduct(TestEntityManager em, ProductCategoryEntity category,MerchantEntity merchant) {



        ProductEntity p1 = new ProductEntity(new Product(0l, "Product", "Product ", BigDecimal.TEN, category.getId(), ""), category, merchant);
        em.persist(p1);
        return p1;
    }
}
