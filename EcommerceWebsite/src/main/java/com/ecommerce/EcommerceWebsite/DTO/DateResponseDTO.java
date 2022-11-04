package com.ecommerce.EcommerceWebsite.DTO;

import lombok.Data;

import java.util.Date;

@Data
public class DateResponseDTO {
    Integer orderId;
    Date orderDate;
    Date deliveryDate;
}
