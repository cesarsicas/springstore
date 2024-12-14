package br.com.cesarsicas.springstore.domain.user_auth;

import br.com.cesarsicas.springstore.domain.user.User;
import br.com.cesarsicas.springstore.domain.user.UserService;
import br.com.cesarsicas.springstore.domain.user.data.UserEntity;
import br.com.cesarsicas.springstore.domain.user_auth.dto.LoginDto;
import br.com.cesarsicas.springstore.domain.user_auth.dto.RegisterDto;
import br.com.cesarsicas.springstore.domain.user_auth.dto.TokenJWTDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    UserService userService;


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
}