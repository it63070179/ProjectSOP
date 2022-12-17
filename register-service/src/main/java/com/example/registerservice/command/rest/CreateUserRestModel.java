package com.example.registerservice.command.rest;

import lombok.Data;

@Data
public class CreateUserRestModel {
    private String name;
    private String username;
    private String password;
    private String email;
    private String gender;
    private String role;
}
