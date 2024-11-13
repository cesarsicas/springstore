package br.com.cesarsicas.springstore.web.controller.merchant;

import br.com.cesarsicas.springstore.data.user.UserEntity;
import br.com.cesarsicas.springstore.domain.model.User;
import br.com.cesarsicas.springstore.domain.service.ProductService;
import br.com.cesarsicas.springstore.domain.user.Role;
import br.com.cesarsicas.springstore.web.model.ProductDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest()
@AutoConfigureMockMvc
@ContextConfiguration
@AutoConfigureJsonTesters
class MerchantProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    JacksonTester<ProductDto> productDto;

    @MockBean
    ProductService productService; //to prevent spring call the real service object

    @Test
    @DisplayName("Should return 400 because the body is wrong")
    void save_400() throws Exception {

        UserEntity userEntity = new UserEntity(new User(1l, "teste@teste.com.br", "123", Role.MERCHANT, true));

        var response = mockMvc.perform(post("/merchant/products")
                .with(user(userEntity))).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }

    @Test
    @DisplayName("Should return 200 because everything is correct")
    void save_200() throws Exception {

        when(productService.saveProduct(any(), any())).thenReturn(true);

        UserEntity userEntity = new UserEntity(new User(1l, "teste@teste.com.br", "123", Role.MERCHANT, true));

        var response = mockMvc.perform(post("/merchant/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(productDto.write(new ProductDto("Product name", "Description", BigDecimal.valueOf(500), 1l, "")).getJson())
                .with(user(userEntity))

        ).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }
}