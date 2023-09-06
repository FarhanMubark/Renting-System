package com.example.rentingsystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

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

    // this for productPhoto;

    private Integer productPrice;

    private String productDescription;

    private String productCategory;

    private String productStatus;

    private Date endDate;

    private Integer review;

    @ManyToOne
    @JoinColumn()
    @JsonIgnore
    private Lessor lessor;

    @ManyToOne
    @JoinColumn()
    @JsonIgnore
    private Renter renter;

    @ManyToOne
    @JoinColumn()
    @JsonIgnore
    private MyOrder myOrder;



}
