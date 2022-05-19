package com.gswf.financecontrol.service;

import java.util.List;

import javax.annotation.Resource;

import com.gswf.financecontrol.model.Sales;
import com.gswf.financecontrol.repository.SalesRepo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SalesServiceImpl implements SalesService {
      
    @Resource
    private SalesRepo salesRepo;
  
    @Override
    public List<Sales> getAllSales() {
        return salesRepo.findAll();        
    }
  
    @Override
    public List<Sales> saveAllSales(List<Sales> sales) {
        return salesRepo.saveAll(sales);        
    }
  
    @Override
    public void deleteAllSales() {
        salesRepo.deleteAll();        
    }
}
