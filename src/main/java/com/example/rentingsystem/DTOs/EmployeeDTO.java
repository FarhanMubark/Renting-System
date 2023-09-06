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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotEmpty(message = "Should be not empty")
    @Column(columnDefinition = "varchar(25) not null")
    private String employeeName;

    @NotEmpty(message = "Should be not empty")
    @Column(columnDefinition = "varchar(25) not null")
    private String age;

    @NotEmpty(message = "Should be not empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String brithDay;

    @NotEmpty(message = "Should be not empty")
    @Column(columnDefinition = "varchar(10) not null")
    private String phoneNumber;
}
