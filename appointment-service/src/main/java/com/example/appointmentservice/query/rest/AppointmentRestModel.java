package com.example.appointmentservice.query.rest;

import lombok.Data;

import java.io.Serializable;

@Data
public class AppointmentRestModel implements Serializable {
    private String id;
    private String fullname;
    private String email;
    private String date;
    private String description;
    private String time;
    private String doctorname;
}
