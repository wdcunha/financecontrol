package com.gswf.financecontrol.service;

import java.util.List;

import com.gswf.financecontrol.model.PaymentTypes;

import org.springframework.validation.annotation.Validated;

@Validated
public interface PaymentTypesService {
      
    List<PaymentTypes> getAllPaymentTypes();

    List<PaymentTypes> saveAllPaymentTypes(List<PaymentTypes> payTypes);

    void deleteAllPaymentTypes();
}
