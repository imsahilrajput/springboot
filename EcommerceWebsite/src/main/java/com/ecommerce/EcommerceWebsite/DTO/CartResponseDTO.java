package com.ecommerce.EcommerceWebsite.DTO;

import lombok.Data;

@Data
public class CartResponseDTO {
    private Integer productId;
    private String productName;
    private Integer quantity;
    private Integer customerId;
    private String image;
}
