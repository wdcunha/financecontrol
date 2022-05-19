package com.gswf.financecontrol.controller;

import java.util.List;

import com.gswf.financecontrol.model.BusinessTypes;
import com.gswf.financecontrol.repository.BusinessTypesRepo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/business-types")
@AllArgsConstructor
public class BusinessTypesController {
    
    private final BusinessTypesRepo businessTypesRepo;

    @GetMapping()
    public List<BusinessTypes> getAllBusinessTypes() {
        return businessTypesRepo.findAll();
    }
    
}
