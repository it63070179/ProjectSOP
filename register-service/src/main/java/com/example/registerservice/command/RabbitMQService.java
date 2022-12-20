package com.example.registerservice.command;

import com.example.registerservice.command.rest.CreateUserRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Service
public class RabbitMQService {

    @Autowired
    CommandGateway commandGateway;

    @RabbitListener(queues = "CreateUserQueue")
    public String createUser(CreateUserCommand command){
        String result;

        System.out.println("CreateUserQueue");
        try{
            result = commandGateway.sendAndWait(command);
        }catch (Exception e){
            e.printStackTrace();
            result = e.getLocalizedMessage();
        }
        return result;
    }
}
