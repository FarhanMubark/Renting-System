package com.example.rentingsystem.DTOs;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LessorDTO {
    @NotNull
    private Integer user_id;

    @NotEmpty(message = "Should not be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty(message = "Should not be empty")
    @Column(columnDefinition = "varchar(10) not null")
    private String phoneNumber;
    @NotEmpty(message = "Should not be empty")
    @Column(columnDefinition = "varchar(40) not null unique")
    private String email;
}
