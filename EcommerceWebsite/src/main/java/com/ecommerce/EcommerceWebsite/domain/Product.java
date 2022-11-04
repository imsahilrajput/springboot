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
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer productId;

    private String productName;

    private Integer price;

    private String image;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category")
    private Category category;

    @JsonIgnore
   @OneToMany(mappedBy = "product")
    private List<SingleOrderDetail> singleOrderDetailList;




}
