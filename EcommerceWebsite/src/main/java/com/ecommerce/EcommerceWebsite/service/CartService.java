package com.ecommerce.EcommerceWebsite.service;


import com.ecommerce.EcommerceWebsite.DTO.CartDTO;
import com.ecommerce.EcommerceWebsite.DTO.CartResponseDTO;
import com.ecommerce.EcommerceWebsite.DTO.ListOfRemoveFromCartDTO;
import com.ecommerce.EcommerceWebsite.DTO.RemoveFromCartDTO;
import com.ecommerce.EcommerceWebsite.domain.Cart;
import com.ecommerce.EcommerceWebsite.domain.Customer;
import com.ecommerce.EcommerceWebsite.repository.CartRepository;
import com.ecommerce.EcommerceWebsite.repository.CustomerRepository;
import com.ecommerce.EcommerceWebsite.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    public Cart addToCart(CartDTO cartDTO){
        Cart cart =new Cart();
        List<Cart> cartList = new ArrayList<>();
//        cartList=cartRepository.findByCustomer(customerRepository.findById(cartDTO.getCustomerId()).get());
        cart.setCustomer(customerRepository.findById(cartDTO.getCustomerId()).get());

        cart.setQuantity(cartDTO.getQuantity());
        cart.setProductId(cartDTO.getProductId());

        return cartRepository.save(cart);
    }

    public ListOfRemoveFromCartDTO cartList(Integer customerId){
        Customer customer;
        try{
            customer=customerRepository.findById(customerId).get();
        }catch (Exception ex ){
            throw new EntityNotFoundException("No customer with id"+customerId+"found.");
        }

        List<Cart> cartList = new ArrayList<>();
        cartList=cartRepository.findByCustomer(customerRepository.findById(customerId).get());

        Map<Integer,CartResponseDTO> cartResponseDTOList= new HashMap<>();
        List<Integer> productIds=new ArrayList<>();
        for(int i = 0; i<cartList.size();i++){
            CartResponseDTO cartResponseDTO;
            if(cartResponseDTOList.get(cartList.get(i).getProductId()) == null){
                cartResponseDTO = new CartResponseDTO();
                cartResponseDTO.setCustomerId(cartList.get(i).getCustomer().getCustomerId());
                cartResponseDTO.setQuantity(cartList.get(i).getQuantity());
                cartResponseDTO.setProductId(cartList.get(i).getProductId());
                cartResponseDTO.setImage(productRepository.findById(cartList.get(i).getProductId()).get().getImage());
                cartResponseDTO.setProductName(productRepository.findById(cartList.get(i).getProductId()).get().getProductName());
                if(!productIds.contains(cartList.get(i).getProductId())){
                    productIds.add(cartList.get(i).getProductId());

                }
            }
            else {
                cartResponseDTO  = cartResponseDTOList.get(cartList.get(i).getProductId());
                cartResponseDTO.setQuantity(cartResponseDTO.getQuantity() + cartList.get(i).getQuantity());
                if(!productIds.contains(cartList.get(i).getProductId())){
                    productIds.add(cartList.get(i).getProductId());

                }
            }
            cartResponseDTOList.put(cartList.get(i).getProductId(),cartResponseDTO);
        }
        ListOfRemoveFromCartDTO ListOfRemoveFromCartDTO = new ListOfRemoveFromCartDTO();
        ListOfRemoveFromCartDTO.setMapOfRemoveFromCartDTO(cartResponseDTOList);
        ListOfRemoveFromCartDTO.setProductIdsList(productIds);
        return ListOfRemoveFromCartDTO;
    }

    public void removeFromCart(RemoveFromCartDTO removeFromCartDTO){
        List<Cart> cartList=cartRepository.findByProductId(removeFromCartDTO.getProductId());
        for(int i= 0;i<cartList.size();i++){
            if(cartList.get(i).getCustomer()==(customerRepository.findById(removeFromCartDTO.getCustomerId()).get())){
                cartRepository.delete(cartList.get(i));
            }
        }
    }
}
