package com.example.userservice2.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class UpdateUserCommand {
    @TargetAggregateIdentifier
    private String id;
    private String name;
    private String gender;
}
