package br.com.cesarsicas.springstore.infra;

import br.com.cesarsicas.springstore.domain.user.Role;
import br.com.cesarsicas.springstore.domain.user.RolePermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize ->
                        authorize
                                .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/products/**").permitAll()

                                .requestMatchers( "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()


                                .requestMatchers("/customer/**").hasAnyRole(Role.CUSTOMER.name())
                                .requestMatchers(HttpMethod.POST, "/customer/**").hasAnyAuthority(RolePermissions.CUSTOMER_CREATE.name())
                                .requestMatchers(HttpMethod.GET, "/customer/**").hasAnyAuthority(RolePermissions.CUSTOMER_READ.name())
                                .requestMatchers(HttpMethod.PUT, "/customer/**").hasAnyAuthority(RolePermissions.CUSTOMER_UPDATE.name())
                                .requestMatchers(HttpMethod.DELETE, "/customer/**").hasAnyAuthority(RolePermissions.CUSTOMER_DELETE.name())

                                .requestMatchers("/cart/**").hasAnyRole(Role.CUSTOMER.name())
                                .requestMatchers(HttpMethod.POST, "/cart/**").hasAnyAuthority(RolePermissions.CUSTOMER_CREATE.name())
                                .requestMatchers(HttpMethod.GET, "/cart/**").hasAnyAuthority(RolePermissions.CUSTOMER_READ.name())
                                .requestMatchers(HttpMethod.PUT, "/cart/**").hasAnyAuthority(RolePermissions.CUSTOMER_UPDATE.name())
                                .requestMatchers(HttpMethod.DELETE, "/cart/**").hasAnyAuthority(RolePermissions.CUSTOMER_DELETE.name())


                                .requestMatchers("/order/**").hasAnyRole(Role.CUSTOMER.name())
                                .requestMatchers(HttpMethod.POST, "/cart/**").hasAnyAuthority(RolePermissions.CUSTOMER_CREATE.name())
                                .requestMatchers(HttpMethod.GET, "/cart/**").hasAnyAuthority(RolePermissions.CUSTOMER_READ.name())


                                .requestMatchers("/merchant/**").hasAnyRole(Role.MERCHANT.name())
                                .requestMatchers(HttpMethod.POST, "/merchant/**").hasAnyAuthority(RolePermissions.MERCHANT_CREATE.name())
                                .requestMatchers(HttpMethod.GET, "/merchant/**").hasAnyAuthority(RolePermissions.MERCHANT_READ.name())
                                .requestMatchers(HttpMethod.PUT, "/merchant/**").hasAnyAuthority(RolePermissions.MERCHANT_UPDATE.name())
                                .requestMatchers(HttpMethod.DELETE, "/merchant/**").hasAnyAuthority(RolePermissions.MERCHANT_DELETE.name())

                                .requestMatchers("/admin/**").hasAnyRole(Role.ADMIN.name())
                                .requestMatchers(HttpMethod.POST, "/admin/**").hasAnyAuthority(RolePermissions.ADMIN_CREATE.name())
                                .requestMatchers(HttpMethod.GET, "/admin/**").hasAnyAuthority(RolePermissions.ADMIN_READ.name())
                                .requestMatchers(HttpMethod.PUT, "/admin/**").hasAnyAuthority(RolePermissions.ADMIN_UPDATE.name())
                                .requestMatchers(HttpMethod.DELETE, "/admin/**").hasAnyAuthority(RolePermissions.ADMIN_DELETE.name())

                                .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
