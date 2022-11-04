package com.ecommerce.EcommerceWebsite.DTO;

import com.ecommerce.EcommerceWebsite.domain.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class ProductDTO {

    private String productName;


    private Integer price;

private String image;
    private Integer categoryId;
}
