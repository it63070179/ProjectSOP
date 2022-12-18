package com.example.loginservice.query.rest;

import lombok.Data;

@Data
public class UserRestModel {

    private String id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String gender;
    private String role;
}
