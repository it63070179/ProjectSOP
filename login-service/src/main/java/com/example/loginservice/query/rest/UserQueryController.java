package com.example.loginservice.query.rest;

import com.example.loginservice.query.RabbitLoginDataModel;
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
        Object users = rabbitTemplate.convertSendAndReceive("LoginExchange", "getAllUser", "");
        return (List<UserRestModel>) users;
    }

    @GetMapping(value = "/{username}/{password}")
    public UserRestModel getUserByUsernameAndPassword(@PathVariable String username, @PathVariable String password){
        if(username == "" || password == ""){
            return null;
        }
        RabbitLoginDataModel loginData = new RabbitLoginDataModel(username, password);
        Object user = rabbitTemplate.convertSendAndReceive("LoginExchange", "getByUsernameAndPassword", loginData);
        return (UserRestModel) user;
    }
}
