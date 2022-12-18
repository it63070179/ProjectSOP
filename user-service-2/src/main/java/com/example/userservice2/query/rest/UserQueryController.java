package com.example.userservice2.query.rest;

import com.example.userservice2.core.UserEntity;
import com.example.userservice2.query.FindByUsernameAndPassword;
import com.example.userservice2.query.FindUserByIdQuery;
import com.example.userservice2.query.FindUsersQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(value = "http://localhost:8080", allowCredentials = "true")
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

    @GetMapping(value = "/{id}")
    public UserRestModel getUserById(@PathVariable String id){
        FindUserByIdQuery findUserByIdQuery = new FindUserByIdQuery(id);
        UserRestModel infoUserById = queryGateway.query(findUserByIdQuery, ResponseTypes.instanceOf(UserRestModel.class)).join();
        return infoUserById;
    }

    @GetMapping(value = "/{username}/{password}")
    public UserRestModel getUserByUsernameAndPassword(@PathVariable String username, @PathVariable String password){
        FindByUsernameAndPassword findByUsernameAndPassword = new FindByUsernameAndPassword(username, password);
        UserRestModel user = queryGateway
                .query(findByUsernameAndPassword, ResponseTypes.instanceOf(UserRestModel.class)).join();
        return user;
    }
}
