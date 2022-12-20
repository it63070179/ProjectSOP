package com.example.userservice2.core.event;

import lombok.Data;

@Data
public class AddAdminEvent {
    private String email;
    private String role;
}
