package com.gswf.financecontrol.repository;

import java.util.List;

import com.gswf.financecontrol.model.BusinessProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BusinessProductRepo extends JpaRepository<BusinessProduct, Long> {

    @Query(value = "SELECT * FROM business_product as p "
           + "WHERE p.business = :id", nativeQuery = true)
    List<BusinessProduct> findByBusinessById(int id);
}
