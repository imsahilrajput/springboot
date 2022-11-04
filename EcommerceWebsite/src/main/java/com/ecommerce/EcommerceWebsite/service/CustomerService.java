package com.ecommerce.EcommerceWebsite.service;

import com.ecommerce.EcommerceWebsite.DTO.CustomerDTO;
import com.ecommerce.EcommerceWebsite.DTO.CustomerLoginDTO;
import com.ecommerce.EcommerceWebsite.DTO.CustomerLoginResponseDTO;
import com.ecommerce.EcommerceWebsite.domain.Customer;
import com.ecommerce.EcommerceWebsite.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer addCustomer(CustomerDTO customerDTO){
        List<Customer> customerList = customerRepository.findAll();
        for(Customer customer:customerList){
            if(customer.getUsername().equals(customerDTO.getUsername())){
                throw new EntityExistsException("CUstomer with  username: " + customer.getUsername() + " already exists.");
            }
        }

        Customer customer = new Customer();
        customer.setCustomerName(customerDTO.getCustomerName());
        customer.setPhone(customerDTO.getPhone());
        customer.setUsername(customerDTO.getUsername());
        customer.setPassword(customerDTO.getPassword());

        return customerRepository.save(customer);
    }


    public List<Customer> getAllCustomers(){
        List<Customer> customerList =customerRepository.findAll();
        if(customerList.size()==0){
            throw new EntityNotFoundException("No customer found");
        }

        return customerList;
    }

    public Customer getCustomerById(Integer id){
        Customer customer;
        try{
            customer=customerRepository.findById(id).get();

        }catch (Exception ex){
            throw new EntityNotFoundException("Customer not found.");
        }

        return customer;
    }

    public CustomerLoginResponseDTO login(CustomerLoginDTO customerLoginDTO){
        Customer customer;
        try{
            customer = customerRepository.findByUsernameAndPassword(customerLoginDTO.getUserName(),customerLoginDTO.getPassword());
        }catch (NullPointerException ex){
            throw new EntityNotFoundException("Invalid Credentials");
        }
        CustomerLoginResponseDTO customerLoginResponseDTO = new CustomerLoginResponseDTO();
        customerLoginResponseDTO.setCustomerId(customer.getCustomerId());
        customerLoginResponseDTO.setRole(customer.getRole());
        customerLoginResponseDTO.setMessage("Success");

        return customerLoginResponseDTO;


    }
}
