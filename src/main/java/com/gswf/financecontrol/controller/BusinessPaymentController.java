package com.gswf.financecontrol.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.gswf.financecontrol.model.BusinessPayment;
import com.gswf.financecontrol.service.BusinessPaymentService;
import com.gswf.financecontrol.service.BusinessService;
import com.gswf.financecontrol.service.PaymentTypesService;

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
    @ResponseStatus(code = HttpStatus.CREATED)
    public List<BusinessPayment> save(@RequestBody List<BusinessPayment> businessPayment) {
        System.out.println("dados recebidos: " + businessPayment);
        return businessPaymentService.saveAllBusinessPayment(businessPayment);
    }
}
