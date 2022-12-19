package com.example.userservice2.command;

import com.example.userservice2.core.event.UserUpdateEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@Aggregate
public class UserAggregate {
    @AggregateIdentifier
    private String randomNumber;
    private String id;
    private String name;
//    private String username;
//    private String password;
    private String email;
    private String gender;
//    private String role;

    public UserAggregate(){
    }

    @CommandHandler
    public UserAggregate(UpdateUserCommand updateUserCommand){
        UserUpdateEvent userUpdateEvent = new UserUpdateEvent();
        BeanUtils.copyProperties(updateUserCommand, userUpdateEvent);
        System.out.println("UpdateCommand : " + updateUserCommand);
        System.out.println("userUpdateEvent : " + userUpdateEvent);
        AggregateLifecycle.apply(userUpdateEvent);
    }

    @EventSourcingHandler
    public void on(UserUpdateEvent event){
        this.id = event.getId();
        this.name = event.getName();
//        this.username = event.getUsername();
//        this.password = event.getPassword();
        this.email = event.getEmail();
        this.gender = event.getGender();
//        this.role = event.getRole();
        this.randomNumber = UUID.randomUUID().toString();
        System.out.println("On Method : " + event);
    }
}