package edu.project2.tripplanner.service;

import edu.project2.tripplanner.model.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {

    void saveUser(User user);

    User updateUser(User user);

    List<User> findAllUsers();

    User findByUsername(String username);

    boolean existsByUsername(String username);

    @Transactional
    void deleteById(Long id);

    User getById(Long id);

    boolean existsById(Long id);
}
