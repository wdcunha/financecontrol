package com.gswf.financecontrol.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.gswf.financecontrol.model.Product;
import com.gswf.financecontrol.model.PurchaseProduct;
import com.gswf.financecontrol.model.Purchases;
import com.gswf.financecontrol.repository.PurchaseProductRepo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PurchaseProductServiceImpl implements PurchaseProductService {
      
    @Resource
    private PurchaseProductRepo purchaseProductRepo;
      
    @Resource
    private ProductService productService;
    
    @Resource
    private PurchasesService purchasesService;

    @Override
    public List<PurchaseProduct> getAllPurchaseProduct() {
        return purchaseProductRepo.findAll();
    }
  
    @Override
    public List<PurchaseProduct> getAllPurchaseByPurchaseId(int id) {
        return purchaseProductRepo.findByPurchasebyId(id);
    }
  
    @Override
    public List<PurchaseProduct> saveAllPurchaseProduct(List<PurchaseProduct> purchaseProducts) {
        return purchaseProductRepo.saveAll(purchaseProducts);        
    }
  
    @Override
    public void deleteAllPurchaseProduct() {
        purchaseProductRepo.deleteAll();        
    }
  
    @Override
    public List<PurchaseProduct> fillFullPPFromAll() {
        List<PurchaseProduct> pps = getAllPurchaseProduct();

        return this.fillOutPPs(pps);
    }
  
    @Override
    public List<PurchaseProduct> fillFullPPFromList(int id) {
        List<PurchaseProduct> pps = getAllPurchaseByPurchaseId(id);

        return this.fillOutPPs(pps);
    }

    private List<PurchaseProduct> fillOutPPs(List<PurchaseProduct> pp) {
        List<PurchaseProduct> dtoList = new ArrayList<>();
        
        for (PurchaseProduct p : pp) {
            Product prod = productService.findProductById(p.getPk().getProduct().getId());
            Purchases purch = purchasesService.findPurchasesById(p.getPk().getPurchase().getId());
            
            PurchaseProduct purchProd = new PurchaseProduct();
            purchProd.setProduct(prod);
            purchProd.setPurchase(purch);
            
            dtoList.add(purchProd);
        }
        return dtoList;
    }
}
