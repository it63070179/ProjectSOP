package com.example.appointmentservice.query;

import com.example.appointmentservice.core.AppointmentEntity;
import com.example.appointmentservice.core.event.data.AppointmentRepository;
import com.example.appointmentservice.query.rest.AppointmentRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppointmentEventHandler {
    private final AppointmentRepository appointmentRepository;

    public AppointmentEventHandler(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @QueryHandler
    List<AppointmentRestModel> findAppointment(FindAppointmentQuery query){
        List<AppointmentRestModel> appointmentRest = new ArrayList<>();
        List<AppointmentEntity> appointmentEntities = appointmentRepository.findAll();
        for(AppointmentEntity appointmentEntity : appointmentEntities){
            AppointmentRestModel appointmentRestModel = new AppointmentRestModel();
            BeanUtils.copyProperties(appointmentEntity, appointmentRestModel);
            appointmentRest.add(appointmentRestModel);
        }
        return appointmentRest;
    }

    @QueryHandler
    List<AppointmentRestModel> findByDoctor(FindAppointmentByDoctorName query){
        List<AppointmentRestModel> appointmentRest = new ArrayList<>();
        List<AppointmentEntity> appointmentEntities = appointmentRepository.findBydoctorname(query.getDoctorname());
        for(AppointmentEntity appointmentEntity : appointmentEntities){
            AppointmentRestModel appointmentRestModel = new AppointmentRestModel();
            BeanUtils.copyProperties(appointmentEntity, appointmentRestModel);
            appointmentRest.add(appointmentRestModel);
        }
        return appointmentRest;
    }

}
