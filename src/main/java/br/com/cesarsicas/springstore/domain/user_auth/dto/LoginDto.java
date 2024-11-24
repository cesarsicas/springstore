package br.com.cesarsicas.springstore.domain.user_auth.dto;

import jakarta.validation.constraints.NotEmpty;

public record LoginDto(
                          @NotEmpty
                          String login, //todo validate email format

                          @NotEmpty
                          String password) {
}