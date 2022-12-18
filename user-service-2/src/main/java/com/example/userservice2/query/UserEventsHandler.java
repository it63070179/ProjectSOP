package com.example.userservice2.query;

import com.example.userservice2.core.UserEntity;
import com.example.userservice2.core.data.UserRepository;
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
        System.out.println(event);
        UserEntity userEntity = userRepository.findUserById(event.getId());
        System.out.println(userEntity);
        userEntity.setName(event.getName());
        userEntity.setGender(event.getGender());
//        BeanUtils.copyProperties(event, userEntity);
        userRepository.save(userEntity);
    }
}
