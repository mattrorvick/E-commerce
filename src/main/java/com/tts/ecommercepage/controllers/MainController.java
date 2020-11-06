package com.tts.ecommercepage.controllers;

import java.util.ArrayList;
import java.util.List;

import com.tts.ecommercepage.model.Product;
import com.tts.ecommercepage.services.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@ControllerAdvice
public class MainController {
    

    @Autowired
    ProductService productService;

    public void addNew() {

        List<Product> allProducts = productService.findAll();

        if (allProducts.isEmpty()) {

            List<Product> newProducts = new ArrayList<Product>();

            newProducts.add(new Product(3, (float) 500.00, "best phone on the market", "iPhone 11", "Apple", "phone", "images/iphone11.jpg"));

            newProducts.add(new Product(7, (float) 500.00, "best computer on the market", "Macbook Pro", "Apple", "computer", "images/macbookpro.jpg"));

            newProducts.add(new Product(6, (float) 500.00, "best tablet on the market", "iPad Pro", "Apple", "tablet", "images/ipadpro.jpg"));

            newProducts.add(new Product(2, (float) 500.00, "best desktop on the market", "iMac Pro", "Apple", "computer", "images/imac.jpg"));

            for (Product product : newProducts) {
                productService.save(product);
            }
        } else {
            System.out.println("no products?");
        }

    }


    @GetMapping("/")
    public String main(Model model) {
        addNew();
        return "main";
    }

    @ModelAttribute("products")
    public List<Product> products() {
        return productService.findAll();
    }

    @ModelAttribute("categories")
    public List<String> categories() {
        return productService.findDistinctCategories();
    }

    @ModelAttribute("brands")
    public List<String> brands() {
        return productService.findDistinctBrands();
    }

    @GetMapping("/filter")
    public String filter(@RequestParam(required = false) String category,
                        @RequestParam(required = false) String brand,
                        Model model) {
           List<Product> filtered = productService.findByBrandAndOrCategory(brand, category);
           // Overrides the @ModelAttribute above.
           model.addAttribute("products", filtered); 
           return "main";
       }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
    
}