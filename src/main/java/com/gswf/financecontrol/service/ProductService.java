package com.gswf.financecontrol.service;

import java.util.List;

import com.gswf.financecontrol.model.Product;

import org.springframework.validation.annotation.Validated;

@Validated
public interface ProductService {
      
    Product findProductById(Long id);

    List<Product> getAllProducts();

    List<Product> saveAllProduct(List<Product> products);

    void deleteAllProducts();
}
