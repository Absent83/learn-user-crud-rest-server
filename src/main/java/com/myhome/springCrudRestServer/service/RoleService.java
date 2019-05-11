package com.myhome.springCrudRestServer.service;

import com.myhome.springCrudRestServer.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<Role> get(long id);

    Optional<Role> getByAuthority(String authority);

    List<Role> getAll();

    void add(Role role);
    void update(Role role);
    void delete(long id);
}

