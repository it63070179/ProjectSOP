package com.example.userservice2.core;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Data
@Document("user")
public class UserEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -7193259241747973314L;
    @Id
    private String id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String gender;
    private String role;
}
