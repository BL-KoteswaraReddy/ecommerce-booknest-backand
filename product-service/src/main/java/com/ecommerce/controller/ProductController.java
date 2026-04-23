package com.ecommerce.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products/test")
public class ProductController {

    @GetMapping
    public String test() {
        return "Product Service is running successfully";
    }
}
