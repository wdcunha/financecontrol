package com.gswf.financecontrol.repository;

import com.gswf.financecontrol.model.PaymentTypes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTypesRepo extends JpaRepository<PaymentTypes, Long> {
    
}
