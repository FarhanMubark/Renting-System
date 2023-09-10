package com.example.rentingsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

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
    @Column(columnDefinition = "varchar(10) not null unique")
    private String phoneNumber;



    @OneToOne
    @JsonIgnore
    private User user;



    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    private Warehouse warehouse;



}
