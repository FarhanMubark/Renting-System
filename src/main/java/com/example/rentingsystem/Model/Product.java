package com.example.rentingsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import jakarta.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
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

    @NotEmpty(message = "Product name should not be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String productName;

    @NotEmpty(message = "Product Number should not be empty")
    @Column(columnDefinition = "varchar(50) not null")
    private String productNumber;

    @NotNull(message = "Product price should not be null")
    @Positive(message = "Product price must be positive")
    @Column(columnDefinition = "int not null")
    private Integer productPrice;

    @NotEmpty(message = "Product Description should not be Empty")
    @Column(columnDefinition = "varchar(100) not null")
    private String productDescription;

    @NotEmpty(message = "Product Category should not be empty")
    @Column(columnDefinition = "varchar(50) not null")

    private String productCategory;


    @Column(columnDefinition = "varchar(25)")
    private String productStatus;

    private LocalDateTime endDate;


    @Positive(message = "Quantity must be positive")
    @NotNull(message = "Quantity should not be null")
    @Column(columnDefinition = "int not null")
    private Integer quantity;


    @ManyToOne
    @JoinColumn(name = "lessor_id",referencedColumnName = "id")
    @JsonIgnore
    private Lessor lessor;

    @OneToMany(cascade = CascadeType.ALL, mappedBy ="product")
    private Set<MyOrder> myOrderSet;

}
