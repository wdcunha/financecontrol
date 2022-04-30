package com.gswf.financecontrol.repository;

import com.gswf.financecontrol.model.Sales;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepo extends JpaRepository<Sales, Long> {
    
}
