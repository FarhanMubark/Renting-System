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
public class Support {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Should be not empty")
    @Column(columnDefinition = "varchar(25) not null")
    private String supportName;

    @NotEmpty(message = "Should be not empty")
    @Column(columnDefinition = "varchar(10) not null")
    private String phoneNumber;

    @NotEmpty(message = "Should be not empty")
    @Column(columnDefinition = "varchar(10) not null")
    private String supportType;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "support")
    private Set<Ticket> tickets;
}
