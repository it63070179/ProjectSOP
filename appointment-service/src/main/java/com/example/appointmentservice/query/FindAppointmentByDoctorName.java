package com.example.appointmentservice.query;

import lombok.Data;

@Data
public class FindAppointmentByDoctorName {
    private String doctorname;

    public FindAppointmentByDoctorName(String doctorname) {
        this.doctorname = doctorname;
    }
}
