package com.myhome.springCrudRestServer.dao;

import com.myhome.springCrudRestServer.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDAO { //CRUD Create Read Update Delete
    Optional<User> get(long id);
    Optional<User> getByUsername(String username);

    List<User> getByFirstName(String firstName);
    List<User> getAll();

    void add(User user);
    void update(User user);
    void delete(long id);
}