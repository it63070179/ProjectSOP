package com.example.userservice2.command.rest;

import com.example.userservice2.command.UpdateUserCommand;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserCommandController {
    private final CommandGateway commandGateway;

    @Autowired
    public UserCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping (value = "/updateUser")
    public String updateUser(@RequestBody UpdateUserRestModel model){
        UpdateUserCommand command = UpdateUserCommand.builder()
                .id(model.getId())
                .name(model.getName())
                .username(model.getUsername())
                .password(model.getPassword())
                .email(model.getEmail())
                .gender(model.getGender())
                .role(model.getRole())
                .build();
        String result;
        try{
            result = commandGateway.sendAndWait(command);
        }
        catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }
}
