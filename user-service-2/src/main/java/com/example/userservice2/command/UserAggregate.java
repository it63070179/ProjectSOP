package com.example.userservice2.command;

import com.example.userservice2.core.event.UserUpdateEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class UserAggregate {
    @AggregateIdentifier
    private String id;
    private String name;
    private String gender;

    public UserAggregate(){
    }

    @CommandHandler
    public UserAggregate(UpdateUserCommand updateUserCommand){
        UserUpdateEvent userUpdateEvent = new UserUpdateEvent();
        BeanUtils.copyProperties(updateUserCommand, userUpdateEvent);
        AggregateLifecycle.apply(userUpdateEvent);
    }

    @EventSourcingHandler
    public void on(UserUpdateEvent userUpdateEvent){
        this.id = userUpdateEvent.getId();
        this.name = userUpdateEvent.getName();
        this.gender = userUpdateEvent.getGender();
    }
}
