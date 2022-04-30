package com.gswf.financecontrol.service;

import java.util.List;

import javax.annotation.Resource;

import com.gswf.financecontrol.model.PurchaseProduct;
import com.gswf.financecontrol.repository.PurchaseProductRepo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PurchaseProductServiceImpl implements PurchaseProductService {
      
    @Resource
    private PurchaseProductRepo purchaseProductRepo;
  
    @Override
    public List<PurchaseProduct> getAllPurchaseProduct() {
        return purchaseProductRepo.findAll();
    }
  
    @Override
    public List<PurchaseProduct> saveAllPurchaseProduct(List<PurchaseProduct> purchaseProducts) {
        return purchaseProductRepo.saveAll(purchaseProducts);        
    }
  
    @Override
    public void deleteAllPurchaseProduct() {
        purchaseProductRepo.deleteAll();        
    }
}
