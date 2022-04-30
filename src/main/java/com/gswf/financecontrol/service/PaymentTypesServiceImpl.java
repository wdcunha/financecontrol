package com.gswf.financecontrol.service;

import java.util.List;

import javax.annotation.Resource;

import com.gswf.financecontrol.model.PaymentTypes;
import com.gswf.financecontrol.repository.PaymentTypesRepo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PaymentTypesServiceImpl implements PaymentTypesService {
      
    @Resource
    private PaymentTypesRepo payTypeRepo;
  
    @Override
    public List<PaymentTypes> getAllPaymentTypes() {
        return payTypeRepo.findAll();        
    }
  
    @Override
    public List<PaymentTypes> saveAllPaymentTypes(List<PaymentTypes> payTypes) {
        return payTypeRepo.saveAll(payTypes);        
    }
  
    @Override
    public void deleteAllPaymentTypes() {
        payTypeRepo.deleteAll();        
    }
}
