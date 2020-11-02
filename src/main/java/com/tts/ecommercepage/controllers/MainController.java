package com.tts.ecommercepage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@ControllerAdvice
public class MainController {
    

    // @Autowired
    // ProductService productService;

    @GetMapping("/")
    public String main() {
        return "main";
    }


}