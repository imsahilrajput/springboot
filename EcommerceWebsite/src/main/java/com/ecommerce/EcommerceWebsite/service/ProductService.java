package com.ecommerce.EcommerceWebsite.service;


import com.ecommerce.EcommerceWebsite.DTO.ProductDTO;
import com.ecommerce.EcommerceWebsite.DTO.ProductEditingDTO;
import com.ecommerce.EcommerceWebsite.DTO.ProductResponseDTO;
import com.ecommerce.EcommerceWebsite.domain.Category;
import com.ecommerce.EcommerceWebsite.domain.Product;
import com.ecommerce.EcommerceWebsite.repository.CategoryRepository;
import com.ecommerce.EcommerceWebsite.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/webapp/resources/";
//public static String uploadDir = "/home/sahil/Desktop/flipkart frontend";


    public Product addProduct(ProductDTO productDTO, MultipartFile file,String imageName) throws IOException {

        List<Product> productList = productRepository.findAll();
        for(Product product:productList){
            if(product.getProductName().equals(productDTO.getProductName())){
                throw new EntityExistsException("Product with  name: " + productDTO.getProductName() + " already exists.");
            }
        }
        Category category;
        try{
            category= categoryRepository.findById(productDTO.getCategoryId()).get();
        }catch (Exception E){
            throw new EntityNotFoundException("Category not found. ");

        }

        Product product = new Product();
        product.setProductName(productDTO.getProductName());
        product.setCategory(category);
        product.setPrice(productDTO.getPrice());
        String imageUUId;

        if(!(file.isEmpty())){
            imageUUId = file.getOriginalFilename();
            Path path = Paths.get(uploadDir,imageUUId);
            Files.write(path,file.getBytes());
        }else {
            imageUUId = imageName;
        }
        product.setImage(imageUUId);
        System.out.println("imageName: "+imageUUId);


        return productRepository.save(product);


    }





    public Product changeProductPrice(ProductEditingDTO productEditingDTO){
        Product product;
        try{
            product=productRepository.findById(productEditingDTO.getProductId()).get();
        }catch(Exception ex){
            throw new EntityNotFoundException("Product with id " + productEditingDTO.getProductId()+"does not exist.");
        }

        product.setProductName(productEditingDTO.getProductName());
        product.setPrice(productEditingDTO.getPrice());
        return productRepository.save(product);
    }

    public List<ProductResponseDTO> getAllProducts(){
        List<Product> productList;
        try{
            productList=productRepository.findAll();
        }catch (Exception e){
            throw new EntityNotFoundException("No products found.");

        }
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        for(int i = 0;i<productList.size();i++){
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();
            productResponseDTO.setProductId(productList.get(i).getProductId());
            productResponseDTO.setProductName(productList.get(i).getProductName());
            productResponseDTO.setPrice(productList.get(i).getPrice());
            productResponseDTO.setCategoryName(productList.get(i).getCategory().getCategoryName());
            productResponseDTO.setImage(productList.get(i).getImage());

            productResponseDTOS.add(productResponseDTO);

        }

        return productResponseDTOS;

    }
}
