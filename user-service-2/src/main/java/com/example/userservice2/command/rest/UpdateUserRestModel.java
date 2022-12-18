package com.example.userservice2.command.rest;

import lombok.Data;

@Data
public class UpdateUserRestModel {
    private String id;
    private String name;
    private String gender;
}
