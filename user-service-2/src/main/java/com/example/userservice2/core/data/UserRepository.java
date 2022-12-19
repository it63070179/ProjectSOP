package com.example.userservice2.core.data;

import com.example.userservice2.core.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, String> {
    UserEntity findUserById(String id);
}
