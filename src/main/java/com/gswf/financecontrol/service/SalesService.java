package com.gswf.financecontrol.service;

import java.util.List;

import com.gswf.financecontrol.model.Sales;

import org.springframework.validation.annotation.Validated;

@Validated
public interface SalesService {
      
    List<Sales> getAllSales();
  
    List<Sales> saveAllSales(List<Sales> sales);

    void deleteAllSales();
}
