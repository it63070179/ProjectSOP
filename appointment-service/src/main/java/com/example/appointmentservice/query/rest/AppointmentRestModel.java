package com.example.appointmentservice.query.rest;

import lombok.Data;

@Data
public class AppointmentRestModel {
    private String id;
    private String fullname;
    private String email;
    private String date;
    private String description;
    private String time;
    private String doctorname;
}
