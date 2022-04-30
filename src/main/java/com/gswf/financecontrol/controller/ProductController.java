package com.gswf.financecontrol.controller;

import java.util.List;

import javax.annotation.Resource;

import com.gswf.financecontrol.model.Product;
import com.gswf.financecontrol.service.ProductService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductController {
    
    @Resource
    private final ProductService productService;

    @GetMapping(value = {"", "/"})
    public List<Product> getAllProducts() {
        return productService.getAllProducts();        
    }
}
