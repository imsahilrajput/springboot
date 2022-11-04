package com.ecommerce.EcommerceWebsite.service;


import com.ecommerce.EcommerceWebsite.repository.SingleOrderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SingleOrderDetailsService {

    @Autowired
    SingleOrderDetailsRepository singleOrderDetailsRepository;

}
