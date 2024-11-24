package br.com.cesarsicas.springstore.data.merchant;

import br.com.cesarsicas.springstore.data.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends JpaRepository<MerchantEntity, Long> {

    MerchantEntity findByUser(UserEntity user);
}
