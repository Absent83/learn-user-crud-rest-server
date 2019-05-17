package com.myhome.springCrudRestServer.controllers;

import com.myhome.springCrudRestServer.model.User;
import com.myhome.springCrudRestServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@RestController
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(path = "/api/users")
    public List<User> getAllUsers(@RequestParam(name = "username", required = false) String username){

        List<User> users;

        if (username == null || username.isEmpty()){
            users = userService.getAll();
        }
        else {
            users = Collections.singletonList(userService.getByUsername(username).orElseThrow(IllegalArgumentException::new));
        }

        return users;
    }


    @PostMapping(path="/api/users")
    public ResponseEntity<?> addUser(@RequestBody User user) {
        userService.add(user);
        return ResponseEntity.ok("{\"result\" : \"ok\"}");
    }


    @GetMapping(path = "/api/users/{user-id}")
    public User getUser(@PathVariable("user-id") Integer userId){
        return userService.get(userId).orElseThrow(IllegalArgumentException::new);
    }


    @PutMapping(path = "/api/users/{user-id}")
    public User updateUser(@PathVariable("user-id") Integer userId, @RequestBody User user) {
        userService.update(user);
        return user;
    }


    @DeleteMapping(path = "/api/users/{user-id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("user-id") Integer userId) {
        userService.delete(userId);
        return ResponseEntity.ok().build();
    }
}
