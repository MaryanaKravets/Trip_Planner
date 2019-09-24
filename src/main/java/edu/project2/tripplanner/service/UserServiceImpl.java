package edu.project2.tripplanner.service;

import edu.project2.tripplanner.exception.Message;
import edu.project2.tripplanner.exception.NotFoundException;
import edu.project2.tripplanner.model.User;
import edu.project2.tripplanner.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, Message {

    private final UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        User user1 = new User(user.getUsername(), user.getEmail(), user.getPassword());
        userRepository.save(user1);
    }

    @Override
    public User updateUser(User user) {

        return userRepository.saveAndFlush(user);
    }

    @Override
    public List<User> findAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(String.format(USERNAME_NOT_FOUND_EXCEPTION_MESSAGE, username)));
    }

    @Override
    public boolean existsByUsername(String username) {

        return userRepository.existsByUsername(username);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(USER_NOT_FOUND_EXCEPTION_MESSAGE, id)));
    }

    @Override
    public boolean existsById(Long id) {

        return userRepository.existsById(id);
    }
}