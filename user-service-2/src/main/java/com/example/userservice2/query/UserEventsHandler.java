package com.example.userservice2.query;

import com.example.userservice2.core.UserEntity;
import com.example.userservice2.core.data.UserRepository;
import com.example.userservice2.core.event.AddAdminEvent;
import com.example.userservice2.core.event.AddDoctorEvent;
import com.example.userservice2.core.event.DoctorUpdateEvent;
import com.example.userservice2.core.event.UserUpdateEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserEventsHandler {
    private final UserRepository userRepository;

    public UserEventsHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventHandler
    public void on(UserUpdateEvent event){
        System.out.println("EventHandler : " + event);
        System.out.println("UserRepo : " + userRepository.findById(event.getId()).get());
        UserEntity userEntity = userRepository.findById(event.getId()).get();
        userEntity.setName(event.getName());
        userEntity.setEmail(event.getEmail());
        userEntity.setGender(event.getGender());
        userRepository.save(userEntity);
    }

    @EventHandler
    public void on(AddDoctorEvent event){
        System.out.println("EventHandler : " + event);
        System.out.println("UserRepo : " + userRepository.findByEmail(event.getEmail()));
        UserEntity userEntity = userRepository.findByEmail(event.getEmail());
        userEntity.setRole(event.getRole());
        userRepository.save(userEntity);
    }

    @EventHandler
    public void on(AddAdminEvent event){
        System.out.println("EventHandler : " + event);
        System.out.println("UserRepo : " + userRepository.findByEmail(event.getEmail()));
        UserEntity userEntity = userRepository.findByEmail(event.getEmail());
        userEntity.setRole(event.getRole());
        userRepository.save(userEntity);
    }

    @EventHandler
    public void on(DoctorUpdateEvent event){
        System.out.println("EventHandler : " + event);
        System.out.println("UserRepo : " + userRepository.findById(event.getId()).get());
        UserEntity userEntity = userRepository.findById(event.getId()).get();
        userEntity.setName(event.getName());
        userEntity.setBranch(event.getBranch());
        userEntity.setDescription(event.getDescription());
        userRepository.save(userEntity);
    }
}
