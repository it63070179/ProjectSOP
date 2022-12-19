package com.example.appointmentservice.core;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("appointment")
@Data
public class AppointmentEntity implements Serializable {

    private static final long serialVersionUID = 8773499781824830791L;

    @Id
    private String id;
    private String fullname;
    private String email;
    private String date;
    private String time;
    private String description;
    private String doctorname;
}
