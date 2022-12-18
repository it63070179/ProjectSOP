package com.example.loginservice.query;

import lombok.Data;

@Data
public class FindByUsernameAndPassword {

    private String username;
    private String password;

    public FindByUsernameAndPassword(String username, String password){
        this.username = username;
        this.password = password;
    }
}
