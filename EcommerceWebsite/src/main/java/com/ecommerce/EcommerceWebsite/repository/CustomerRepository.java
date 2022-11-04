package com.ecommerce.EcommerceWebsite.repository;

import com.ecommerce.EcommerceWebsite.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer>{
    Optional<Customer> findByUsername(String username);
    Customer findByUsernameAndPassword(String username,String password);
}
