package com.gswf.financecontrol.service;

import java.util.List;

import com.gswf.financecontrol.model.BusinessPayment;

import org.springframework.validation.annotation.Validated;

@Validated
public interface BusinessPaymentService {
      
    List<BusinessPayment> getAllBusinessPayment();
  
    BusinessPayment saveBusinessPayment(BusinessPayment businessPayment);
  
    List<BusinessPayment> saveAllBusinessPayment(List<BusinessPayment> businessPayment);

    void deleteAllBusinessPayment();
}
