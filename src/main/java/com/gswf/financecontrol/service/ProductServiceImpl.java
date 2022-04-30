package com.gswf.financecontrol.service;

import java.util.List;

import javax.annotation.Resource;

import com.gswf.financecontrol.model.Product;
import com.gswf.financecontrol.repository.ProductRepo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
      
    @Resource
    private ProductRepo productRepo;
  
    @Override
    public Product findProductById(Long id) {
        return productRepo.findById(id).get();        
    }
  
    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();        
    }
  
    @Override
    public List<Product> saveAllProduct(List<Product> products) {
        return productRepo.saveAll(products);        
    }
  
    @Override
    public void deleteAllProducts() {
        productRepo.deleteAll();        
    }
}
