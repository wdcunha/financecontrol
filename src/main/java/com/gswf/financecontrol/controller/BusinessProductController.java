package com.gswf.financecontrol.controller;

import java.util.List;

import javax.annotation.Resource;

import com.gswf.financecontrol.dto.PurchaseProductDto;
import com.gswf.financecontrol.model.BusinessProduct;
import com.gswf.financecontrol.service.ProductService;
import com.gswf.financecontrol.service.BusinessProductService;
import com.gswf.financecontrol.service.BusinessService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/business-products")
@AllArgsConstructor
public class BusinessProductController {
    
    @Resource
    private final BusinessProductService businessProductService;
    
    @Resource
    private final ProductService productService;
    
    @Resource
    private final BusinessService businessService;

    @GetMapping(value = {"", "/"})
    public List<BusinessProduct> getAllProductBusiness() {
        return businessProductService.getAllBusinessProduct();
    }

    @PostMapping(value = {"", "/save-all"})
    public List<BusinessProduct> saveAll(@RequestBody List<BusinessProduct> businessProduct) {
        return businessProductService.saveAllBusinessProduct(businessProduct);
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
