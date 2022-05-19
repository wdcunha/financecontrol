package com.gswf.financecontrol.service;

import java.util.List;

import com.gswf.financecontrol.model.BusinessTypes;

import org.springframework.validation.annotation.Validated;

@Validated
public interface BusinessTypesService {
      
    List<BusinessTypes> getAllBusinessTypes();

    List<BusinessTypes> saveAllTypes(List<BusinessTypes> businessTypes);

    void deleteAllBusinessTypes();
}
