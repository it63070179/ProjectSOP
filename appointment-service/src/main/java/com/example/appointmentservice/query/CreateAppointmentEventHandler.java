package com.example.appointmentservice.query;

import com.example.appointmentservice.core.AppointmentEntity;
import com.example.appointmentservice.core.event.AppointmentCreatedEvent;
import com.example.appointmentservice.core.event.data.AppointmentRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CreateAppointmentEventHandler {
    private final AppointmentRepository appointmentRepository;

    public CreateAppointmentEventHandler(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @EventHandler
    public void on(AppointmentCreatedEvent event){
        AppointmentEntity appointmentEntity = new AppointmentEntity();
        BeanUtils.copyProperties(event, appointmentEntity);
        appointmentRepository.save(appointmentEntity);
    }
}
