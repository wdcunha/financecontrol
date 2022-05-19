package com.gswf.financecontrol.repository;

import com.gswf.financecontrol.model.BusinessTypes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessTypesRepo extends JpaRepository<BusinessTypes, Long> {
    
}
