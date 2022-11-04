package com.ecommerce.EcommerceWebsite.repository;

import com.ecommerce.EcommerceWebsite.domain.OrderDetails;
import com.ecommerce.EcommerceWebsite.domain.SingleOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SingleOrderDetailsRepository extends JpaRepository<SingleOrderDetail,Integer> {
    List<SingleOrderDetail> findByOrderDetails(OrderDetails orderDetails);
}
