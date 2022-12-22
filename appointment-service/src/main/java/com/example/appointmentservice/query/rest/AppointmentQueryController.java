package com.example.appointmentservice.query.rest;

import com.example.appointmentservice.query.FindAppointmentQuery;
import com.example.appointmentservice.query.FindAppointmentByDoctorName;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(value = "http://localhost:8080", allowCredentials = "true")
@RestController
@RequestMapping("/appointmentUser")
public class AppointmentQueryController {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    QueryGateway queryGateway;

    @GetMapping
    public List<AppointmentRestModel> getAppointment(){
        Object users = rabbitTemplate.convertSendAndReceive("AppointmentExchange", "getAllAppointment", "");
        return (List<AppointmentRestModel>) users;
    }

    @GetMapping(value = "/{doctorname}")
    public List<AppointmentRestModel> getAppointmentByDoctor(@PathVariable String doctorname){
        FindAppointmentByDoctorName findAppointmentByDoctorName = new FindAppointmentByDoctorName(doctorname);
        List<AppointmentRestModel> appointmentUser = queryGateway.query(findAppointmentByDoctorName, ResponseTypes.multipleInstancesOf(AppointmentRestModel.class)).join();
        return appointmentUser;
    }
}
