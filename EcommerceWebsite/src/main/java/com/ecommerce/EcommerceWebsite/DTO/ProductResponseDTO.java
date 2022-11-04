package com.ecommerce.EcommerceWebsite.DTO;

import lombok.Data;

@Data
public class ProductResponseDTO {
    private Integer productId;
    private String productName;

    private Integer price;


    private String categoryName;

    private String image;
}
