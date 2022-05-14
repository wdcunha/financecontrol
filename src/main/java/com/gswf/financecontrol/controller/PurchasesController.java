package com.gswf.financecontrol.controller;

import java.util.List;

import javax.annotation.Resource;

import com.gswf.financecontrol.model.Purchases;
import com.gswf.financecontrol.service.PurchaseProductService;
import com.gswf.financecontrol.service.PurchasesService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/purchases")
@AllArgsConstructor
public class PurchasesController {
    
    @Resource
    private final PurchasesService purchasesService;
    
    @Resource
    private final PurchaseProductService ppService;

    @GetMapping(value = {"", "/"})
    public List<Purchases> getAllPurchases() {
        return purchasesService.getAllPurchases();
    }

    @GetMapping(value = {"", "/full"})
    public List<Purchases> getAllPurchasesFull() {
        return purchasesService.getAllPurchases();
    }

    @PostMapping(value = {"", "/save"})
    public List<Purchases> save(List<Purchases> purchases) {
        return purchasesService.saveAllPurchases(purchases);
    }

}
