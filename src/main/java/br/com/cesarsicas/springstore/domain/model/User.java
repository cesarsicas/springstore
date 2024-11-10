package br.com.cesarsicas.springstore.domain.model;

import br.com.cesarsicas.springstore.data.user.UserEntity;
import br.com.cesarsicas.springstore.domain.user.Role;
import br.com.cesarsicas.springstore.web.model.UserDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public record User(long id, String login, String password, Role role, boolean isActive) {

    public User(UserEntity userEntity) {
        this(userEntity.getId(), userEntity.getLogin(), "", userEntity.getRole(), userEntity.isActive());
    }

    public User(UserDto userDto) {
        this(userDto.id(), userDto.email(), new BCryptPasswordEncoder().encode(userDto.password()), userDto.role(), userDto.isActive());
    }
}

