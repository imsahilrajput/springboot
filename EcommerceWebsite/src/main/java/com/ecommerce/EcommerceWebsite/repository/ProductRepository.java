package com.ecommerce.EcommerceWebsite.repository;


import com.ecommerce.EcommerceWebsite.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
