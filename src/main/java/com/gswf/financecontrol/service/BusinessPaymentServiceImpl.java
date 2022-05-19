package com.gswf.financecontrol.service;

import java.util.List;

import javax.annotation.Resource;

import com.gswf.financecontrol.model.BusinessPayment;
import com.gswf.financecontrol.repository.BusinessPaymentRepo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BusinessPaymentServiceImpl implements BusinessPaymentService {
      
    @Resource
    private BusinessPaymentRepo businessPaymentRepo;
  
    @Override
    public List<BusinessPayment> getAllBusinessPayment() {
        return businessPaymentRepo.findAll();        
    }
  
    @Override
    public List<BusinessPayment> saveAllBusinessPayment(List<BusinessPayment> businessPayment) {
        return businessPaymentRepo.saveAll(businessPayment);        
    }
  
    @Override
    public void deleteAllBusinessPayment() {
        businessPaymentRepo.deleteAll();        
    }
}
