package com.gswf.financecontrol.controller;

import java.util.List;

import javax.annotation.Resource;

import com.gswf.financecontrol.model.Business;
import com.gswf.financecontrol.service.BusinessService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/business")
@AllArgsConstructor
public class BusinessController {
    
    @Resource
    private final BusinessService businessService;
    
    @GetMapping(value = {"", "/"})
    public List<Business> getAllBusiness() {
        return businessService.getAllBusiness();
    }

    @PostMapping(value = {"", "/save"})
    public List<Business> save(List<Business> business) {
        return businessService.saveAllBusiness(business);
    }

    @GetMapping(value = {"", "/type/{type}"})
    public List<Business> getByBusinessType(@PathVariable(value = "type") Long type) {
        return businessService.findByBusinessesType(type);
    }

}
