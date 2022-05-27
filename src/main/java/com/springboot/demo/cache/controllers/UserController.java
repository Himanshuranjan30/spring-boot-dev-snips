package com.springboot.demo.cache.controllers;

import com.springboot.demo.cache.entity.User;
import com.springboot.demo.cache.service.UserService;
import lombok.AllArgsConstructor;
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
