package com.gswf.financecontrol.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.gswf.financecontrol.model.Product;
import com.gswf.financecontrol.model.BusinessProduct;
import com.gswf.financecontrol.model.Business;
import com.gswf.financecontrol.repository.BusinessProductRepo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BusinessProductServiceImpl implements BusinessProductService {
      
    @Resource
    private BusinessProductRepo businessProductRepo;
      
    @Resource
    private ProductService productService;
    
    @Resource
    private BusinessService businessService;

    @Override
    public List<BusinessProduct> getAllBusinessProduct() {
        return businessProductRepo.findAll();
    }
  
    @Override
    public List<BusinessProduct> getAllBusinessByBusinessId(int id) {
        return businessProductRepo.findByBusinessById(id);
    }
  
    @Override
    public List<BusinessProduct> saveAllBusinessProduct(List<BusinessProduct> businessProducts) {
        return businessProductRepo.saveAll(businessProducts);        
    }
  
    @Override
    public void deleteAllBusinessProduct() {
        businessProductRepo.deleteAll();        
    }
  
    @Override
    public List<BusinessProduct> fillFullPPFromAll() {
        List<BusinessProduct> pps = getAllBusinessProduct();

        return this.fillOutPPs(pps);
    }
  
    @Override
    public List<BusinessProduct> fillFullPPFromList(int id) {
        List<BusinessProduct> pps = getAllBusinessByBusinessId(id);

        return this.fillOutPPs(pps);
    }

    private List<BusinessProduct> fillOutPPs(List<BusinessProduct> pp) {
        List<BusinessProduct> dtoList = new ArrayList<>();
        
        for (BusinessProduct p : pp) {
            Product prod = productService.findProductById(p.getPk().getProduct().getId());
            Business purch = businessService.findBusinessById(p.getPk().getBusiness().getId());
            
            BusinessProduct purchProd = new BusinessProduct();
            purchProd.setProduct(prod);
            purchProd.setBusiness(purch);
            
            dtoList.add(purchProd);
        }
        return dtoList;
    }
}
