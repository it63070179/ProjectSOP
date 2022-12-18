package com.example.userservice2.query;

import lombok.Data;

@Data
public class FindUserByIdQuery {
    private String id;

    public FindUserByIdQuery(String id) {
        this.id = id;
    }
}
