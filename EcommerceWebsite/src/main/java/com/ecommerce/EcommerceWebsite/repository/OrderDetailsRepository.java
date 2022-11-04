package com.ecommerce.EcommerceWebsite.repository;

import com.ecommerce.EcommerceWebsite.domain.Customer;
import com.ecommerce.EcommerceWebsite.domain.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Integer> {
   List<OrderDetails>  findByCustomer(Customer customer);
}
