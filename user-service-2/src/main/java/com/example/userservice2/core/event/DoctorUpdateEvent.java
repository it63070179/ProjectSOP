package com.example.userservice2.core.event;

import lombok.Data;

@Data
public class DoctorUpdateEvent {
    private String id;
    private String name;
    private String branch;
    private String description;
}
