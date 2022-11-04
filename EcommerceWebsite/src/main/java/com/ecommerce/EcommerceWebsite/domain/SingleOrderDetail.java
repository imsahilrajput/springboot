package com.ecommerce.EcommerceWebsite.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class SingleOrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer singleOrderId;

    @ManyToOne
    @JoinColumn(name="product")
    private Product product;

    private Integer amount;

    private Integer quantity;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "orderId")
    private OrderDetails orderDetails;



}
