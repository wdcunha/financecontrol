package com.gswf.financecontrol.service;

import java.util.List;

import javax.annotation.Resource;

import com.gswf.financecontrol.model.BusinessTypes;
import com.gswf.financecontrol.repository.BusinessTypesRepo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BusinessTypesServiceImpl implements BusinessTypesService {
      
    @Resource
    private BusinessTypesRepo businessTypesRepo;
  
    @Override
    public List<BusinessTypes> getAllBusinessTypes() {
        return businessTypesRepo.findAll();        
    }
  
    @Override
    public List<BusinessTypes> saveAllTypes(List<BusinessTypes> businessTypes) {
        return businessTypesRepo.saveAll(businessTypes);        
    }
  
    @Override
    public void deleteAllBusinessTypes() {
        businessTypesRepo.deleteAll();        
    }
}
