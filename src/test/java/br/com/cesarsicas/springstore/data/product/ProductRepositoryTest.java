package br.com.cesarsicas.springstore.data.product;

import br.com.cesarsicas.springstore.data.product_category.ProductCategoryEntity;
import br.com.cesarsicas.springstore.data.user.UserEntity;
import br.com.cesarsicas.springstore.domain.model.Product;
import br.com.cesarsicas.springstore.domain.model.ProductCategory;
import br.com.cesarsicas.springstore.domain.model.User;
import br.com.cesarsicas.springstore.domain.user.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class ProductRepositoryTest {

    @Autowired
    TestEntityManager em;

    @Autowired
    ProductRepository productRepository;


    @Test
    @DisplayName("Assert that filter by category is working correctly")
    void searchByCategoryId() {
        UserEntity user = new UserEntity(new User(1L, "teste@teste.com.br", "", Role.ADMIN, true));
        em.persist(user);

        ProductCategoryEntity category1 = new ProductCategoryEntity(new ProductCategory("Category 1", "Category 1"));
        ProductCategoryEntity category2 = new ProductCategoryEntity(new ProductCategory("Category 2", "Category 2"));
        ProductCategoryEntity category3 = new ProductCategoryEntity(new ProductCategory("Category 3", "Category 3"));
        em.persist(category1);
        em.persist(category2);
        em.persist(category3);


        ProductEntity p1 = new ProductEntity(new Product(0l, "Product 1", "Product ", BigDecimal.TEN, category1.getId(), ""), category1, user);
        ProductEntity p2 = new ProductEntity(new Product(0l, "Product 2 ", "Product ", BigDecimal.TEN, category2.getId(), ""), category2, user);
        ProductEntity p3 = new ProductEntity(new Product(0l, "Product 3 ", "Product ", BigDecimal.TEN, category2.getId(), ""), category2, user);
        em.persist(p1);
        em.persist(p2);


        var resultCategoryId1 = productRepository.searchByCategoryId(category1.getId());
        resultCategoryId1.forEach(p ->
                assertThat(p.getCategory()).isEqualTo(category1));


    }

    @Test
    void searchByUser() {
    }

}