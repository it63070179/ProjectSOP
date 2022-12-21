package com.example.loginservice.query;

import com.example.loginservice.query.rest.UserRestModel;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class RabbitMQService {
    @Autowired
    QueryGateway queryGateway;

    @RabbitListener(queues = "GetAllUserQueue")
    public List<UserRestModel> getUser(){
        System.out.println("GetAllUserQueue");
        FindUsersQuery findUsersQuery = new FindUsersQuery();
        List<UserRestModel> users = queryGateway.query(findUsersQuery, ResponseTypes.multipleInstancesOf(UserRestModel.class)).join();
        return users;
    }

    @RabbitListener(queues = "GetUserByUsernameAndPassword")
    public UserRestModel getUserByUsernameAndPassword(RabbitLoginDataModel loginData){
        System.out.println("GetUserByUsernameAndPassword");
        FindByUsernameAndPassword findByUsernameAndPassword = new FindByUsernameAndPassword(loginData.getUsername(), loginData.getPassword());

        UserRestModel user;
        try{
            user = queryGateway.query(findByUsernameAndPassword, ResponseTypes.instanceOf(UserRestModel.class)).join();
        }catch (Exception e){
            user = null;
        }
        return user;
    }
}
