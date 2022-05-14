package com.gswf.financecontrol.controller;

import java.util.List;

import javax.annotation.Resource;

import com.gswf.financecontrol.dto.PurchaseProductDto;
import com.gswf.financecontrol.model.PurchaseProduct;
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
        return purchaseProductService.getAllPurchaseProduct();
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
