package com.example.userservice2.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.UUID;

@Builder
@Data
public class UpdateUserCommand {

    @TargetAggregateIdentifier
    private String id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String gender;
    private String role;

}
