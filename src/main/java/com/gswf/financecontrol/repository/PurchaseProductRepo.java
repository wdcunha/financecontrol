package com.gswf.financecontrol.repository;

import com.gswf.financecontrol.model.PurchaseProduct;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseProductRepo extends JpaRepository<PurchaseProduct, Long> {
    
}
