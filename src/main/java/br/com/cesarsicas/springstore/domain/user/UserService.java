package br.com.cesarsicas.springstore.domain.user;

import br.com.cesarsicas.springstore.domain.user.data.UserEntity;
import br.com.cesarsicas.springstore.domain.user.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;


    public List<User> getAllUsers() {
        return userRepository.findAll().stream().map(User::new).toList();
    }

    public void saveUser(User user) {
        userRepository.save(new UserEntity(user));
    }

    public void softDeleteUser(Long id) {
        var user = userRepository.getReferenceById(id);
        user.setActive(false);
    }
}
