package com.gswf.financecontrol.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.gswf.financecontrol.dto.PurchaseProductDto;
import com.gswf.financecontrol.model.Product;
import com.gswf.financecontrol.model.PurchaseProduct;
import com.gswf.financecontrol.model.Purchases;
import com.gswf.financecontrol.service.ProductService;
import com.gswf.financecontrol.service.PurchaseProductService;
import com.gswf.financecontrol.service.PurchasesService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/purchase-products")
@AllArgsConstructor
public class PurchasesProductController {
    
    @Resource
    private final PurchaseProductService purchaseProductService;
    
    @Resource
    private final ProductService productService;
    
    @Resource
    private final PurchasesService purchasesService;

    @GetMapping(value = {"", "/"})
    public List<PurchaseProductDto> getAllProductPurchases() {
        List<PurchaseProduct> pp = purchaseProductService.getAllPurchaseProduct();
        List<PurchaseProductDto> dtoList = new ArrayList<>();

        for (PurchaseProduct p : pp) {
            System.out.println("pk product id: " + p.getPk().getProduct().getId());
            Product prod = productService.findProductById(p.getPk().getProduct().getId());
            Purchases purch = purchasesService.findPurchasesById(p.getPk().getPurchase().getId());
            PurchaseProductDto dto = new PurchaseProductDto(purch, prod, p.getQuantity());
            
            dtoList.add(dto);
        }

        return dtoList;
    }

    @PostMapping(value = {"", "/save"})
    public List<PurchaseProduct> save(List<PurchaseProduct> purchaseProduct) {
        return purchaseProductService.saveAllPurchaseProduct(purchaseProduct);
    }

    public static class PurchaseForm {

        private List<PurchaseProductDto> productPurchase;

        public List<PurchaseProductDto> getProductPurchase() {
            return productPurchase;
        }

        public void setProductPurchase(List<PurchaseProductDto> productPurchase) {
            this.productPurchase = productPurchase;
        }
    }

}
