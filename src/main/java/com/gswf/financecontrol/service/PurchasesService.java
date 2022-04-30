package com.gswf.financecontrol.service;

import java.util.List;

import com.gswf.financecontrol.model.Purchases;

import org.springframework.validation.annotation.Validated;

@Validated
public interface PurchasesService {
      
    Purchases findPurchasesById(Long id);
    
    List<Purchases> getAllPurchases();
  
    List<Purchases> saveAllPurchases(List<Purchases> purchases);

    void deleteAllPurchases();
}
