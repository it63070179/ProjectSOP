package com.example.registerservice.command;



import com.example.registerservice.core.event.UserCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class CreateUserAggregate {
    @AggregateIdentifier
    private  String id;
    private  String name;
    private  String username;
    private  String password;
    private  String email;
    private  String gender;
    private  String role;

    public CreateUserAggregate() {
    }

    @CommandHandler
    public CreateUserAggregate(CreateUserCommand createUserCommand) {
        UserCreatedEvent userCreatedEvent = new UserCreatedEvent();
        BeanUtils.copyProperties(createUserCommand, userCreatedEvent);
        AggregateLifecycle.apply(userCreatedEvent);
    }

    @EventSourcingHandler
    public void on(UserCreatedEvent userCreatedEvent){
        this.id = userCreatedEvent.getId();
        this.name = userCreatedEvent.getName();
        this.username = userCreatedEvent.getUsername();
        this.password = userCreatedEvent.getPassword();
        this.email = userCreatedEvent.getEmail();
        this.gender = userCreatedEvent.getGender();
        this.role = userCreatedEvent.getRole();
    }
}
