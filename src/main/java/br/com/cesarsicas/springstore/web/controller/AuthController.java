package br.com.cesarsicas.springstore.web.controller;

import br.com.cesarsicas.springstore.web.model.AuthDataDto;
import br.com.cesarsicas.springstore.web.model.TokenJWTDto;
import br.com.cesarsicas.springstore.data.user.UserEntity;
import br.com.cesarsicas.springstore.domain.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenJWTDto> login(@RequestBody @Valid AuthDataDto data) {
        var token = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = manager.authenticate(token);

        var tokenJWT = tokenService.generateToken((UserEntity) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJWTDto(tokenJWT));

    }
}