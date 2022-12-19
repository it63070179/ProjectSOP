package com.example.appointmentservice.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.io.Serializable;

@Builder
@Data
public class CreateAppointmentCommand implements Serializable {
    public CreateAppointmentCommand(String id, String fullname, String email, String date, String time, String description, String doctorname) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.date = date;
        this.time = time;
        this.description = description;
        this.doctorname = doctorname;

    }

    @TargetAggregateIdentifier
    private final String id;
    private final String fullname;
    private final String email;
    private final String date;
    private final String time;
    private final String description;
    private final String doctorname;
}
