package com.ecommerce.EcommerceWebsite.DTO;


import lombok.Data;

@Data
public class ProductEditingDTO {
    private Integer productId;
    private String productName;

    private Integer price;
}
