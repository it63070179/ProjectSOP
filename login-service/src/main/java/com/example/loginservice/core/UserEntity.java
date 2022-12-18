package com.example.loginservice.core;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@Document("Users")
@Data
public class UserEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 3106264412778629176L;

    @Id
    private String id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String gender;
    private String role;
}
