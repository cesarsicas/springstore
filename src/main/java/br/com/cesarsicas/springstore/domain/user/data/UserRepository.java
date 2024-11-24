package br.com.cesarsicas.springstore.domain.user.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserDetails findByLoginAndIsActiveTrue(String username);

    List<UserEntity> findAllByIsActiveTrue();
}
