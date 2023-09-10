package com.example.rentingsystem.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String productStatus;

    private String LessorUsername;

    private String productName;

    private Integer quantity;

    private Integer pricePerHour;

    private Integer productPrice;

    private Integer totalHours;

    private Double finalPrice;

    private LocalDateTime endDate;

    private String renterName;
    private String phoneNumber;
    private Boolean isreturned = false;
    private Boolean orderIsActive;
    // if user is still didn't give us the lessor product
    private Boolean orderBlockState;
    private LocalDateTime finalWarningDate;

//    @NotNull(message = "Should be not null")
//    @Column(columnDefinition = "int not null")
//    private Integer productPrice;

//    @NotEmpty(message = "Should be not empty")
//    @Column(columnDefinition = "varchar(30) not null")
//    private String lessorName;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    // problem
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "renter_id",referencedColumnName = "id")
    private Renter renter;




}
