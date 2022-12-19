package com.example.appointmentservice.core.event.data;

import com.example.appointmentservice.core.AppointmentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppointmentRepository extends MongoRepository<AppointmentEntity, String> {
}
