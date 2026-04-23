package com.ecommerce.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @GetMapping("/check")
    public String test() {
        return "Service is running successfully";
    }
}
