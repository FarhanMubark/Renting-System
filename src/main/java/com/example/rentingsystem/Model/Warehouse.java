package com.example.rentingsystem.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Should not be empty")
    @Column(columnDefinition = "varchar(25) not null")
    private String size;
    @NotEmpty(message = "Should not be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String location;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "warehouse")
    private Set<Lessor> lessors;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "warehouse")
    private Set<Employee> employees;

}
