package com.ecommerce.EcommerceWebsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    @RequestMapping("/")
    public String indexPage() {
        return "index";
    }

    @RequestMapping("/index")
    public String indexPage1() {
        return "index";
    }

    @RequestMapping("/adminDashboard")
    public String adminDashboard() {
        return "adminDashboard";
    }
    @RequestMapping("/cart")
    public String cart() {
        return "cart";
    }
    @RequestMapping("/clothes")
    public String clothes() {
        return "clothes";
    }
    @RequestMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
    @RequestMapping("/mobile")
    public String mobile() {
        return "mobile";
    }
    @RequestMapping("/orders")
    public String orders() {
        return "orders";
    }
    @RequestMapping("/sports")
    public String sports() {
        return "sports";
    }



}
