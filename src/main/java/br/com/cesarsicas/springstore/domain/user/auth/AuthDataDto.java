package br.com.cesarsicas.springstore.domain.user.auth;

import jakarta.validation.constraints.NotEmpty;

public record AuthDataDto(
                          @NotEmpty
                          String login, //todo validate email format

                          @NotEmpty
                          String password) {
}