package com.gswf.financecontrol.service;

import java.util.List;

import javax.annotation.Resource;

import com.gswf.financecontrol.model.Purchases;
import com.gswf.financecontrol.repository.PurchasesRepo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PurchasesServiceImpl implements PurchasesService {
      
    @Resource
    private PurchasesRepo purchasesRepo;
  
    @Override
    public Purchases findPurchasesById(Long id) {
        return purchasesRepo.findById(id).get();        
    }
  
    @Override
    public List<Purchases> getAllPurchases() {
        return purchasesRepo.findAll();        
    }
  
    @Override
    public List<Purchases> saveAllPurchases(List<Purchases> purchases) {
        return purchasesRepo.saveAll(purchases);        
    }
  
    @Override
    public void deleteAllPurchases() {
        purchasesRepo.deleteAll();        
    }
}
