package com.springboot.demo.models;

import lombok.Data;

@Data
public class SignupEvent {

    private String signupTime;
    private String userName;
    private String email;

}