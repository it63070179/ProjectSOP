package com.example.userservice2.command;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Builder
@Data
public class AddAdminCommand implements Serializable {
    private String email;
    private String role;
}
