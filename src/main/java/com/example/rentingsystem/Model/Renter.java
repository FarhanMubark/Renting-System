package com.example.rentingsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Renter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Should not be empty")
    @Column(columnDefinition = "varchar(10) not null")
    private String phoneNumber;

    @NotEmpty(message = "Should not be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty(message = "Should not be empty")
    @Column(columnDefinition = "varchar(20) not null")
    private String status;

    @NotEmpty(message = "Should not be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String email;

    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;


    @ManyToOne
    @JoinColumn(name = "support_id", referencedColumnName = "id")
    @JsonIgnore
    private Support support;

}
