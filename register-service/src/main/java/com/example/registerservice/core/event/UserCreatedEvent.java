package com.example.registerservice.core.event;

import lombok.Data;

@Data
public class UserCreatedEvent {
    private String id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String gender;
    private String role;
}
