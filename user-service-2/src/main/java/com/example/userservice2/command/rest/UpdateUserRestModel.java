package com.example.userservice2.command.rest;

import lombok.Data;

@Data
public class UpdateUserRestModel {
    private String id;
    private String name;
//    private String username;
//    private String password;
    private String email;
    private String gender;
//    private String role;
}
