package com.example.userservice2.core.event;

import lombok.Data;

@Data
public class UserUpdateEvent {
    private String id;
    private String name;
    private String gender;
}
