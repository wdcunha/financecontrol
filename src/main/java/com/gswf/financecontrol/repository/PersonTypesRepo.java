package com.gswf.financecontrol.repository;

import com.gswf.financecontrol.model.PersonTypes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonTypesRepo extends JpaRepository<PersonTypes, Long> {
    
}
