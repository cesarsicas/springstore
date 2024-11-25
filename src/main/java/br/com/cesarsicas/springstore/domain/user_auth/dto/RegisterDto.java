package br.com.cesarsicas.springstore.domain.user_auth.dto;

import br.com.cesarsicas.springstore.domain.user.Role;
import jakarta.validation.constraints.NotEmpty;

public record RegisterDto(
        String login,
        @NotEmpty
        String password,
        Role role) {
}
