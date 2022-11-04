package com.ecommerce.EcommerceWebsite.DTO;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ListOfRemoveFromCartDTO {
    Map<Integer,CartResponseDTO> MapOfRemoveFromCartDTO;
    List<Integer> productIdsList;
}
