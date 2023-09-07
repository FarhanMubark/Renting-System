package com.example.rentingsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DateFormat;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "should not be null")
    @Column(columnDefinition = " int not null")
    private Integer price;

    private Date startDate;

    private Date endDate;

    @NotEmpty(message = "should not be empty")
    @Column(columnDefinition = "varchar(30) not null")
    private String sizeOfWharehose;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Lessor lessor;

}
