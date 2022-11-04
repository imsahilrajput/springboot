package com.ecommerce.EcommerceWebsite.DTO;

import com.ecommerce.EcommerceWebsite.domain.Customer;
import lombok.Data;

import java.util.List;


@Data
public class OrderDTO {
    private String address;

    private Integer customerId;

    private List<PerItemOrderDTO> perItemOrderDTOS;


}
