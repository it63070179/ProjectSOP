package com.example.loginservice.data;

import com.example.loginservice.core.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, String> {
    UserEntity findByUsernameAndPassword(String username, String password);
}
