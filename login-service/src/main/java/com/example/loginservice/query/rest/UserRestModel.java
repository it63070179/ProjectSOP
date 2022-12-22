package com.example.loginservice.query.rest;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRestModel implements Serializable {

    private String id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String gender;
    private String branch;
    private String description;
    private String role;
    private String picture;
}
