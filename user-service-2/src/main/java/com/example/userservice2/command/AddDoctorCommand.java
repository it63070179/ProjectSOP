package com.example.userservice2.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class AddDoctorCommand {
    @TargetAggregateIdentifier
//    private String id;
    private String email;
    private String role;
}
