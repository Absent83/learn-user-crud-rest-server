package com.myhome.springCrudRestServer.controllers;

import com.myhome.springCrudRestServer.model.Role;
import com.myhome.springCrudRestServer.model.User;
import com.myhome.springCrudRestServer.model.dto.UserForm;
import com.myhome.springCrudRestServer.service.RoleService;
import com.myhome.springCrudRestServer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
public class UserRestController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;


    @GetMapping(path = "/api/users")
    public List<User> getAllUsers(@RequestParam(name = "username", required = false) String username){

        List<User> users = null;

        if (username == null || username.isEmpty()){
            users = userService.getAll();
        }
        else {
            users = Collections.singletonList(userService.getByUsername(username).orElseThrow(IllegalArgumentException::new));
        }

        return users;
    }


    @PostMapping(path="/api/users")
    public ResponseEntity<?> addUser(@RequestBody UserForm userForm) {

        User userNew = new User();

        updateUserData(userForm, userNew);

        userService.add(userNew);

        return ResponseEntity.ok("{\"result\" : \"ok\"}");
    }


    @GetMapping(path = "/api/users/{user-id}")
    public User getUser(@PathVariable("user-id") Integer userId){
        return userService.get(userId).orElseThrow(IllegalArgumentException::new);
    }


    @PutMapping(path = "/api/users/{user-id}")
    public User updateUser(@PathVariable("user-id") Integer userId, @RequestBody UserForm userForm) {

        System.out.println("username: " + userForm.getUsername() + "" +
                "firstname:" + userForm.getFirstName() + ""
        );

        User user = userService.get(userId).orElseThrow(IllegalArgumentException::new);

        updateUserData(userForm, user);

        userService.update(user);

        return user;
    }


    @DeleteMapping(path = "/api/users/{user-id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("user-id") Integer userId) {

        userService.delete(userId);

        return ResponseEntity.ok().build();
    }


    private void updateUserData(UserForm userForm, User user) {
        user.setUsername(userForm.getUsername());
        user.setFirstName(userForm.getFirstName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());

        Set<Role> roles = new HashSet<>();

        for (Integer roleId : userForm.getRoles()) {
            roles.add(roleService.get(roleId).orElseThrow(IllegalArgumentException::new));
        }
        user.setRoles(roles);
    }
}
