package com.gswf.financecontrol.controller;

import java.util.List;

import com.gswf.financecontrol.model.PaymentTypes;
import com.gswf.financecontrol.service.PaymentTypesService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/payment-types")
@AllArgsConstructor
public class PaymentTypesController {
    
    private final PaymentTypesService paymentTypesService;

    @GetMapping()
    public List<PaymentTypes> getAllBusinessTypes() {
        return paymentTypesService.getAllPaymentTypes();
    }
    
}
