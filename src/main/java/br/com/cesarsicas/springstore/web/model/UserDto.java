package br.com.cesarsicas.springstore.web.model;

import br.com.cesarsicas.springstore.data.user.UserEntity;
import br.com.cesarsicas.springstore.domain.user.Role;

public record UserDto(
        Long id,
        String email,
        String password,
        Role role) {

   public UserDto(UserEntity userEntity){
        this(userEntity.getId(), userEntity.getLogin(), "", userEntity.getRole());
    }
}
