package com.gswf.financecontrol.controller;

import java.util.List;

import com.gswf.financecontrol.model.Sales;
import com.gswf.financecontrol.repository.SalesRepo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/sales")
@AllArgsConstructor
public class SalesController {

    private final SalesRepo salesRepo;

    @GetMapping
    public List<Sales> getAllSales() {
        return salesRepo.findAll();
    }


    
}
