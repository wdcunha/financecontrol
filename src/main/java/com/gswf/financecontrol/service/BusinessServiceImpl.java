package com.gswf.financecontrol.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gswf.financecontrol.model.Business;
import com.gswf.financecontrol.repository.BusinessRepo;

@Service
@Transactional
public class BusinessServiceImpl implements BusinessService {
      
    @Resource
    private BusinessRepo businessRepo;
  
    @Override
    public Business findBusinessById(Long id) {
        return businessRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Business not found by id num: " + id));        
    }
  
    @Override
    public List<Business> findByBusinessesType(Long type){
        return businessRepo.findByBusinessType(type);
    }
  
    @Override
    public List<Business> getAllBusiness() {
        return businessRepo.findAll();        
    }
  
    @Override
    public Business saveBusiness(Business business) {
        return businessRepo.save(business);        
    }
  
    @Override
    public List<Business> saveAllBusiness(List<Business> business) {
        return businessRepo.saveAll(business);        
    }
  
    @Override
    public void deleteAllBusiness() {
        businessRepo.deleteAll();        
    }
}
