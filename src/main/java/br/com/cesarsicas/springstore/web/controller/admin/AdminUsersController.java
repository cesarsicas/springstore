package br.com.cesarsicas.springstore.web.controller.admin;

import br.com.cesarsicas.springstore.domain.model.User;
import br.com.cesarsicas.springstore.domain.service.UserService;
import br.com.cesarsicas.springstore.web.model.UserDto;
import br.com.cesarsicas.springstore.data.user.UserEntity;
import br.com.cesarsicas.springstore.data.user.UserRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("admin/users")
@SecurityRequirement(name = "bearer-key")
public class AdminUsersController {

    @Autowired
    UserService userService;


    @GetMapping
    public ResponseEntity<List<UserDto>> listUsers() {
        return ResponseEntity.ok(userService.getAllUsers().stream().map(UserDto::new).toList());
    }


    @PostMapping
    @Transactional
    public ResponseEntity saveUsers(@RequestBody @Valid UserDto userDto) {
        userService.saveUser(new User(userDto));
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.softDeleteUser(id);
        return ResponseEntity.ok().build();
    }
}
