package br.com.cesarsicas.springstore.domain.user;

public record UserDto(
        Long id,
        String email) {

   public UserDto(User user){
        this(user.getId(), user.getLogin());
    }
}
