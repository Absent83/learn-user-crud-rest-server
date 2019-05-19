package com.myhome.springCrudRestServer.controllers;

import com.myhome.springCrudRestServer.model.Role;
import com.myhome.springCrudRestServer.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class RoleRestController {

    private final RoleService roleService;

    @Autowired
    public RoleRestController(RoleService roleService) {
        this.roleService = roleService;
    }


    @GetMapping(path = "/api/roles")
    public List<Role> getAllRoles() {
        return roleService.getAll();
    }


    @GetMapping(path = "/api/roles", params = { "authority" })
    public Role getRoleByAuthority(@RequestParam(name = "authority", required = true) String authority) {
        return roleService.getByAuthority(authority).orElseThrow(IllegalArgumentException::new);
    }


    @GetMapping(path = "/api/roles/{role-id}")
    public Role getRoleById(@PathVariable("role-id") Integer roleId){
        return roleService.get(roleId).orElseThrow(IllegalArgumentException::new);
    }
}
