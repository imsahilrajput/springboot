package com.ecommerce.EcommerceWebsite.DTO;

import lombok.Data;

@Data
public class RemoveProductFromOrderDTO {
    Integer orderId;
    Integer productId;
    Integer quantity;
}
