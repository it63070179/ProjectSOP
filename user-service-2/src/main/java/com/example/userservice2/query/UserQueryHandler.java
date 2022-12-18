package com.example.userservice2.query;

import com.example.userservice2.core.UserEntity;
import com.example.userservice2.core.data.UserRepository;
import com.example.userservice2.query.rest.UserRestModel;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserQueryHandler {
    private final UserRepository userRepository;

    public UserQueryHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @QueryHandler
    UserRestModel findUserById(FindUserByIdQuery query){
        UserRestModel userRestModel = new UserRestModel();
        UserEntity storeUsers = userRepository.findUserById(query.getId());
        BeanUtils.copyProperties(storeUsers, userRestModel);
        return userRestModel;
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
}
