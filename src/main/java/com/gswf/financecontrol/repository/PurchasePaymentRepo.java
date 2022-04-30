package com.gswf.financecontrol.repository;

import com.gswf.financecontrol.model.PurchasePayment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasePaymentRepo extends JpaRepository<PurchasePayment, Long> {
    
}
