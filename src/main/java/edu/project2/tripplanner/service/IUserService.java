package edu.project2.tripplanner.service;

import edu.project2.tripplanner.model.User;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface IUserService {

    void saveUser(User user);

    User updateUser(User user);

    List<User> findAllUsers();

    Optional<User> getUserById(Long id);

    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    @Transactional
    void deleteByUsername(String username);

}
