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


    @OneToOne
    @PrimaryKeyJoinColumn
    private Subscription subscription;

    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;


    @OneToMany(cascade = CascadeType.ALL, mappedBy ="lessor")
    private Set<Product> products;

    @OneToMany(cascade = CascadeType.ALL, mappedBy ="lessor")
    private Set<Ticket> tickets;


    @ManyToOne
    @JoinColumn(name = "support_id", referencedColumnName = "id")
    @JsonIgnore
    private Support support;


}
