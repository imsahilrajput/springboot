package com.ecommerce.EcommerceWebsite.repository;

import com.ecommerce.EcommerceWebsite.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer>{
    Category findByCategoryName(String categoryName);
}
