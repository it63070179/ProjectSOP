package com.example.loginservice.query.rest;

import com.example.loginservice.query.FindByUsernameAndPassword;
import com.example.loginservice.query.FindUsersQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserQueryController {

    @Autowired
    QueryGateway queryGateway;

    @GetMapping
    public List<UserRestModel> getUser(){
        FindUsersQuery findUsersQuery = new FindUsersQuery();
        List<UserRestModel> users = queryGateway
                .query(findUsersQuery, ResponseTypes.multipleInstancesOf(UserRestModel.class)).join();
        return users;
    }

    @GetMapping(value = "/{username}/{password}")
    public UserRestModel getUserByUsernameAndPassword(@PathVariable String username, @PathVariable String password){
        FindByUsernameAndPassword findByUsernameAndPassword = new FindByUsernameAndPassword(username, password);
        UserRestModel user = queryGateway
                .query(findByUsernameAndPassword, ResponseTypes.instanceOf(UserRestModel.class)).join();
        return user;
    }
}
