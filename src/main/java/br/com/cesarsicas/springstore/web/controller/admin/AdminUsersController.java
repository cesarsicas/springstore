package br.com.cesarsicas.springstore.web.controller.admin;

import br.com.cesarsicas.springstore.domain.product_category.ProductCategoryDto;
import br.com.cesarsicas.springstore.domain.user.UserDto;
import br.com.cesarsicas.springstore.domain.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("admin/users")
public class AdminUsersController {

    @Autowired
    UserRepository userRepository;


    @GetMapping
    public ResponseEntity listUsers() {
        return ResponseEntity.ok(userRepository.findAll().stream().map(UserDto::new));
    }


    @PostMapping
    public ResponseEntity saveUsers(@RequestBody @Valid ProductCategoryDto productDto, UriComponentsBuilder uriBuilder) {
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/id")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok().build();
    }
}
