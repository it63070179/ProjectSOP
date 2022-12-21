package com.example.loginservice.query;

import lombok.Data;

import java.io.Serializable;

@Data
public class RabbitLoginDataModel implements Serializable {
    private String username;
    private String password;

    public RabbitLoginDataModel(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
