package br.com.cesarsicas.springstore.domain.user.dto;

import br.com.cesarsicas.springstore.domain.user.Role;
import br.com.cesarsicas.springstore.domain.user.User;
import jakarta.validation.constraints.NotEmpty;

public record UserDto(
        Long id,
        @NotEmpty
        String email,
        @NotEmpty
        String password,
        Role role,
        boolean isActive) {

   public UserDto(User user){
        this(user.id(), user.login(), "", user.role(), user.isActive());
    }
}
