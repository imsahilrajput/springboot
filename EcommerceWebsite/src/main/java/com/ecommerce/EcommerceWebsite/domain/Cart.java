package com.ecommerce.EcommerceWebsite.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private Integer productId;

    private  Integer quantity;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;
}
