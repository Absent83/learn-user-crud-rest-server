package com.myhome.springCrudRestServer.controllers;

import com.myhome.springCrudRestServer.model.Role;
import com.myhome.springCrudRestServer.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;


@RestController
public class RoleRestController {

    private final RoleService roleService;

    @Autowired
    public RoleRestController(RoleService roleService) {
        this.roleService = roleService;
    }


    @GetMapping(path = "/api/roles")
    public List<Role> getAllRoles(@RequestParam(name = "authority", required = false) String authority) {

        List<Role> roles;

        if (authority == null || authority.isEmpty()) {
            roles = roleService.getAll();
            System.out.println("getAllRoles. Count=" + roles.size());

        } else {
            roles = Collections.singletonList(roleService.getByAuthority(authority).orElseThrow(IllegalArgumentException::new));
            System.out.println("getAllRoles with authority=" + authority + "; response = " + roles.get(0).getAuthority());
        }

        return roles;
    }


    @GetMapping(path = "/api/roles/{role-id}")
    public Role getRole(@PathVariable("role-id") Integer roleId){
        Role role = roleService.get(roleId).orElseThrow(IllegalArgumentException::new);
        System.out.println("getRole by ID=" + roleId + "; response = " + role.getAuthority());

        return role;
    }
}
