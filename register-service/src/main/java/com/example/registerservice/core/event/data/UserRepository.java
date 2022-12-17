package com.example.registerservice.core.event.data;


import com.example.registerservice.core.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, String> {
}
