package com.example.rentingsystem.DTOs;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class EmployeeDTO {
    private Integer user_id;

    private String username;

    private String password;

    @NotEmpty(message = "Should not be empty")
    private String name;

    @NotEmpty(message = "Should not be empty")
    private String phoneNumber;

    @NotEmpty(message = "Should not be empty")
    private String email;

    @NotEmpty(message = "Should be not empty")
    private String employeeName;

    @NotEmpty(message = "Should be not empty")
    private String age;

    @NotEmpty(message = "Should be not empty")
    private String brithDay;

}
