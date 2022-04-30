package com.gswf.financecontrol.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

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
    public List<PurchaseProduct> getAllProductPurchases() {
        List<PurchaseProduct> pp = purchaseProductService.getAllPurchaseProduct();
        List<PurchaseProduct> fetchpps = new ArrayList<>();

        for (PurchaseProduct p : pp) {
            System.out.println("pk product id: " + p.getPk().getProduct().getId());
            Product prod = productService.findProductById(p.getPk().getProduct().getId());
            Purchases purch = purchasesService.findPurchasesById(p.getPk().getPurchase().getId());
            p.getPk().setProduct(prod);
            p.getPk().setPurchase(purch);
            fetchpps.add(p);
        }

        return fetchpps;
    }

    @PostMapping(value = {"", "/save"})
    public List<PurchaseProduct> save(List<PurchaseProduct> purchaseProduct) {
        return purchaseProductService.saveAllPurchaseProduct(purchaseProduct);
    }

}
