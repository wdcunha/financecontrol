package com.gswf.financecontrol.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gswf.financecontrol.model.BusinessTypes;
import com.gswf.financecontrol.service.BusinessTypesService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/business-types")
@AllArgsConstructor
public class BusinessTypesController {
    
    private final BusinessTypesService businessTypesService;

    @GetMapping()
    public List<BusinessTypes> getAllBusinessTypes() {
        return businessTypesService.getAllBusinessTypes();
    }
    
}
