package com.gswf.financecontrol.repository;

import java.util.List;

import com.gswf.financecontrol.model.PurchaseProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PurchaseProductRepo extends JpaRepository<PurchaseProduct, Long> {

    @Query(value = "SELECT * FROM purchase_product as p "
           + "WHERE p.purchase = :id", nativeQuery = true)
    List<PurchaseProduct> findByPurchasebyId(int id);
    
}
