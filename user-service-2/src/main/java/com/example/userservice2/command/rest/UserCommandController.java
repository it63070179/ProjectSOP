package com.example.userservice2.command.rest;

import com.example.userservice2.command.UpdateUserCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserCommandController {
    private final CommandGateway commandGateway;

    @Autowired
    public UserCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PutMapping("/update")
    public String updateUser(@RequestBody UpdateUserRestModel model){
        UpdateUserCommand command = UpdateUserCommand.builder()
                .id(model.getId())
                .name(model.getName())
//                .username(model.getUsername())
//                .password(model.getPassword())
                .email(model.getEmail())
                .gender(model.getGender())
//                .role(model.getRole())
                .build();
        System.out.println("Command : " + command);
        System.out.println("ID :  "+ model.getId());
        System.out.println("Name : " + model.getName());
        System.out.println("Gender : " + model.getGender());
        String result;
        try{
            result = commandGateway.sendAndWait(command);
        }
        catch (Exception e){
            result = e.getLocalizedMessage();
        }
        System.out.println("result : " + result);
        return result;
    }
}
