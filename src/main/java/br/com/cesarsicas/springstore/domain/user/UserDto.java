package br.com.cesarsicas.springstore.domain.user;

public record UserDto(
        Long id,
        String email,
        String password,
        Role role) {

   public UserDto(UserEntity userEntity){
        this(userEntity.getId(), userEntity.getLogin(), "", userEntity.getRole());
    }
}
