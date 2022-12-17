package com.example.registerservice.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.io.Serializable;

@Builder
@Data
public class CreateUserCommand implements Serializable {
    public CreateUserCommand(String id,String name, String username, String password, String email, String gender,String role){
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.role = role;

    }

    @TargetAggregateIdentifier
    private final String id;
    private final String name;
    private final String username;
    private final String password;
    private final String email;
    private final String gender;
    private final String role;


}

