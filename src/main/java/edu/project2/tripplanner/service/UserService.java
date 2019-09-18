package edu.project2.tripplanner.service;

import edu.project2.tripplanner.model.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface UserService {

    void saveUser(User user);

    User updateUser(User user);

    List<User> findAllUsers();

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    @Transactional
    void deleteById(Long id);

    User findById(Long id);

    boolean existsById(Long id);
}
