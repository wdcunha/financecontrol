package com.gswf.financecontrol.repository;

import java.util.List;

import com.gswf.financecontrol.model.Business;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BusinessRepo extends JpaRepository<Business, Long> {
    
    @Query(value = "SELECT * FROM business "
           + "WHERE business_type = :type", nativeQuery = true)
    List<Business> findByBusinessType(Long type);
}
