package com.example.registerservice.command.rest;



import com.example.registerservice.command.CreateUserCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@CrossOrigin(value = "http://localhost:8080", allowCredentials = "true")
@RestController
@RequestMapping("/user")
public class CreateUserCommandController {
    private final CommandGateway commandGateway;
    @Autowired
    public CreateUserCommandController(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }
    @PostMapping("/register")
    public String createUser(@RequestBody CreateUserRestModel model){
        CreateUserCommand command = CreateUserCommand.builder()
                .id(UUID.randomUUID().toString())
                .name(model.getName())
                .username(model.getUsername())
                .password(model.getPassword())
                .email(model.getEmail())
                .gender(model.getGender())
                .role(model.getRole()).build();

        String result;
        try{
            result = commandGateway.sendAndWait(command);
        }catch (Exception e){
            e.printStackTrace();
            result = e.getLocalizedMessage();
        }
        return result;
    }
}
