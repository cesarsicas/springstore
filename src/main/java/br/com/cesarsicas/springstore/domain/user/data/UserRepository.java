package br.com.cesarsicas.springstore.domain.user.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserDetails findByLoginAndIsActiveTrue(String username);

    List<UserEntity> findAllByIsActiveTrue();
}
