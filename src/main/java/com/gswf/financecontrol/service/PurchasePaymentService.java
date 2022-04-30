package com.gswf.financecontrol.service;

import java.util.List;

import com.gswf.financecontrol.model.PurchasePayment;

import org.springframework.validation.annotation.Validated;

@Validated
public interface PurchasePaymentService {
      
    List<PurchasePayment> getAllPurchasePayment();
  
    List<PurchasePayment> saveAllPurchasePayment(List<PurchasePayment> purchasePayment);

    void deleteAllPurchasePayment();
}
