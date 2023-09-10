package com.example.rentingsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String productName;

    private String productNumber;


    private Integer productPrice;

    private String productDescription;

    private String productCategory;

    private String productStatus;

    private LocalDateTime endDate;

    private Integer quantity;
    private Integer review;

    @ManyToOne
    @JoinColumn(name = "lessor_id",referencedColumnName = "id")
    @JsonIgnore
    private Lessor lessor;

    @OneToMany(cascade = CascadeType.ALL, mappedBy ="product")
    private Set<MyOrder> myOrderSet;



}
