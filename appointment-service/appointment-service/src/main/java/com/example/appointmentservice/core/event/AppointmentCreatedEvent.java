package com.example.appointmentservice.core.event;

import lombok.Data;

@Data
public class AppointmentCreatedEvent {
    private String id;
    private String fullname;
    private String email;
    private String date;
    private String time;
    private String description;
    private String doctorname;
}
