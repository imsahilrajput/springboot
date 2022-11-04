package com.ecommerce.EcommerceWebsite.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrderDetails {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer OrderId;

    private String address;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;

    private Integer totalPayment;

    private Integer numberOfItems;

    private Date orderDate;

    private Date deliveryDate;

    private String status;

    @JsonIgnore
    @OneToMany(mappedBy = "orderDetails")
    private List<SingleOrderDetail> singleOrderDetailList;


}
