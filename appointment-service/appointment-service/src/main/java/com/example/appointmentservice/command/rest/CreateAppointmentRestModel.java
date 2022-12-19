package com.example.appointmentservice.command.rest;

import lombok.Data;

@Data
public class CreateAppointmentRestModel {
    private String fullname;
    private String email;
    private String date;
    private String time;
    private String description;
    private String doctorname;

}
