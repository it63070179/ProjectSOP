package com.example.appointmentservice.command.rest;

import com.example.appointmentservice.command.CreateAppointmentCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(value = "http://localhost:8080", allowCredentials = "true")
@RestController
@RequestMapping("/appointmentUser/")
public class CreateAppointmentController {

    @Autowired
    RabbitTemplate rabbitTemplate;
    private final CommandGateway commandGateway;
    @Autowired
    public CreateAppointmentController(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    @PostMapping("/appointment")
    public String createAppointment(@RequestBody CreateAppointmentRestModel model){
        CreateAppointmentCommand command = CreateAppointmentCommand.builder()
                .id(UUID.randomUUID().toString())
                .fullname(model.getFullname())
                .email(model.getEmail())
                .date(model.getDate())
                .time(model.getTime())
                .description(model.getDescription())
                .doctorname(model.getDoctorname())
                .build();
        Object users = rabbitTemplate.convertSendAndReceive("AppointmentExchange", "createAppointment", command);
        return (String) users;
    }
}
