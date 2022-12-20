package com.example.userservice2.query;

import com.example.userservice2.query.rest.UserRestModel;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class RabbitService {

    @Autowired
    QueryGateway queryGateway;

    @RabbitListener(queues = "GetUserByIdQueue")
    public UserRestModel getUserById(String id){
        System.out.println("GetUserByIdQueue");
        FindUserByIdQuery findUserByIdQuery = new FindUserByIdQuery(id);
        UserRestModel infoUserById = queryGateway.query(findUserByIdQuery, ResponseTypes.instanceOf(UserRestModel.class)).join();
        return infoUserById;
    }
}
