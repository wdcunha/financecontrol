package com.gswf.financecontrol.controller;

import java.util.List;

import javax.annotation.Resource;

import com.gswf.financecontrol.model.BusinessPayment;
import com.gswf.financecontrol.service.BusinessPaymentService;
import com.gswf.financecontrol.service.BusinessService;
import com.gswf.financecontrol.service.PaymentTypesService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/business-payments")
@AllArgsConstructor
public class BusinessPaymentController {
    
    @Resource
    private final BusinessPaymentService businessPaymentService;
    
    @Resource
    private final PaymentTypesService payTypesService;
    
    @Resource
    private final BusinessService businessService;

    @GetMapping(value = {"", "/"})
    public List<BusinessPayment> getAllBusinessPayments() {
        return businessPaymentService.getAllBusinessPayment();
    }

    @PostMapping(value = {"", "/save"})
    public List<BusinessPayment> save(List<BusinessPayment> businessPayment) {
        return businessPaymentService.saveAllBusinessPayment(businessPayment);
    }
}
