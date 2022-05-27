package com.springboot.demo.cache.models;

import lombok.Data;

@Data
public class SignupEvent {

    private String signupTime;
    private String userName;
    private String email;

}