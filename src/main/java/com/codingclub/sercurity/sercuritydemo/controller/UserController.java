package com.codingclub.sercurity.sercuritydemo.controller;

import com.codingclub.sercurity.sercuritydemo.dto.RoleToUser;
import com.codingclub.sercurity.sercuritydemo.entity.Role;
import com.codingclub.sercurity.sercuritydemo.entity.User;
import com.codingclub.sercurity.sercuritydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @PostMapping("/users/new")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        URI uri  = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }
    @PostMapping("/roles/new")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri  = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/roles").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }
    @PostMapping("/users/newrole")
    public ResponseEntity<?> addNewRole(@RequestBody RoleToUser form){
//        URI uri  = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/roles").toUriString());
        userService.addRoletoUser(form.getUsername(),form.getRolename());
        return ResponseEntity.ok().build();
    }
}
