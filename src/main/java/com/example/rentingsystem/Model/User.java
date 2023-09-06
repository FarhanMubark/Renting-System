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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Should be not empty")
    @Column(columnDefinition = "varchar(25) not null")
    private String username;
    @NotEmpty(message = "Should be not empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String password;
    @NotEmpty(message = "Should be not empty")
    @Column(columnDefinition = "varchar(25) not null")
    private String role;

}
