package com.example.appointmentservice.query;

import com.example.appointmentservice.query.rest.AppointmentRestModel;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RabbitMQService {

    @Autowired
    QueryGateway queryGateway;

    @RabbitListener(queues = "GetAllAppointmentQueue")
    public List<AppointmentRestModel> getAppointment(){
        FindAppointmentQuery findAppointmentQuery = new FindAppointmentQuery();
        List<AppointmentRestModel> appointmentUser = queryGateway.query(findAppointmentQuery, ResponseTypes.multipleInstancesOf(AppointmentRestModel.class)).join();
        return appointmentUser;
    }
}
