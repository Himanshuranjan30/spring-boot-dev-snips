package com.springboot.demo.service;

import com.springboot.demo.entity.User;
import com.springboot.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Cacheable("USER_DATA_CONFIG")
    public Optional<User> getUserById(Long id){
        return fetchData(id);
    }

    private Optional<User> fetchData(Long id){
        System.out.println("fetching from db");
        return userRepository.findById(id);
    }



    public User createUser(User user){
        return userRepository.save(user);
    }
}
