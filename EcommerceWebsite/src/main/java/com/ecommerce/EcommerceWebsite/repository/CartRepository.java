package com.ecommerce.EcommerceWebsite.repository;


import com.ecommerce.EcommerceWebsite.domain.Cart;
import com.ecommerce.EcommerceWebsite.domain.Customer;
import com.ecommerce.EcommerceWebsite.domain.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    List<Cart> findByCustomer(Customer customer);
    List<Cart> findByProductId(Integer productId);

}
