package com.example.rentingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lessor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Should not be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty(message = "Should not be empty")
    @Column(columnDefinition = "varchar(40) not null unique")
    private String email;

    @NotEmpty(message = "Should not be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String status;

    @NotEmpty(message = "Should not be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String phoneNumber;

    private Double balance = 0.0;

}
