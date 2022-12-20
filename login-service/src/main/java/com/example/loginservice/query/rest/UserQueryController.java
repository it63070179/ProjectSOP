package com.example.loginservice.query.rest;

import com.example.loginservice.query.FindByUsernameAndPassword;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(value = "http://localhost:8080")
@RestController
@RequestMapping("/users")
public class UserQueryController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    QueryGateway queryGateway;

    @GetMapping
    public List<UserRestModel> getUser(){
        Object users = rabbitTemplate.convertSendAndReceive("UserExchange", "getAllUser", "");
        return (List<UserRestModel>) users;
    }

    @GetMapping(value = "/{username}/{password}")
    public UserRestModel getUserByUsernameAndPassword(@PathVariable String username, @PathVariable String password){
        FindByUsernameAndPassword findByUsernameAndPassword = new FindByUsernameAndPassword(username, password);
        UserRestModel user = queryGateway
                .query(findByUsernameAndPassword, ResponseTypes.instanceOf(UserRestModel.class)).join();
        return user;
    }
}
