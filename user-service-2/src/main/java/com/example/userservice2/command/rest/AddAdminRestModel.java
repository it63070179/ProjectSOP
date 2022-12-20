package com.example.userservice2.command.rest;

import lombok.Data;

@Data
public class AddAdminRestModel {
    private String email;
    private String role;
}
