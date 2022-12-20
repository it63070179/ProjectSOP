package com.example.userservice2.command.rest;

import lombok.Data;

@Data
public class UpdateDoctorRestModel {
    private String id;
    private String name;
    private String branch;
    private String description;
}
