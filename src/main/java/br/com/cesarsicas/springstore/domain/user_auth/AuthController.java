package br.com.cesarsicas.springstore.domain.user_auth;

import br.com.cesarsicas.springstore.domain.product.ProductService;
import br.com.cesarsicas.springstore.domain.user.User;
import br.com.cesarsicas.springstore.domain.user.UserService;
import br.com.cesarsicas.springstore.domain.user.data.UserEntity;
import br.com.cesarsicas.springstore.domain.user.dto.UserDto;
import br.com.cesarsicas.springstore.domain.user_auth.dto.LoginDto;
import br.com.cesarsicas.springstore.domain.user_auth.dto.RegisterDto;
import br.com.cesarsicas.springstore.domain.user_auth.dto.TokenJWTDto;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    UserService userService;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Autowired
    RestTemplate restTemplate;


    @PostMapping("/login")
    public ResponseEntity<TokenJWTDto> login(@RequestBody @Valid LoginDto data) {
        var token = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = manager.authenticate(token);

        var tokenJWT = tokenService.generateToken((UserEntity) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJWTDto(tokenJWT));

    }

    @PostMapping("/register")
    @Transactional
    public ResponseEntity register(@RequestBody @Valid RegisterDto registerDto) {
        userService.saveUser(new User(registerDto));
        return ResponseEntity.ok().build();
    }

    @PostMapping("/teste")
    @Transactional
    public ResponseEntity teste() {

        ServiceInstance serviceInstance = loadBalancerClient.choose("SPRINGSTORE-PAYMENT-SERVICE");

        String uri = serviceInstance.getUri().toString();

        System.out.println("Uri >>>>> " + uri);

        var result = restTemplate.getForObject(uri + "/payments/api/home", String.class);


        return ResponseEntity.ok("Chegou aqui e retornou : "+result);
    }

}