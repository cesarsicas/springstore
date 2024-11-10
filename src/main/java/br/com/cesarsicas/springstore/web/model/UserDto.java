package br.com.cesarsicas.springstore.web.model;

import br.com.cesarsicas.springstore.domain.model.User;
import br.com.cesarsicas.springstore.domain.user.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

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
