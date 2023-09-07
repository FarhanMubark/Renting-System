package com.example.rentingsystem.DTOs;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LessorDTO {

    private Integer user_id;

    private String username;

    private String password;

////
    private String status;

    @NotEmpty(message = "Should not be empty")
    private String name;

    @NotEmpty(message = "Should not be empty")
    private String phoneNumber;

    @NotEmpty(message = "Should not be empty")
    private String email;

}
