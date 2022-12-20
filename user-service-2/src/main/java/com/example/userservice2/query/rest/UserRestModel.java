package com.example.userservice2.query.rest;

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
    private String role;
}
