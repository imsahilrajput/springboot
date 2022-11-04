package com.ecommerce.EcommerceWebsite.service;


import com.ecommerce.EcommerceWebsite.DTO.CategoryDTO;
import com.ecommerce.EcommerceWebsite.DTO.CreateCategoryDTO;
import com.ecommerce.EcommerceWebsite.domain.Category;
import com.ecommerce.EcommerceWebsite.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;


//Create a new category of products
    public Category createCategory(CategoryDTO categoryDTO){
        List<Category> categoryList = categoryRepository.findAll();
        for(Category newCategory : categoryList){
            if(categoryDTO.getCategortyName().equals(newCategory.getCategoryName())){
                throw new EntityExistsException("Category with  name: " + categoryDTO.getCategortyName() + " already exists.");
            }
        }
        Category category = new Category();
        category.setCategoryName(categoryDTO.getCategortyName());
        category.setDescription(categoryDTO.getDescription());
        return categoryRepository.save(category);


    }
    //Update any category

    public Category updateCategory(CategoryDTO categoryDTO){
        Category category;
        try{
            category=categoryRepository.findByCategoryName(categoryDTO.getCategortyName());

        }catch (Exception ex){
            throw new EntityNotFoundException("Category with  name: " + categoryDTO.getCategortyName() + " does not exist.");
        }

        category.setDescription(categoryDTO.getDescription());
        return categoryRepository.save(category);
    }
}
