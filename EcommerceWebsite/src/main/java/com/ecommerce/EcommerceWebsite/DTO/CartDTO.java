package com.ecommerce.EcommerceWebsite.DTO;

import lombok.Data;

@Data
public class CartDTO {
    private Integer productId;
    private Integer quantity;
    private Integer customerId;
}
