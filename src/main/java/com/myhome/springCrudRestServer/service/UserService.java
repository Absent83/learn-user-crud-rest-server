package com.myhome.springCrudRestServer.service;

import com.myhome.springCrudRestServer.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> get(long id);
    Optional<User> getByUsername(String userName);
    Optional<User> getByEmail(String email);

    List<User> getAll();
    List<User> getByFirstName(String firstName);

    void add(User user);
    void update(User user);
    void delete(long id);

}

