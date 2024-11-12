package br.com.cesarsicas.springstore.web.controller.merchant;

import br.com.cesarsicas.springstore.data.user.UserEntity;
import br.com.cesarsicas.springstore.domain.model.User;
import br.com.cesarsicas.springstore.domain.user.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest()
@AutoConfigureMockMvc
@ContextConfiguration
class MerchantProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @DisplayName("Should return 400 because the body is wrong")
    void save_400() throws Exception {

        UserEntity userEntity = new UserEntity(new User(1l, "teste@teste.com.br", "123", Role.MERCHANT, true));

        var response = mockMvc.perform(post("/merchant/products")
                .with(user(userEntity))).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

    }
}