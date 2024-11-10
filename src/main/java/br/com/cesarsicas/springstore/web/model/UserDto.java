package br.com.cesarsicas.springstore.web.model;

import br.com.cesarsicas.springstore.domain.model.User;
import br.com.cesarsicas.springstore.domain.user.Role;

public record UserDto(
        Long id,
        String email,
        String password,
        Role role,
        boolean isActive) {

   public UserDto(User user){
        this(user.id(), user.login(), "", user.role(), user.isActive());
    }
}
