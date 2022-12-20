package com.example.appointmentservice.core.event.data;

import com.example.appointmentservice.core.AppointmentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AppointmentRepository extends MongoRepository<AppointmentEntity, String> {

    List<AppointmentEntity> findBydoctorname(String doctorname);
}
