package com.ecommerce.EcommerceWebsite.DTO;


import lombok.Data;

@Data
public class CustomerLoginResponseDTO {
    private Integer customerId;
    private String role;
    private  String message;
}
