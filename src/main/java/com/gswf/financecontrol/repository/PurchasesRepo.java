package com.gswf.financecontrol.repository;

import com.gswf.financecontrol.model.Purchases;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchasesRepo extends JpaRepository<Purchases, Long> {
    
}
