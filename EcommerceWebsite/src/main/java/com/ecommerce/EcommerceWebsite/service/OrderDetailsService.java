package com.ecommerce.EcommerceWebsite.service;


import com.ecommerce.EcommerceWebsite.DTO.*;
import com.ecommerce.EcommerceWebsite.domain.Cart;
import com.ecommerce.EcommerceWebsite.domain.Customer;
import com.ecommerce.EcommerceWebsite.domain.OrderDetails;
import com.ecommerce.EcommerceWebsite.domain.SingleOrderDetail;
import com.ecommerce.EcommerceWebsite.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class OrderDetailsService {

    @Autowired
    OrderDetailsRepository orderDetailsRepository;

    @Autowired
    SingleOrderDetailsRepository singleOrderDetailsRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CartRepository cartRepository;

    public List<OrderDetails> order(OrderDTO orderDTO){
        Integer totalAmount = 0;
        Integer totalQuantity=0;

        for(PerItemOrderDTO perItemOrderDTO: orderDTO.getPerItemOrderDTOS()){
            totalAmount+=productRepository.findById( perItemOrderDTO.getProductId()).get().getPrice()*perItemOrderDTO.getQuantity();
            totalQuantity+=perItemOrderDTO.getQuantity();
        }


        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setAddress(orderDTO.getAddress());
        orderDetails.setCustomer(customerRepository.findById(orderDTO.getCustomerId()).get());
        orderDetails.setTotalPayment(totalAmount);
        orderDetails.setNumberOfItems(totalQuantity);
        Date orderDate = new Date();
        Date deliveryDate =new Date(orderDate.getTime()+(7*86400000));

        String status;
        Date today = new Date();

        if (today.after(deliveryDate)) {
            status="Delivered";
        }else{
            status="In Transit";

        }

        orderDetails.setOrderDate(orderDate);
        orderDetails.setDeliveryDate(deliveryDate);
        orderDetails.setStatus(status);


        OrderDetails orderDetails1 = orderDetailsRepository.saveAndFlush(orderDetails);


        Integer amount = 1;

        List<SingleOrderDetail> singleOrderDetails = new ArrayList<>();
        for(PerItemOrderDTO perItemOrderDTO: orderDTO.getPerItemOrderDTOS()){
            SingleOrderDetail singleOrderDetail= new SingleOrderDetail();
            singleOrderDetail.setProduct(productRepository.getReferenceById(perItemOrderDTO.getProductId()));
            singleOrderDetail.setQuantity(perItemOrderDTO.getQuantity());
            amount = productRepository.findById( perItemOrderDTO.getProductId()).get().getPrice()*perItemOrderDTO.getQuantity();
            singleOrderDetail.setAmount(amount);
            singleOrderDetail.setOrderDetails(orderDetails1);

            SingleOrderDetail singleOrderDetail1 = singleOrderDetailsRepository.saveAndFlush(singleOrderDetail);
            singleOrderDetails.add(singleOrderDetail1);
        }


        List<Cart> cartList = cartRepository.findByCustomer(customerRepository.findById(orderDTO.getCustomerId()).get());
        for(int i = 0;i<cartList.size();i++){
            cartRepository.delete(cartList.get(i));
        }


        return orderDetailsRepository.findAll();




    }

    public List<OrderResponseDTO> getAllOrders(Integer id){
        Customer customer = customerRepository.findById(id).get();
        List<OrderDetails> orderDetails;
        List<SingleOrderDetail> singleOrderDetails = new ArrayList<>();
        List<OrderResponseDTO>  orderResponseDTOS =new ArrayList<>();
        List<DateResponseDTO> dateResponseDTOList = new ArrayList<>();


        try{
             orderDetails =orderDetailsRepository.findByCustomer(customer);

        }catch (Exception ex){
            throw new EntityNotFoundException("No orders found");
        }

        for(int i = 0;i<orderDetails.size();i++){
            DateResponseDTO dateResponseDTO = new DateResponseDTO();
            List<SingleOrderDetail> singleOrderDetailsList= new ArrayList<>();
            singleOrderDetailsList=singleOrderDetailsRepository.findByOrderDetails(orderDetails.get(i));
            singleOrderDetails.addAll(singleOrderDetailsList);
            dateResponseDTO.setOrderId(orderDetails.get(i).getOrderId());
            dateResponseDTO.setOrderDate(orderDetails.get(i).getOrderDate());
            dateResponseDTO.setDeliveryDate(orderDetails.get(i).getDeliveryDate());
            dateResponseDTOList.add(dateResponseDTO);
        }

        for(int i =0;i<singleOrderDetails.size();i++){
            OrderResponseDTO orderResponseDTO = new OrderResponseDTO();

            orderResponseDTO.setProductName(singleOrderDetails.get(i).getProduct().getProductName());
            orderResponseDTO.setAmount(singleOrderDetails.get(i).getAmount());
            orderResponseDTO.setQuantity(singleOrderDetails.get(i).getQuantity());
            orderResponseDTO.setPrice(singleOrderDetails.get(i).getProduct().getPrice());
            orderResponseDTO.setProductId(singleOrderDetails.get(i).getProduct().getProductId());
            orderResponseDTO.setImage(singleOrderDetails.get(i).getProduct().getImage());
            for(int j =0;j<dateResponseDTOList.size();j++){
                if(Objects.equals(singleOrderDetails.get(i).getOrderDetails().getOrderId(), dateResponseDTOList.get(j).getOrderId())){
                    orderResponseDTO.setOrderDate(dateResponseDTOList.get(j).getOrderDate());
                    orderResponseDTO.setDeliveryDate(dateResponseDTOList.get(j).getDeliveryDate());
                }

          }
            orderResponseDTOS.add(orderResponseDTO);

        }



        return  orderResponseDTOS;

    }

    public void editOrder(RemoveProductFromOrderDTO removeProductFromOrderDTO){
        OrderDetails orderDetails;
        List<Integer> singleOrderIds = new ArrayList<>();
        try{
            orderDetails=orderDetailsRepository.findById(removeProductFromOrderDTO.getOrderId()).get();

        }catch (Exception ex){
            throw new EntityNotFoundException("No order found with this id.");
        }

        List<SingleOrderDetail> singleOrderDetails=orderDetails.getSingleOrderDetailList();
        for(int i = 0;i <singleOrderDetails.size();i++){
            singleOrderIds.add(singleOrderDetails.get(i).getSingleOrderId());

        }

        for(int i = 0; i<singleOrderIds.size();i++){
            SingleOrderDetail singleOrderDetail = singleOrderDetailsRepository.findById(singleOrderIds.get(i)).get();
            if(singleOrderDetail.getProduct().getProductId()==removeProductFromOrderDTO.getProductId()){

                System.out.println((singleOrderDetail.getQuantity()-removeProductFromOrderDTO.getQuantity()));
                singleOrderDetail.setAmount((singleOrderDetail.getQuantity()-removeProductFromOrderDTO.getQuantity())*productRepository.findById(removeProductFromOrderDTO.getProductId()).get().getPrice());
                singleOrderDetail.setQuantity(singleOrderDetail.getQuantity()-removeProductFromOrderDTO.getQuantity());
                singleOrderDetailsRepository.save(singleOrderDetail);
            }
        }

        orderDetails.setTotalPayment(orderDetails.getTotalPayment()-(productRepository.findById(removeProductFromOrderDTO.getProductId()).get().getPrice()*removeProductFromOrderDTO.getQuantity()));
        orderDetailsRepository.save(orderDetails);
    }

}
