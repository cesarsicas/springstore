package br.com.cesarsicas.springstore.web.controller.admin;

import br.com.cesarsicas.springstore.web.model.UserDto;
import br.com.cesarsicas.springstore.data.user.UserEntity;
import br.com.cesarsicas.springstore.data.user.UserRepository;
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
        return ResponseEntity.ok(userRepository.findAllByIsActiveTrue().stream().map(UserDto::new));
    }


    @PostMapping
    @Transactional
    public ResponseEntity saveUsers(@RequestBody @Valid UserDto userDto, UriComponentsBuilder uriBuilder) {
        UserEntity userEntity = new UserEntity(userDto);
        userRepository.save(userEntity);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUser(@PathVariable Long id) {
        var user = userRepository.getReferenceById(id);
        user.setActive(false);
        return ResponseEntity.ok().build();
    }
}
