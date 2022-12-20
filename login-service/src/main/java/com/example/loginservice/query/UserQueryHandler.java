package com.example.loginservice.query;

import com.example.loginservice.core.UserEntity;
import com.example.loginservice.data.UserRepository;
import com.example.loginservice.query.rest.UserRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserQueryHandler {

    private final UserRepository userRepository;

    public UserQueryHandler(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @QueryHandler
    List<UserRestModel> findUsers(FindUsersQuery query){
        List<UserRestModel> usersRest = new ArrayList<>();
        List<UserEntity> storedUsers = userRepository.findAll();
        for(UserEntity userEntity : storedUsers){
            UserRestModel userRestModel = new UserRestModel();
            BeanUtils.copyProperties(userEntity, userRestModel);
            usersRest.add(userRestModel);
        }
        return usersRest;
    }

    @QueryHandler
    UserRestModel findByUsernameAndPassword(FindByUsernameAndPassword query){
        UserRestModel userRest = new UserRestModel();
        UserEntity user = userRepository.findByUsernameAndPassword(query.getUsername(), query.getPassword());
        BeanUtils.copyProperties(user, userRest);
        return userRest;
    }
}
