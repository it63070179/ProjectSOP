package com.example.userservice2.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.io.Serializable;

@Builder
@Data
public class UpdateDoctorCommand implements Serializable {
    @TargetAggregateIdentifier
    private String id;
    private String name;
    private String branch;
    private String description;
}
