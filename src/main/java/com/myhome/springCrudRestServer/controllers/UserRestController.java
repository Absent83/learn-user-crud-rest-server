package com.myhome.springCrudRestServer.controllers;

import com.myhome.springCrudRestServer.model.User;
import com.myhome.springCrudRestServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(path = "/api/users")
    public List<User> getAllUsers(){
        return userService.getAll();
    }


    @GetMapping(path = "/api/users", params = { "username" })
    public User getUserByUsername(@RequestParam(name = "username", required = true) String username){
        return userService.getByUsername(username).orElseThrow(IllegalArgumentException::new);
    }


    @GetMapping(path = "/api/users", params = { "email" })
    public ResponseEntity<User> getUserByEmail(@RequestParam(name = "email", required = true) String email){
        Optional<User> userCandidate = userService.getByEmail(email);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        if (userCandidate.isPresent()){
            User user = userCandidate.get();
            return new ResponseEntity<>(user, headers, HttpStatus.OK);
        }
        else {
            System.out.println("no user found by email: " + email);
            return new ResponseEntity<>(headers, HttpStatus.OK);
        }
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
