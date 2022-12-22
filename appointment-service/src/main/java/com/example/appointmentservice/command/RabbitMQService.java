package com.example.appointmentservice.command;

import com.example.appointmentservice.command.rest.CreateAppointmentRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RabbitMQService {
    @Autowired
    CommandGateway commandGateway;

    @RabbitListener(queues = "CreateAppointmentQueue")
    public String createAppointment(CreateAppointmentCommand command){

        System.out.println("CreateAppointmentQueue");
        String result;
        try{
            result = commandGateway.sendAndWait(command);
        }catch(Exception e){
            e.printStackTrace();
            result = e.getLocalizedMessage();
        }
        System.out.println("result : " + result);
        return result;
    }
}


