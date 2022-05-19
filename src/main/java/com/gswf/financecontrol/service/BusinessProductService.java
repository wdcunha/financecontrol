package com.gswf.financecontrol.service;

import java.util.List;

import com.gswf.financecontrol.model.BusinessProduct;

import org.springframework.validation.annotation.Validated;

@Validated
public interface BusinessProductService {
      
    List<BusinessProduct> getAllBusinessProduct();

    List<BusinessProduct> getAllBusinessByBusinessId(int id);
  
    List<BusinessProduct> saveAllBusinessProduct(List<BusinessProduct> businessProduct);

    void deleteAllBusinessProduct();

    List<BusinessProduct> fillFullPPFromAll();

    List<BusinessProduct> fillFullPPFromList(int id);
}
