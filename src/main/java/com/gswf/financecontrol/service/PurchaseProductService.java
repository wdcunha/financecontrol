package com.gswf.financecontrol.service;

import java.util.List;

import com.gswf.financecontrol.model.PurchaseProduct;

import org.springframework.validation.annotation.Validated;

@Validated
public interface PurchaseProductService {
      
    List<PurchaseProduct> getAllPurchaseProduct();

    List<PurchaseProduct> getAllPurchaseByPurchaseId(int id);
  
    List<PurchaseProduct> saveAllPurchaseProduct(List<PurchaseProduct> purchaseProduct);

    void deleteAllPurchaseProduct();

    List<PurchaseProduct> fillFullPPFromAll();

    List<PurchaseProduct> fillFullPPFromList(int id);
}
