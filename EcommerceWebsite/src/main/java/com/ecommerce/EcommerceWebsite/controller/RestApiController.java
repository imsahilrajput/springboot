package com.ecommerce.EcommerceWebsite.controller;


import com.ecommerce.EcommerceWebsite.DTO.*;
import com.ecommerce.EcommerceWebsite.domain.*;
import com.ecommerce.EcommerceWebsite.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/ecommerce")
public class RestApiController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    CustomerService customerService;

    @Autowired
    OrderDetailsService orderDetailsService;

    @Autowired
    CartService cartService;


//Add a new Category
    @PostMapping("/admin/category")
    public Category addCAtegory(@RequestBody CategoryDTO categoryDTO) {
        return this.categoryService.createCategory(categoryDTO);

    }
//Add a new product
//    @PostMapping("/product")
//    public Product addProduct(@RequestBody ProductDTO productDTO) {
//        return this.productService.addProduct(productDTO);
//
//    }
//New Customer Sign In
    @PostMapping("/customer")
    public Customer addCustomer(@RequestBody CustomerDTO customerDTO) {
        return this.customerService.addCustomer(customerDTO);

    }
//Place An Order
    @PostMapping("/order")
    public List<OrderDetails> addOrder(@RequestBody OrderDTO orderDTO) {
        return this.orderDetailsService.order(orderDTO);

    }
    //Get all Customers

    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return this.customerService.getAllCustomers();
    }
//Get Customer by id
    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable Integer id){
        return this.customerService.getCustomerById(id);
    }
//Get all ordrers
    @GetMapping("/order/{id}")
    public List<OrderResponseDTO> getAllOrders(@PathVariable Integer id){
        return this.orderDetailsService.getAllOrders(id);
    }

//Edit order
    @PutMapping("/orderedit")
    public void editOrder(@RequestBody RemoveProductFromOrderDTO removeProductFromOrderDTO){
         this.orderDetailsService.editOrder(removeProductFromOrderDTO);
    }

    //Edit Category
    @PutMapping("/editcategory")
    public Category editCategory(@RequestBody CategoryDTO categoryDTO){
        return this.categoryService.updateCategory(categoryDTO);
    }
//Edit a Product
    @PutMapping("/editProduct")
    public Product editProduct(@RequestBody ProductEditingDTO productEditingDTO){
        return  this.productService.changeProductPrice(productEditingDTO);
    }

    //Get all products

    @GetMapping("/products")
    public  List<ProductResponseDTO> getAllProducts(){
        return this.productService.getAllProducts();
    }

    @PostMapping("/admin/products/add")
    public Product adminProductAdd(@RequestParam  String productName, @RequestParam  Integer price , @RequestParam Integer categoryId, @RequestParam("productImage") MultipartFile file,@RequestParam("imgName") String imageName) throws IOException {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductName(productName);
        productDTO.setPrice(price);
        productDTO.setCategoryId(categoryId);


       return this.productService.addProduct(productDTO,file,imageName);
    }

    @PostMapping("/add/cart")
    public Cart addToCart(@RequestBody CartDTO cartDTO){
        return this.cartService.addToCart(cartDTO);
    }

    @GetMapping("/cart/{customerId}")
    public ListOfRemoveFromCartDTO getItemsOfCart(@PathVariable Integer customerId){
        return this.cartService.cartList(customerId);
    }

    @DeleteMapping("/remove/cart")
    public void removeFromCart(@RequestBody RemoveFromCartDTO removeFromCartDTO){
        this.cartService.removeFromCart(removeFromCartDTO);
    }

    //Login

    @PostMapping("/login")
    public CustomerLoginResponseDTO login(@RequestBody CustomerLoginDTO customerLoginDTO){
         return this.customerService.login(customerLoginDTO);
        }

}
