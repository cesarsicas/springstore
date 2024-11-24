package br.com.cesarsicas.springstore.domain.user;

import br.com.cesarsicas.springstore.domain.user.data.UserEntity;
import br.com.cesarsicas.springstore.domain.user.dto.UserDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public record User(long id, String login, String password, Role role, boolean isActive) {

    public User(UserEntity userEntity) {
        this(userEntity.getId(), userEntity.getLogin(), "", userEntity.getRole(), userEntity.isActive());
    }

    public User(UserDto userDto) {
        this(userDto.id(), userDto.email(), new BCryptPasswordEncoder().encode(userDto.password()), userDto.role(), userDto.isActive());
    }
}

