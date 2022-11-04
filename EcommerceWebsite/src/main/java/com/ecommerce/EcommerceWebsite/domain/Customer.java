package com.ecommerce.EcommerceWebsite.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@Getter
@Setter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer customerId;

    private String customerName;

    private String username;
    private String password;
    private String Phone;

    private String role;
    private String email;
    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<OrderDetails> orderDetailsList;

    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<Cart> cartList;



}
