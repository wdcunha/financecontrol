package com.gswf.financecontrol.repository;

import com.gswf.financecontrol.model.BusinessPayment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessPaymentRepo extends JpaRepository<BusinessPayment, Long> {
    
}
