package com.gswf.financecontrol.service;

import java.util.List;

import javax.annotation.Resource;

import com.gswf.financecontrol.model.PurchasePayment;
import com.gswf.financecontrol.repository.PurchasePaymentRepo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PurchasePaymentServiceImpl implements PurchasePaymentService {
      
    @Resource
    private PurchasePaymentRepo purchasePaymentRepo;
  
    @Override
    public List<PurchasePayment> getAllPurchasePayment() {
        return purchasePaymentRepo.findAll();        
    }
  
    @Override
    public List<PurchasePayment> saveAllPurchasePayment(List<PurchasePayment> purchasePayment) {
        return purchasePaymentRepo.saveAll(purchasePayment);        
    }
  
    @Override
    public void deleteAllPurchasePayment() {
        purchasePaymentRepo.deleteAll();        
    }
}
