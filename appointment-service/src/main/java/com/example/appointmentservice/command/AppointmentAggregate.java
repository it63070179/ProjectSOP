package com.example.appointmentservice.command;

import com.example.appointmentservice.core.event.AppointmentCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class AppointmentAggregate {
    @AggregateIdentifier
    private String id;
    private String fullname;
    private String email;
    private String date;
    private String time;
    private String description;
    private String doctorname;

    public AppointmentAggregate() {
    }

    @CommandHandler
    public AppointmentAggregate(CreateAppointmentCommand createAppointmentCommand){
        AppointmentCreatedEvent appointmentCreatedEvent = new AppointmentCreatedEvent();
        BeanUtils.copyProperties(createAppointmentCommand, appointmentCreatedEvent);
        AggregateLifecycle.apply(appointmentCreatedEvent);
    }

    @EventSourcingHandler
    public void on(AppointmentCreatedEvent appointmentCreatedEvent){
        this.id = appointmentCreatedEvent.getId();
        this.fullname = appointmentCreatedEvent.getFullname();
        this.email = appointmentCreatedEvent.getEmail();
        this.date = appointmentCreatedEvent.getDate();
        this.time = appointmentCreatedEvent.getTime();
        this.description = appointmentCreatedEvent.getDescription();
        this.doctorname = appointmentCreatedEvent.getDoctorname();



    }

}
