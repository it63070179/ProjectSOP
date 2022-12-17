package com.example.registerservice.query;


import com.example.registerservice.core.UserEntity;
import com.example.registerservice.core.event.UserCreatedEvent;
import com.example.registerservice.core.event.data.UserRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CreateUserEventHandler {
    private final UserRepository userRepository;

    public CreateUserEventHandler(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @EventHandler
    public void on(UserCreatedEvent event){
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(event, userEntity);
        userRepository.save(userEntity);
    }


}
