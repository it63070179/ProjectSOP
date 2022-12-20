package com.example.userservice2.command;

import com.example.userservice2.command.rest.AddDoctorRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class RabbitService {

    @Autowired
    CommandGateway commandGateway;

    @RabbitListener(queues = "UpdateUserQueue")
    public String updateUser(UpdateUserCommand command){

        System.out.println("UpdateUserQueue");
        String result;
        try{
            result = commandGateway.sendAndWait(command);
        }
        catch (Exception e){
            result = e.getLocalizedMessage();
        }
        return result;
    }

    @RabbitListener(queues = "AddDoctorQueue")
    public String addDoctor(AddDoctorCommand command){

        System.out.println("AddDoctorQueue");
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
