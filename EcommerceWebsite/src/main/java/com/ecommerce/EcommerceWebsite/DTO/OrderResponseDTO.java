package com.ecommerce.EcommerceWebsite.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class OrderResponseDTO {
    String productName;
    Integer quantity;
    Integer price;
    Integer amount;
    Integer productId;
    String image;
    Date orderDate;
    Date deliveryDate;
}
