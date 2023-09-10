package com.example.rentingsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Should be not empty")
    @Column(columnDefinition = "varchar(255) not null")
    private String description;

    @NotEmpty(message = "Should be not empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String ticketType;


    @NotEmpty(message = "Should be not empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String ticketStatus = "active";


    @ManyToOne
    @JoinColumn(name = "lessor_id", referencedColumnName = "id")
    @JsonIgnore
    private Lessor lessor;

    @ManyToOne
    @JoinColumn(name = "renter_id", referencedColumnName = "id")
    @JsonIgnore
    private Renter renter;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}
