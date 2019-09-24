package edu.project2.tripplanner.controller;

import edu.project2.tripplanner.model.User;
import edu.project2.tripplanner.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @ResponseBody
    @GetMapping("/all")
    public List<User> findAllUsers() {

        return userService.findAllUsers();
    }


    @ResponseBody
    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        userService.saveUser(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteById(id);
    }


    @ResponseBody
    @PatchMapping
    public User updateUser(@RequestBody User user) {

        return userService.updateUser(user);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") Long id) {

        return userService.getById(id);
    }

    @ResponseBody
    @GetMapping("/{username}")
    public User findByUsername(@PathVariable("username") String username) {

        return userService.findByUsername(username);
    }
}


