package com.springboot.demo.controllers;

import com.springboot.demo.entity.User;
import com.springboot.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/user-api")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public Optional<User> fetchById(@RequestParam Long id){
        return userService.getUserById(id);
    }


    @PostMapping("/user")
    public User createUser(@RequestBody User user){
        System.out.println(user.getLevel());
        return userService.createUser(user);
    }

}
