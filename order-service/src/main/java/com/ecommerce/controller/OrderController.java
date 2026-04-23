package com.ecommerce.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class OrderController {

    @GetMapping
    public String test() {
        return "Service is running successfully";
    }
}
