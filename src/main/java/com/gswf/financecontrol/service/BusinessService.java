package com.gswf.financecontrol.service;

import java.util.List;

import com.gswf.financecontrol.model.Business;

import org.springframework.validation.annotation.Validated;

@Validated
public interface BusinessService {
      
    Business findBusinessById(Long id);
    
    List<Business> findByBusinessesType(Long type);
    
    List<Business> getAllBusiness();
  
    List<Business> saveAllBusiness(List<Business> business);

    void deleteAllBusiness();
}
