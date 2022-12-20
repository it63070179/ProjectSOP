package com.example.userservice2.command;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddAdminCommand {
    private String email;
    private String role;
}
