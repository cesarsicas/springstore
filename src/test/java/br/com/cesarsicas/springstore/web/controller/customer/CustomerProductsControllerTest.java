//package br.com.cesarsicas.springstore.web.controller.customer;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@ContextConfiguration
//class CustomerProductsControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    @DisplayName("Should return 403 because isn't authenticated")
//    void list_return403() throws Exception {
//
//        var response = mockMvc.perform(get("/customer/products")).andReturn().getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());
//
//    }
//
//    @Test
//    @DisplayName("Should return 200")
//    @WithMockUser(username = "teste@spring.com", roles = "CUSTOMER")
//    void list_return200() throws Exception {
//
//        var response = mockMvc.perform(get("/customer/products").with(csrf()))
//                .andReturn().getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
//
//    }
//    @Test
//    @DisplayName("Should return 403 because its using a merchant user")
//    @WithMockUser(username = "teste@spring.com", roles = "MERCHANT")
//    void list_return403MerchantRole() throws Exception {
//
//        var response = mockMvc.perform(get("/customer/products").with(csrf()))
//                .andReturn().getResponse();
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());
//
//    }
//}