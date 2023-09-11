package com.example.rentingsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "Should Not be empty")
    @Column(columnDefinition = "varchar(25) not null")
    private String productName;

    @NotEmpty(message = "Should Not be empty")
    @Column(columnDefinition = "varchar(25) not null")
    private String productNumber;

    @NotEmpty(message = "Should Not be empty")
    @Column(columnDefinition = "int not null")
    private Integer productPrice;

    @NotEmpty(message = "Should Not be empty")
    @Column(columnDefinition = "varchar(100) not null")
    private String productDescription;

    @NotEmpty(message = "Should Not be empty")
    @Column(columnDefinition = "varchar(25) not null")
    private String productCategory;

    @NotEmpty(message = "Should Not be empty")
    @Column(columnDefinition = "varchar(25) not null")
    private String productStatus;

    private LocalDateTime endDate;

    @NotEmpty(message = "Should Not be empty")
    @Column(columnDefinition = "int not null")
    private Integer quantity;

    private Integer review;

    @ManyToOne
    @JoinColumn(name = "lessor_id",referencedColumnName = "id")
    @JsonIgnore
    private Lessor lessor;

    @OneToMany(cascade = CascadeType.ALL, mappedBy ="product")
    private Set<MyOrder> myOrderSet;

}
