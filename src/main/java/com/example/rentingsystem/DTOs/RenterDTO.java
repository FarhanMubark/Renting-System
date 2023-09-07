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
public class RenterDTO {

    //private Integer user_id;

    private String username;

    private String password;

    @NotEmpty(message = "Should not be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty(message = "Should not be empty")
    private String phoneNumber;
    @NotEmpty(message = "Should not be empty")
    private String email;

    private String status;

}
