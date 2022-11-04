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
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer categoryId;

    private String categoryName;

    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> productList;



}
